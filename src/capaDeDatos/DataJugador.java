package capaDeDatos;

import java.sql.*;

import appExceptions.ApplicationException;
import capaDeEntidades.Jugador;

public class DataJugador {
	
	public DataJugador(){
		
	}

	public Jugador getByDni(String dni) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Jugador j = null;
		
		try{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select dni, nombre, apellido from jugadores where dni = ?");
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				j = new Jugador();
				j.setDni(rs.getString("dni"));
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
			}
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
		return j;
	}

	public void add(Jugador j) {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into jugadores(dni, nombre, apellido) values (?,?,?)");
			
			stmt.setString(1, j.getDni());
			stmt.setString(2, j.getNombre());
			stmt.setString(3, j.getApellido());
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

	public void update(Jugador newJug) throws ApplicationException {
		
		PreparedStatement stmt = null;
		
		try{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update jugadores set nombre=?, apellido=? where dni=?;");
			
			stmt.setString(1, newJug.getNombre());
			stmt.setString(2, newJug.getApellido());
			stmt.setString(3, newJug.getDni());
			
			stmt.execute();
			
		} catch(SQLException e){
			
			throw new ApplicationException("Error al actualizar datos de persona", e);
			
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
