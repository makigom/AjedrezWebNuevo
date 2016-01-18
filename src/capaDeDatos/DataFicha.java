package capaDeDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import appExceptions.ApplicationException;
import capaDeEntidades.*; 

public class DataFicha
{
	public DataFicha() {
		
	}
	
	DataPosicion datPos = new DataPosicion();
	
	public ArrayList<Ficha> buscarFichas(int idPartida){
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Ficha> fichas = new ArrayList<Ficha>();
		Ficha fic = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select fichas.idFicha, estadoFicha, color, tipoFicha from fichas inner join posiciones on fichas.idFicha = posiciones.idFicha inner join partidas on partidas.idPartida = posiciones.idPartida where partidas.idPartida = ? " );
			
			stmt.setString(1, String.valueOf(idPartida));
			rs = stmt.executeQuery();
			
			while(rs !=null && rs.next()){
				String color = rs.getString("color"); 
				int idFicha = rs.getInt("idFicha");
				boolean estadoFicha = rs.getBoolean("estadoFicha");				
				char tipoFicha = rs.getString("tipoFicha").charAt(0);
				
				switch (tipoFicha) {
					case 'A': fic = new Alfil(idFicha, color, estadoFicha);
					break;
					case 'C': fic = new Caballo(idFicha, color, estadoFicha);
					break;
					case 'D': fic = new Dama(idFicha, color, estadoFicha);
					break;
					case 'P': fic = new Peon(idFicha, color, estadoFicha);
					break;
					case 'R': fic = new Rey(idFicha, color, estadoFicha);
					break;
					case 'T': fic = new Torre(idFicha, color, estadoFicha);
					break;
				}
				
				fichas.add(fic);
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
		
		for (Ficha f : fichas) {
			
			f.setPosicion(datPos.buscarPosiciones(f.getIdFicha()));			
		}
		
		
		return fichas;
		
	}

	public void crearFicha(Ficha f, Partida p) throws ApplicationException{
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into fichas(estadoFicha, color, tipoFicha) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setBoolean(1, f.getEstado());
			stmt.setString(2, f.getColor());
			stmt.setString(3, String.valueOf(f.getTipoFicha()));
			stmt.execute();
			
			rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
				f.setIdFicha(rs.getInt(1)); 
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
		
		datPos.crearPosicion(f, p);
	}
	
	public void actualizarFicha(Ficha f, Partida p) throws ApplicationException{
		
		PreparedStatement stmt = null;
		
		try{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update fichas set estadoFicha=? where fichas.idFicha=?;");
			
			stmt.setBoolean(1, f.getEstado());
			stmt.setInt(2, f.getIdFicha());
			
			stmt.execute();
			
		} catch(SQLException e){
			
			throw new ApplicationException("Error al actualizar ficha", e);
			
		}
		finally{
			
			try{
				
				if(stmt != null) stmt.close();
				
			} catch (SQLException e){
				
				e.printStackTrace();
				
			}
			
			FactoryConexion.getInstancia().releaseConn();
			
		}
		
		datPos.actualizarPosicion(f, p);
		
	}
		
}
