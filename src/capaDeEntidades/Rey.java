package capaDeEntidades;

public class Rey extends Ficha {
	public Rey(int id, String c, boolean b){
		this.setIdFicha(id);
		this.setColor(c);
		this.setEstado(b);
		this.setTipoFicha('R');
	}

	public Rey(char i, int j, String c, boolean b, int id) {
		this.posicion = new Posicion();
		this.posicion.setNumero(j);
		this.posicion.setLetra(i);
		this.setColor(c);
		this.setEstado(b);
		this.setIdFicha(id);
		this.setTipoFicha('R');
	}

	@Override
	public boolean validarMovimiento(Posicion ini, Posicion fin) {

		if (((char)ini.getLetra() == (char)fin.getLetra()) && (ini.getNumero() + 1 == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() == (char)fin.getLetra()) && (ini.getNumero() - 1 == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() + 1 == (char)fin.getLetra()) && (ini.getNumero() == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() - 1 == (char)fin.getLetra()) && (ini.getNumero() == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() + 1 == (char)fin.getLetra()) && (ini.getNumero() + 1 == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() + 1 == (char)fin.getLetra()) && (ini.getNumero() - 1 == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() - 1 == (char)fin.getLetra()) && (ini.getNumero() -1  == fin.getNumero()) && validarTablero(fin)) return true;
		else if (((char)ini.getLetra() - 1 == (char)fin.getLetra()) && (ini.getNumero() + 1 == fin.getNumero()) && validarTablero(fin)) return true;
		else return false;
		
	}


}
