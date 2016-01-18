package capaDeEntidades;

import java.util.ArrayList;

public abstract class Ficha{
	
	private int idFicha;
	private boolean estado;
	private String color;
	protected Posicion posicion; 
	private char tipoFicha;

	public char getTipoFicha() {
		return tipoFicha;
	}

	public void setTipoFicha(char tipoFicha) {
		this.tipoFicha = tipoFicha;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public boolean validarTablero(Posicion fin){
		
		if((fin.getNumero() <= 8) && (fin.getNumero()>=1) && (fin.getLetra() >= 'a') && (fin.getLetra() <= 'h') ) return true;
		else return false;	
		
	}
	
	public static ArrayList<Ficha> setearFichas(){ //resolver idFicha con tema de la base de datos, sino cuando se agregue una partida nueva se van a repetir ids y no va
	
		int id = 0;
		ArrayList<Ficha> fichas = new ArrayList<Ficha>();
		
		for (char i='a'; i <= 'h';i++){
			for(int j = 1; j <= 8; j++){
				//SETEAR FICHAS BLANCAS		
				if((i=='a' && j == 1) || (i=='h' && j==1)){					
					Torre t = new Torre(i, j, "B", true, id);
					fichas.add(t);
					id++;
				}
				
				else if((i=='b' && j == 1) || (i=='g' && j==1)){
					Caballo c = new Caballo(i, j, "B", true, id);
					fichas.add(c);
					id++;
				}
				
				else if((i=='c' && j == 1) || (i=='f' && j==1)){
					Alfil a = new Alfil(i, j, "B", true, id);
					fichas.add(a);
					id++;
				}
				
				else if((i=='d') && (j == 1)){
					Dama d = new Dama(i, j, "B", true, id);
					fichas.add(d);
					id++;
				}
				
				else if((i=='e') && (j == 1)){
					Rey r = new Rey(i, j, "B", true, id);
					fichas.add(r);
					id++;
				}
				
				else if((i>='a') && (i<='h') && (j == 2)){
					Peon p = new Peon(i, j, "B", true, id);
					fichas.add(p);
					id++;
				}
				
				//SETEAR FICHAS NEGRAS
				else if((i=='a' && j == 8) || (i=='h' && j==8)){					
					Torre t = new Torre(i, j, "N", true, id);
					fichas.add(t);
					id++;
				}
				
				else if((i=='b' && j == 8) || (i=='g' && j==8)){
					Caballo c = new Caballo(i, j, "N", true, id);
					fichas.add(c);
					id++;
				}
				
				else if((i=='c' && j == 8) || (i=='f' && j==8)){
					Alfil a = new Alfil(i, j, "N", true, id);
					fichas.add(a);
					id++;
				}
				
				else if((i=='d') && (j == 8)){
					Dama d = new Dama(i, j, "N", true, id);
					fichas.add(d);
					id++;
				}
				
				else if((i=='e') && (j == 8)){
					Rey r = new Rey(i, j, "N", true, id);
					fichas.add(r);
					id++;
				}
				
				else if((i>='a') && (i<='h') && (j == 7)){
					Peon p = new Peon(i, j, "N", true, id);
					fichas.add(p);
					id++;
				}
			}
		}
		return fichas;
	}
		
	public boolean validarMovimiento(Posicion ini, Posicion fin) {
		return false;
	}
	
	public boolean validarMovimiento(Posicion ini, Posicion fin, String color){
		return false;
	}
	
	public int getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
