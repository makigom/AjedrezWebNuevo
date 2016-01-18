package capaDeEntidades;

import java.util.ArrayList;

public class Partida 
{
	private int idPartida;
	private Jugador jugadorBlanco;
	private Jugador jugadorNegro;
	private boolean turno;
	private ArrayList<Ficha> fichas;

	public ArrayList<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(ArrayList<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Partida() {
		// TODO Auto-generated constructor stub
	}

	public Jugador getJugadorBlanco() {
		return jugadorBlanco;
	}

	public void setJugadorBlanco(Jugador jugadorBlanco) {
		this.jugadorBlanco = jugadorBlanco;
	}

	public Jugador getJugadorNegro() {
		return jugadorNegro;
	}

	public void setJugadorNegro(Jugador jugadorNegro) {
		this.jugadorNegro = jugadorNegro;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public boolean getTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}
		
}
