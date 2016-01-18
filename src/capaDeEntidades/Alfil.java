package capaDeEntidades;

public class Alfil extends Ficha {
	public Alfil(int id, String c, boolean b){
		this.setIdFicha(id);
		this.setColor(c);
		this.setEstado(b);
		this.setTipoFicha('A');
	}
	
	public Alfil(char i, int j, String c, boolean b, int id) {
		this.posicion = new Posicion();
		this.posicion.setNumero(j);
		this.posicion.setLetra(i);
		this.setColor(c);
		this.setEstado(b);
		this.setIdFicha(id);
		this.setTipoFicha('A');
	}

	@Override
	public boolean validarMovimiento(Posicion ini, Posicion fin) {

		//falta validar que si en su camino se encuentra otra pieza, no puede moverse mas alla de esta
		if ((ini.getLetra() != fin.getLetra()) && (ini.getNumero() != fin.getNumero()) && validarTablero(fin)) return true;	
		else return false;
	
	}



}
