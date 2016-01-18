package capaDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import appExceptions.ApplicationException;
import capaDeEntidades.*; 

public class DataPosicion {
	
	public DataPosicion(){
		
	}
	//preguntar del workbench, no nos toma la foreignKey de posiciones
	public Posicion buscarPosiciones(int idFicha){
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Posicion pos = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select letra, numero from posiciones where posiciones.idFicha = ? " );
			
			stmt.setString(1, String.valueOf(idFicha));
			rs = stmt.executeQuery();
			
			if(rs !=null && rs.next()){
				
				pos = new Posicion();
				pos.setLetra(rs.getString("letra").charAt(0));
				pos.setNumero(rs.getInt("numero"));
				
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
		
		return pos;
		
	}
	public void crearPosicion(Ficha f, Partida p) {

		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into posiciones(letra, numero, idFicha, idPartida) values (?,?,?,?)");
			
			stmt.setString(1, String.valueOf(f.getPosicion().getLetra()));
			stmt.setInt(2, f.getPosicion().getNumero());
			stmt.setInt(3, f.getIdFicha());
			stmt.setInt(4, p.getIdPartida());
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
		
	}
	public void actualizarPosicion(Ficha f, Partida p) throws ApplicationException {

		PreparedStatement stmt = null;
		
		try{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update posiciones set letra=?, numero=? where posiciones.idFicha=? and posiciones.idPartida=? ;");
			
			stmt.setString(1, String.valueOf(f.getPosicion().getLetra()));
			stmt.setInt(2, f.getPosicion().getNumero());
			stmt.setInt(3, f.getIdFicha());
			stmt.setInt(4, p.getIdPartida());			
			stmt.execute();

						
		} catch(SQLException e){
			
			e.printStackTrace();;
			//throw new ApplicationException("Error al actualizar posiciones", e);
			
		}
		finally{
			
			try{
				
				if(stmt != null) stmt.close();
				
			} catch (SQLException e){
				
				e.printStackTrace();
				
			}
			
			FactoryConexion.getInstancia().releaseConn();
			
		}
		
	}

}
