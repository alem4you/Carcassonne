/** la classe Giocatore memorizza tutte le informazioni riferite a ogni
 * giocatore e i metodi per modificarle
 * 
 * l'identificativo di ogni giocatore e' rappresentato dal colore che e'
 * cosi' definito
 * 0 =ROSSO
 * 1 =BLU
 * 2 =VERDE
 * 3 =GIALLO
 * 4 =NERO
 * 
 * @author
 * @version  1.0
 */

package model;

import data.Constants;

public class Player implements Constants{


	private final int color;//rosso=0;blu=1;verde=2;giallo=3;nero=4;
	private int score;
	private int pieces;


	/** metodo Costruttore della classe Giocatore
	 * 
	 * @param color del nuovo Giocatore da inizializzare
	 */
	public Player(int color){
		this.color=color;
		this.pieces=MAX_PIECE;
		this.score=0;
	}

	/**metodo che ritorna il colore del giocatore corrente
	 * 
	 * @return colore del giocatore
	 */
	public int getColor(){
		return this.color;
	}

	/**metodo che ritorna il punteggio accumulato dal giocatore
	 * sul quale viene chiamato
	 * 
	 * @return punteggio del giocatore
	 */
	public int getScore(){
		return this.score;
	}

	/**
	 * metodo che ritorna il numero di pedine che sono ancora 
	 * posizionabili dal giocatore
	 * 
	 * @return numero pedine non posizionate
	 */
	public int getPieces(){
		return this.pieces;
	}

	/**
	 * metodo che incrementa di una unita' il numero di pedine
	 * che il giocatore ha ancora a disposizione
	 * 
	 */
	public void addPiece(){
		this.pieces++;
	}


	/**metodo che decrementa di una unita' il numero di pedine che
	 * il giocatore puo' ancora posizionare
	 * 
	 */
	public void subPiece(){
		this.pieces--;
	}


	/**
	 * il metodo incrementa il punteggio del giocatore corrente
	 * 
	 * @param points da aggiungere al punteggio precedente
	 */
	public void addPoints(int points) {
		score=score+points;
	}

}
