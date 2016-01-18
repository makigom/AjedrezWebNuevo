package capaDeNegocio;

import capaDeEntidades.*;
import java.util.ArrayList;
import appExceptions.ApplicationException;
import capaDeDatos.*;

public class CtrlPartida {
	
	private DataPartida datosPartida;
	public CtrlPartida(){
		
		datosPartida = new DataPartida();
	}
	
	Partida partida = new Partida(); 

	public Partida recuperarPartida(String dni1, String dni2) throws ApplicationException {
		
		partida = datosPartida.buscarPartida(dni1,dni2);		
		if(partida == null){ 
			
			partida = datosPartida.crearPartida(dni1, dni2);

		}
		return partida;
	}

	public boolean validarJugada(Posicion posOrigen, Posicion posDestino, Partida part) {
		
		String color;
		if(part.getTurno()) color = "B";
		else color = "N";
		
		//recorre todo el arreglo fichasPartida en busca de la ficha que se encuentre en posOrigen, si la encuentra, el mov es valido, la ficha no esta  y esa ficha corresponde a ese turno, hace los cambios y retorna true
		for (Ficha f : part.getFichas()) {
			if (f.getPosicion().getLetra() == posOrigen.getLetra()&& f.getPosicion().getNumero() == posOrigen.getNumero() && f.getEstado()==true && f.getColor().equals(color)) {
				if (f.getTipoFicha() != 'P') {
					if(f.validarMovimiento(posOrigen, posDestino)){
						for (Ficha fic : part.getFichas()) {
							if(fic.getPosicion().getLetra() == posDestino.getLetra() && fic.getPosicion().getNumero() == posDestino.getNumero()){
								fic.setEstado(false); //No se considera si come una ficha propia o no
								break;
							}
						}
						f.setPosicion(posDestino);
						return true;
					}
				}
				else {
					if(f.validarMovimiento(posOrigen, posDestino, color)){
						for (Ficha fic : part.getFichas()) {
							if(fic.getPosicion().getLetra() == posDestino.getLetra() && fic.getPosicion().getNumero() == posDestino.getNumero()) {
								fic.setEstado(false); //No se considera si come una ficha propia o no								
								break;
							}
						}
						f.setPosicion(posDestino);
						return true;
					}
				}
				
				
			}
		}
	
		return false;
		
	}

	public boolean guardarPartida(Partida part) throws ApplicationException {
		// TODO Auto-generated method stub
		if(datosPartida.savePartida(part)) return true;
		else return false;
	}

}
