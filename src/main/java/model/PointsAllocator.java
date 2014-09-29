/**la classe AssegnatorePunti gestisce l'assegnamento dei punti ai giocatori sia nel caso di fine percorso 
 * che di fine partita
 * 
 * @author
 * @version 1.0
 */

package model;

import java.util.*;

import data.Constants;

public class PointsAllocator implements Constants{


	private ArrayList<Card> crossedCards = new ArrayList<Card>();
	private Boolean isFinished;
	private ArrayList<Side> piecedCards = new ArrayList<Side>();
	private ArrayList<Integer> playerCounter = new ArrayList<Integer>();

	/**
	 * metodo Costruttore della classe:
	 * -inizializza le strutture dati necessarie
	 * 
	 */
	public PointsAllocator() {
		isFinished=false;
		crossedCards = new ArrayList<Card>();
		piecedCards = new ArrayList<Side>();
	}

	/** 
	 * inserisce una tessera nell'ArrayList se non e' gia' presente
	 * 
	 * @param card e' la tessera del percorso che vogliamo inserire nell'ArrayList
	 * @return false se la tessera e' gia' presente
	 * @return true se la tessera non era presente e ora e' stata inserita
	 */
	public boolean insertCard(Card card) {

		for(Card p: crossedCards) {
			if(card == p) {
				return false;
			}
		}
		crossedCards.add(card);
		return true;

	}
	/**
	 * inserisce un lato nell'ArrayList se non e' gia' presente
	 * 
	 * @param lato e' il lato con pedina che vogliamo inserire nell'ArrayList
	 * @return false se il lato in questione era gia' presente
	 * @return true se il lato non era presente ed e' stato inserito
	 */
	public boolean insertSide(Side lato) {

		for(Side p: piecedCards) {
			if(lato == p) {
				return false;
			}
		}
		piecedCards.add(lato);
		return true;

	}
	/**
	 * ritorna l'elenco di tutti i lati con pedine presenti nell'ArrayList
	 * 
	 * @return ArrayList lati con pedine
	 */
	public ArrayList<Side> getLatoList() {
		return piecedCards;
	}

	/** 
	 * setta lo stato di terminazione
	 * 
	 * @param valore e' un Booleano che indica se il percorso e' finito o no
	 */
	public void setState(Boolean valore) {
		isFinished=valore;
	}

	/** 
	 * prende lo stato
	 *
	 * @return ritorna lo stato del percorso:true se e' finito,false altrimenti
	 */
	public boolean getState() {
		return isFinished;
	}

	/**
	 * ritorna la lista delle tessere attraversate nel percorso
	 * 
	 * @return ArrayList delle tessere percorse
	 */
	public ArrayList<Card> getList() {
		return crossedCards;
	}

	/**
	 * aggiunge tutte le tessere passate come parametro nell'ArrayList delle tessere attraversate
	 * 
	 * @param cards da aggiungere nell'ArrayList
	 */
	public void addCards(ArrayList<Card> cards) {

		for(Card p : cards) {
			insertCard(p);
		}

	}

	/**
	 * aggiunge tutti i lati passati come parametro nell'ArrayList dei lati con pedina
	 * 
	 * @param side dove e' presente una pedina che vogliamo aggiungere all'ArrayList corrispondente
	 */
	public void addSides(ArrayList<Side> side) {
		for(Side p: side) {
			insertSide(p);
		}

	}

	/**
	 * dice qual'e' il numero di tessere presenti nell'ArrayList
	 * 
	 * @return la dimensione del percorso
	 */
	public int howManyCards() {
		return crossedCards.size();
	}

	/**
	 * confronta i contatori delle pedine dei Giocatori 
	 * 
	 * @return il numero di pedine del Giocatore che e' più presente in un determinato percorso 
	 */
	private int getMax() {
		int max=0;
		for(Integer p: playerCounter) {
			if(p>max) {
				max=p;
			}
		}
		return max;
	}

	/**
	 * il metodo assegna i punti al giocatore che risulta avere più pedine nel tipo
	 * di percorso indicato
	 * 
	 * @param sideKind indica di che percorso si tratta:0=strada,1=citta',2=niente
	 * @param players sono tutti i giocatori presenti nella partita
	 */
	public void assignPoints(int sideKind, ArrayList<Player> players) {

		Integer j;

		for(int i=0; i<players.size(); i++) {
			playerCounter.add(i, 0);
		}

		for(Side p : piecedCards) {
			if(p.containsPiece()) {
				j=playerCounter.get(p.getPlayerPiece());
				j++;
				playerCounter.remove(p.getPlayerPiece());
				playerCounter.add(p.getPlayerPiece(), j);
				p.removePiece(players.get(p.getPlayerPiece()));
			}
		}

		j=getMax();

		if(j>0) {

			int pointsNumber;

			if(sideKind==0) {
				pointsNumber=POINTS_STREET*crossedCards.size();
			}
			else {
				if(sideKind==2) {
					pointsNumber=0;
				}
				else {
					pointsNumber=POINTS_CITY*crossedCards.size();
				}
			}

			for(int i=0; i<playerCounter.size();i++) {
				if(playerCounter.get(i)==j) {
					players.get(i).addPoints(pointsNumber);
				}
			}

		}
	}

}
