package capaDeDatos;

import java.sql.*;
import java.util.ArrayList;

import appExceptions.ApplicationException;
import capaDeEntidades.Ficha;
import capaDeEntidades.Jugador;
import capaDeEntidades.Partida;
import capaDeEntidades.Posicion;

public class DataPartida {

	public DataPartida(){
		
	}
	
	DataJugador datJug = new DataJugador();
	DataFicha datFic = new DataFicha(); 
	
	public Partida buscarPartida(String dni1, String dni2) {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Partida part = new Partida();
		
		try{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select idPartida, turno from partidas where dniJugadorBlanco = ? and dniJugadorNegro = ?");
			stmt.setString(1, dni1);
			stmt.setString(2, dni2);
			rs = stmt.executeQuery(); 
			
			if(rs !=null && rs.next()){ 
				part.setIdPartida(rs.getInt("idPartida")); 
				part.setTurno(rs.getBoolean("turno"));
			}
			else return null;
			
		} catch (SQLException e){			
			e.printStackTrace();
		}
		finally{
			
			try{
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				
			} catch (SQLException e){
				
				e.printStackTrace();
				
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
		//Se le asignan los jugadores a la partida, falta asignarle el array de fichas
		
		part.setJugadorBlanco(datJug.getByDni(dni1));
		part.setJugadorNegro(datJug.getByDni(dni2));
		//se asigna en arreglo de fichas
		part.setFichas(datFic.buscarFichas(part.getIdPartida()));
		return part;
		
	}

	public Partida crearPartida(String dni1, String dni2) throws ApplicationException {//QUEDAMOS ACA 4/11 VER TURNO SI PONERLO EN BOOL O DEJARLO INT - HECHO
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Partida part = new Partida();
		
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into partidas(turno, dniJugadorBlanco, dniJugadorNegro) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setBoolean(1, true); // RESUELTO- SE COMIENZA POR DEFECTO CON LAS FICHAS BLANCAS, TRUE = BLANCAS, FALSE = NEGRAS
			stmt.setString(2, dni1);
			stmt.setString(3, dni2);
			stmt.execute();
			
			rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
				part.setIdPartida(rs.getInt(1)); 
			}
		}
		
		catch(SQLException e){
			
			e.printStackTrace();	
		}
		finally{
			
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				
			} catch(SQLException e){
				
				e.printStackTrace();
				
			}
			finally{
				
				FactoryConexion.getInstancia().releaseConn();
				
			}
			
		}
		
		part.setFichas(Ficha.setearFichas());
		for (Ficha f : part.getFichas()) {
			
			datFic.crearFicha(f, part);
			
		}
		
		return part;
		
	}

	public boolean savePartida(Partida part) throws ApplicationException { //update de la partida y de cada ficha del arreglo perteneciente a la partida, si partida finalizada? cuando finaliza?
		
		//update partida
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update partidas set turno=? where idPartida=?");
			
			stmt.setBoolean(1, part.getTurno());
			stmt.setInt(2, part.getIdPartida());
			stmt.execute();			
			
		}
		
		catch(SQLException e){
			
			e.printStackTrace();	
		}
		finally{
			
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				
			} catch(SQLException e){
				
				e.printStackTrace();
				
			}
			finally{
				
				FactoryConexion.getInstancia().releaseConn();
				
			}
			
		}
		
		for (Ficha f : part.getFichas()) {
			
			datFic.actualizarFicha(f, part);
			
		}

		return true;
	}

}


