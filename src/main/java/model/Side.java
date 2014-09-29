/**la classe Lato identifica il tipo di lato e 
 * memorizza se c'e' o meno una pedina su quel lato
 * 
 * la variabile tipoLato puo' contenere i seguenti valori
 * 0 =Strada
 * 1 =Citta'
 * 2 =Niente
 * 
 * la variabile pedina contiene -1 se su quel lato non ci sono
 * pedine,mentre contiene l'ID del giocatore (il colore) se e' presente
 * una sua pedina su quel lato
 * 
 * @author Giulia
 * @version 1.0
 */

package model;

import data.Methods;

public class Side {

	private int sideKind;
	private int piece; 

	/**
	 * metodo Costruttore della Classe Lato
	 * 
	 * @param tipo del lato che deve essere inizializzato
	 */
	public Side (int sideKind) {
		this.sideKind=sideKind;
		piece=-1;
	}

	/**
	 * metedo che ritorna il tipo di lato
	 * 
	 * @return tipo del lato
	 */
	public int getSideKind() {
		return sideKind;
	}



	/**
	 * metodo che posiziona una pedina sul lato se questo e' di tipo
	 * strada o citta',se non vi sono altre pedine sul quel lato
	 * e se il giocatore ha ancora pedine posizionabili
	 * 
	 * @param player di cui vogliamo posizionare la pedina
	 * @return -1 se non e' stato possibile posizionare la pedina,1 altrimenti
	 */
	public boolean putPiece(Player player){
		if(sideKind==2 || player.getPieces()==0 || piece!=-1) {
			return false; }

		this.putPlayerPiece(player.getColor());
		player.subPiece();
		return true;

	}

	/**
	 * metodo che rimuove una pedina da un lato se quello ne conteneva una
	 * 
	 * @param player a cui vogliamo togliere la pedina
	 * @return false se non c'erano pedine su quel lato, true se la pedina e' stata rimossa
	 */
	public boolean removePiece(Player player){
		if(!this.containsPiece()) {
			return false; }
		if(this.getPlayerPiece()==player.getColor()){
			piece=-1;
			player.addPiece();
			return true;
		}
		return false;
	}

	/**
	 * metodo che ritorna se su un lato ci sono pedine o no
	 * 
	 * @return falso se sul lato non ci sono pedine,vero altrimenti
	 */
	public boolean containsPiece(){
		if(piece==-1) {
			return false; }

		return true; 
	}

	/**
	 * metodo che indica a quale giocatore e' associata una certa pedina
	 * 
	 * @return il colore del giocatore che possiede la pedina
	 */
	public int getPlayerPiece(){
		return piece;
	}

	/** 
	 * 
	 * Metodo che imposta una pedina su un lato
	 * E' private perche' puo' essere acceduto solo dall'interno della classe,
	 * dall'esterno si accede tramite inserisciPedinaGiocatore
	 * 	
	 * @param color del giocatore di cui vogliamo posizionare la pedina
	 */
	private void putPlayerPiece(int color){
		this.piece=color;
	}


	/** 
	 *
	 * Metodo che verifica la presenza di una pedina di un determinato
	 * giocatore
	 */
	public boolean containsPlayerPiece(int color) {

		if(piece==color) {
			return true;
		}

		return false;

	}

	/**metodo che restituisce il tipo di collegamento
	 * 
	 * @return una stringa dove e' indicato di che collegamento di tratta:NS,NE,NW,WE,SE,SW
	 */
	public String string() {
		return Methods.getLinkName(sideKind);
	}


}
