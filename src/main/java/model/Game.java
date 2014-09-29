/**
 * 
 * La classe Gioco contiene tutta la logica di gioco e i dettagli
 * sulla partita e sul turno che si sta giocando. La classe Gioco comunica con
 * il controller attraverso lo scambio di un vettore di stringe contente le 
 * informazioni correnti del gioco.
 * 
 * 
 * @author 
 * @version 1.0
 */

package model;
import java.util.*;

import data.Constants;
import data.Coordinate;
import data.Methods;


public class Game implements Constants{

	private HashMap<Coordinate,Card> map = new HashMap<Coordinate,Card>();
	private ArrayList<Player> players = new ArrayList<Player>();

	private Deck deck = new Deck();
	private int minorColumn;
	private int majorColumn;
	private int minorRow;
	private int majorRow;

	private Player currentPlayer; 

	private Card currentCard;
	private int currentColumn;
	private int currentRow;




	/**
	 * 
	 * Metoto costruttore della classe Gioco,
	 * - Crea una nuova partita assegnandogli un nome
	 * - Estrae la prima casella dal mazzo e la inserisce nella mappa in (0,0)
	 * 
	 */
	public Game() {

		map.put(new Coordinate(0,0), deck.getInitialCard());
		minorColumn=0;
		majorColumn=0;
		minorRow=0;
		majorRow=0;
		currentPlayer=null;
		currentCard=null;

	}


	/**
	 * Ritorna il colore del giocatore che sta giocando
	 */
	public int getCurrentPlayer() {
		return currentPlayer.getColor();
	}

	/**
	 * Ritorna il numero di giocatori della partita 
	 */
	public int getPlayersNumber() {
		return players.size();
	}


	/**
	 * 
	 * @return ritorna l'oggetto del Giocatore corrente
	 */
	public Player getCurrentPlayerObject() {
		return currentPlayer;
	}

	/**
	 * Restituisce il punteggio del giocatore corrente
	 */
	public int getCurrentPlayerScore() {
		return currentPlayer.getScore();
	}

	/**
	 * Restituisce il numero di pedine disponibili del giocatore corrente
	 */
	public int getCurrentPlayerPiecesNumber() {
		return currentPlayer.getPieces();
	}

	/**
	 * 
	 * Metodo che inserisce un nuovo giocatore nella partita,
	 * 
	 * Controlla quanti giocatori sono gia' presenti nell'ArrayList
	 * 
	 * @return -1 se non e' stato possibile inserire un nuovo giocatore
	 * 		(si e' raggiunto il limite massimo di giocatori inseribili)
	 * @return idColore se il giocatore e' stato inserito correttamente
	 * 		seguendo la seguente logica:
	 * 		 * 0 =ROSSO 1 =BLU 2 =VERDE 3 =GIALLO 4 =NERO
	 * 
	 */
	public int insertPlayer() {

		if(players.size()==MAX_PLAYERS) {
			return -1;
		}
		else {
			players.add(new Player(players.size()));
			return players.size()-1;
		}

	}

	/**
	 * Imposta il prossimo giocatore che effettuera' un turno come giocatore 
	 * corrente
	 */
	public void setNextPlayer() {

		if(currentPlayer==null) {
			currentPlayer=players.get(0);
		}
		else {
			if(currentPlayer.getColor()==(players.size()-1)) {
				currentPlayer=players.get(0);
			}
			else {
				currentPlayer=players.get(currentPlayer.getColor()+1);
			}
		}
	}

	/**
	 * Imposta la tessera corrente estraendone una dal mazzo
	 */
	public void setCurrentCard() {

		currentCard=deck.getCard();

	}

	/**il metodo verifica se le tessere nel mazzo sono esaurite o no
	 * 
	 * @return il numero di tessere ancora nel mazzo
	 */
	public int getRemainingCards() {
		return deck.remanentCards();
	}

	/**
	 * Imposta la tessera corrente 
	 */
	public void setCurrentCard(Card tessera) {
		currentCard=tessera;
	}

	/**
	 * Imposta le coodinate correnti
	 */
	public void setCurrentCoordinate(int column, int row) {

		currentColumn=column;
		currentRow=row;

	}


	/**
	 * Verifica se la tessera corrente e' applicabile nell'intera
	 * mappa
	 */
	public boolean checkCurrentCardMapInsertability() {

		return isApplicable(currentCard);

	}

	/**
	 * Verifica l'inseribilita' della tesseraCorrente nella posizioneCorrente
	 */
	public boolean checkCurrentCardInsertability() {

		if(checkInsertabilityPosition(currentColumn,currentRow,currentCard)) {
			return true; }
		return false;

	}

	/**
	 * Inserisce la tessera corrente nella posizione corrente
	 */
	public void insertCurrentCard() {

		insertCard(currentColumn, currentRow, currentCard);
	}


	/**
	 * Metodo che verifica l'inseribilita' di una tessera in una determinata
	 * riga/colonna passata come paramento
	 * 
	 * @return true se la tessera e' posizionabile alle coordinate indicate,false altrimenti
	 * 
	 * 
	 */
	public boolean checkInsertabilityPosition(int column, int row, Card card) {

		/*
		 * contiene true se matcha, false se e' vuoto 
		 */
		Boolean[] arrayResults = new Boolean[SIDE_NUM];
		Coordinate[] arrayCoordinates = Methods.getNeighborsCoordinates(column, row);

		if(map.containsKey(new Coordinate(column,row))) {
			return false;
		}

		for(int i=0;i<SIDE_NUM;i++) {
			if(!map.containsKey(arrayCoordinates[i])) {
				arrayResults[i]=false; 
			}
			else {
				Card closeCard = map.get(arrayCoordinates[i]);
				if(closeCard.getSide(Methods.getOppositeSide(i)).getSideKind()==card.getSide(i).getSideKind()) {
					arrayResults[i]=true;
				}
				else {
					return false;
				}	
			}
		}		


		int voidSidesCounter = 0;
		for(int i=0;i<SIDE_NUM;i++) {
			if(!arrayResults[i]){
				voidSidesCounter++;
			}
		}

		if(voidSidesCounter==SIDE_NUM) {
			return false; }

		return true;

	}

	/**
	 * Metodo che verifica l'inseribilita' di una tessera sull'intera
	 * mappa. In caso di esito negativo @return false la tessera in oggetto
	 * deve essere scartata e il giocatore che sta giocando ha il diritto
	 * di pescare un'ulteriore tessera.
	 * 
	 */	
	private boolean isApplicable(Card card){

		for(int z=0;z<SIDE_NUM;z++){
			for(int i=minorRow-1;i<=majorRow+1;i++) {
				for(int j=minorColumn-1;j<=majorColumn+1;j++){
					Coordinate coordinata=new Coordinate(j,i);
					if(!map.containsKey(coordinata) && checkInsertabilityPosition(j,i,card)){
						return true; }
				}
			}
			card.rotateCard();
		}
		return false;
	}


	/**
	 * ruota di 90° la tessera corrente
	 */
	public void rotateCurrentCard() {
		currentCard.rotateCard();
	}

	/**
	 * Inserisce una tessera in una posizione e modifica i bordi
	 */
	public void insertCard(int column, int row, Card card) {

		if(column<minorColumn) {
			minorColumn--;
		}
		if(column>majorColumn) {
			majorColumn++;
		}
		if(row<minorRow) {
			minorRow--;
		}

		if(row>majorRow) {
			majorRow++;
		}

		map.put(new Coordinate(column, row), card);

	}


	/**
	 * Controlla l'esistenza di una pedina impostando le proprie coordinate di provenienza
	 * e il punto cardinale di provenienza
	 * 
	 * @return true se nel percorso non sono presenti pedine del Giocatore corrente,false altrimenti
	 */
	public boolean checkPiecePresence(int provenienceColumn, int provenienceRow, int provenienceCardinalPoint) {

		int arrivalPoint=Methods.getOppositeSide(provenienceCardinalPoint);
		int continueSideIndex;

		Coordinate[] coordinate = Methods.getNeighborsCoordinates(provenienceColumn, provenienceRow);
		Coordinate interestingCoordinate = coordinate[provenienceCardinalPoint];
		if(!map.containsKey(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()))) {
			return true;
		}

		Card cardUnderExamination = map.get(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()));

		if(currentCard.equals(cardUnderExamination)) {
			return true;
		}

		if(cardUnderExamination.getSide(arrivalPoint).containsPlayerPiece(currentPlayer.getColor())) {
			return false;
		}
		else {
			ArrayList<Integer> activeLinks = cardUnderExamination.getLinkFromSide(arrivalPoint);
			if(activeLinks.size()==0) {
				return true;
			}
			ArrayList<Boolean> control = new ArrayList<Boolean>();
			for(Integer p: activeLinks) {
				continueSideIndex=Methods.getLinkedSide(p, arrivalPoint);
				if(cardUnderExamination.getSide(continueSideIndex).containsPlayerPiece(currentPlayer.getColor())) {
					return false;
				}
				else {
					control.add(checkPiecePresence(interestingCoordinate.getColumn(), interestingCoordinate.getRow(), continueSideIndex));
				}
			}

			for(Boolean p:control) {
				if(!p) {
					return false;
				}
			}
			return true;
		}

	}


	/**
	 * Verifica se e' inseribile una pedina nella tessera corrente, utilizzando la funzione situazioneInseribilitaPedina()
	 */
	public Boolean pieceInsertable() {

		Boolean[] result = pieceInsertionSituation();

		for(int i=0;i<SIDE_NUM;i++) {
			if(result[i] && currentPlayer.getPieces()>0) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Metodo che verifica su quali lati di tesseraCorrente e' possibile posizionare
	 * una pedina: utilizza la funzione controlloEsistenzaPedina
	 * @return un array di Boolean dove a ogni indice corrisponde o meno
	 * la possibilita' di inserire una pedina
	 */
	public Boolean[] pieceInsertionSituation()	{

		Boolean[] result = new Boolean[SIDE_NUM];

		for(int i=0; i<SIDE_NUM;i++) {
			if(currentCard.getSide(i).getSideKind()==VOID) {
				result[i]=false;
			}
			else {
				result[i]=checkPiecePresence(currentColumn, currentRow, i);
			}
		}

		for(int i=0; i<SIDE_NUM; i++) {

			if(result[i]) {
				ArrayList<Integer> activeLinksIndex = currentCard.getLinkFromSide(i);
				if(activeLinksIndex.size()!=0) {
					for(Integer p: activeLinksIndex) {
						int continueSideIndex=Methods.getLinkedSide(p, i);
						if(!result[continueSideIndex]) {
							result[i]=false;
							break;
						}
					}
				}
			}

		}
		return result;
	}	


	/**
	 * Inserisce une pedina nel punto cardinale indicato
	 */
	public void insertPiece(int puntoCardinale) {

		currentCard.getSide(puntoCardinale).putPiece(currentPlayer);

	}


	/**
	 * Controlla la terminazione di una strada o di una citta'
	 * Questa funzione e' ricorsiva, utilizza un oggetto di tipo AssegnatorePunti.
	 * 
	 * @param provenienceColumn indica la colonna di provenienza 
	 * @param riga di provenienza indica la riga di provenienza
	 * @param puntoCardinaleDi Provenienza indica il punto cardinale da cui si proviene (NORD SUD OVEST EST)
	 * 
	 * @return un oggetto di tipoAssegnatore Punti che contiene i lati che congono pedine e le tessere attraversate
	 * 
	 */
	public PointsAllocator checkFinishObject(int provenienceColumn, int provenienceRow, int provenienceCardinalPoint) {


		PointsAllocator pointsAllocator = new PointsAllocator();

		int puntoDiArrivo=Methods.getOppositeSide(provenienceCardinalPoint);
		int continueSideIndex;

		// controlla se la tessera dove sono arrivato esiste, se non esiste ritorna false, altrimenti ne crea un puntatore
		Coordinate[] coordinate = Methods.getNeighborsCoordinates(provenienceColumn, provenienceRow);
		Coordinate interestingCoordinate = coordinate[provenienceCardinalPoint];
		if(!map.containsKey(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()))) {
			pointsAllocator.setState(false);
			return pointsAllocator;
		}


		Card cardUnderExamination = map.get(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()));

		if(cardUnderExamination.getSide(puntoDiArrivo).containsPiece()) {
			pointsAllocator.insertSide(cardUnderExamination.getSide(puntoDiArrivo));
		}

		// se la tessera da cui sono partito e' la stessa in cui sono arrivato vuol dire che ho terminato il percorso
		if(currentCard==cardUnderExamination) {
			pointsAllocator.setState(true);
			return pointsAllocator;
		}
		
		pointsAllocator.insertCard(cardUnderExamination);
		

		// vado a cercare i collegamenti attivi dal lato a un altro lato
		ArrayList<Integer> activeLinks = cardUnderExamination.getLinkFromSide(puntoDiArrivo);
		// se non ci sono collegamenti la strada e' finita e ritorno true
		if(activeLinks.size()==0) {
			pointsAllocator.setState(true);
			return pointsAllocator;
		}
		// se arrivo qui vuol dire che ci sono collegamenti, per ogni lato collegato eseguo l'operazione
		ArrayList<PointsAllocator> control = new ArrayList<PointsAllocator>();
		for(Integer p: activeLinks) {
			continueSideIndex=Methods.getLinkedSide(p, puntoDiArrivo);
			if(cardUnderExamination.getSide(continueSideIndex).containsPiece()) {
				pointsAllocator.insertSide(cardUnderExamination.getSide(continueSideIndex));
			}
			control.add(checkFinishObject(interestingCoordinate.getColumn(), interestingCoordinate.getRow(), continueSideIndex));
		}
		for(PointsAllocator p:control) {
			pointsAllocator.addCards(p.getList());
			pointsAllocator.addSides(p.getLatoList());

			if(!p.getState()) {
				pointsAllocator.setState(false);
				return pointsAllocator;

			}
		}
		pointsAllocator.setState(true);
		return pointsAllocator;
	}

	/**se un percorso e' terminato si attribuiscono i punti al Giocatore col magior numero di pedine
	 * nel percorso
	 * 
	 */
	public Boolean assignPoints() {

		PointsAllocator[] verifier = new PointsAllocator[SIDE_NUM];

		for(int i=0; i<SIDE_NUM;i++) {
			if(currentCard.getSide(i).getSideKind()==VOID) {
				verifier[i]=new PointsAllocator();
			}
			else {
				verifier[i]=checkFinishObject(currentColumn,currentRow,i);
			}
		}

		for(int i=0; i<SIDE_NUM;i++) {
			ArrayList<Integer> activeLinksIndex = currentCard.getLinkFromSide(i);
			verifier[i].insertCard(currentCard);

			if(currentCard.getSide(i).containsPiece()) {
				verifier[i].insertSide(currentCard.getSide(i));
			}


			for(Integer p : activeLinksIndex) {
				int continueSideIndex=Methods.getLinkedSide(p, i);
				if(!verifier[continueSideIndex].getState()) {
					verifier[i].setState(false); }
				else {
					verifier[i].addCards(verifier[continueSideIndex].getList());
					verifier[i].addSides(verifier[continueSideIndex].getLatoList());
				}
			}

		}

		Boolean isFinished=false;
		for(int i=0; i<SIDE_NUM; i++) {
			if(verifier[i].getState()) {
				verifier[i].assignPoints(currentCard.getSide(i).getSideKind(), players);
				isFinished=true;
			}
		}

		return isFinished;

	}

	/**il metodo fa tutte le verifiche finali sulla mappa e controlla lo stato dei percorsi e la presenza
	 * di pedine su di essi.tutte queste operazioni vengono ritornate per permettere il corretto assegnamento
	 * dei punti
	 * 
	 * @param provenienceColumn e' la colonna riferita alla tessera dalla quale il giocatore
	 * proviene
	 * @param provenienceRow e' la riga riferita alla tessera dalla quale il giocatore
	 * proviene
	 * @param provenienceCardinalPoint e' il punto cardinale sulla tessera che viene analizzato
	 * @param provenienceCard tessera sulla quale stiamo facendo i vari controlli
	 * @return un oggetto AssegnatorePunti dove vengono salvate tutte le informazioni relative al 
	 * completamento dei percorsi e le pedine presenti su di essi
	 */
	public PointsAllocator finalOperations(int provenienceColumn, int provenienceRow, int provenienceCardinalPoint, Card provenienceCard) {


		PointsAllocator pointsAssigner = new PointsAllocator();

		int arrivalPoint=Methods.getOppositeSide(provenienceCardinalPoint);
		int continueIndex;

		Coordinate[] coordinate = Methods.getNeighborsCoordinates(provenienceColumn, provenienceRow);
		Coordinate interestingCoordinate = coordinate[provenienceCardinalPoint];
		if(!map.containsKey(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()))) {
			return pointsAssigner;
		}

		Card cardUnderExam = map.get(new Coordinate(interestingCoordinate.getColumn(),interestingCoordinate.getRow()));

		if(cardUnderExam.getSide(arrivalPoint).containsPiece()) {
			pointsAssigner.insertSide(cardUnderExam.getSide(arrivalPoint));
		}


		if(provenienceCard==cardUnderExam) {
			return pointsAssigner;
		}

		pointsAssigner.insertCard(cardUnderExam);

		ArrayList<Integer> activeLinks = cardUnderExam.getLinkFromSide(arrivalPoint);
		if(activeLinks.size()==0) {
			pointsAssigner.setState(true);
			return pointsAssigner;
		}
		ArrayList<PointsAllocator> control = new ArrayList<PointsAllocator>();
		for(Integer p: activeLinks) {
			continueIndex=Methods.getLinkedSide(p, arrivalPoint);
			if(cardUnderExam.getSide(continueIndex).containsPiece()) {
				pointsAssigner.insertSide(cardUnderExam.getSide(continueIndex));
			}
			control.add(checkFinishObject(interestingCoordinate.getColumn(), interestingCoordinate.getRow(), continueIndex));
		}
		for(PointsAllocator p:control) {
			pointsAssigner.addCards(p.getList());
			pointsAssigner.addSides(p.getLatoList());
		}
		return pointsAssigner;
	}


	/**inserisce in una stringa tutte le informazioni sulla tessera da passare alla mappa 
	 * 
	 * @return una stringa contenente le informazioni relative ogni tessera sulla mappa
	 */
	public ArrayList<String[]> returnStringedMap() {

		ArrayList<String[]> mapData = new ArrayList<String[]>();

		for(int i=minorRow;i<=majorRow;i++) {
			for(int j=minorColumn;j<=majorColumn;j++) {
				if(map.containsKey(new Coordinate(j,i))) {
					String[] temporary = map.get(new Coordinate(j,i)).string();
					temporary[COLUMN]=""+j;
					temporary[ROW]=""+i;
					mapData.add(temporary);
				}
			}
		}

		return mapData;
	}

	/**
	 * 
	 * @return una stringa contenente tutte le informazioni relative alla 
	 * TesseraCorrente
	 */
	public String[] riturnStringedCard() {

		return currentCard.string();

	}


	/**
	 * Assegna i punti finali prima di terminare il gioco poiche'
	 * le tessere disponibili sono finite
	 * in teoria dovrebbe chiamare per ogni lato verificaTerminazioneOggetto senza dare importanza se lo stato e' false
	 */
	public void assignFinalPoints() {
		int k;
		PointsAllocator[] verifier = new PointsAllocator[SIDE_NUM];

		for(int j=minorRow-1;j<=majorRow+1;j++) {
			for(int i=minorColumn-1;i<=majorColumn+1;i++) {

				if(map.containsKey(new Coordinate(i,j))) {
					for(k=0;k<SIDE_NUM;k++) {
						verifier[k]=finalOperations(i,j,k, map.get(new Coordinate(i,j)));
						verifier[k].insertCard(map.get(new Coordinate(i,j)));
						if(map.get(new Coordinate(i,j)).getSide(k).containsPiece()) {
							verifier[k].insertSide(map.get(new Coordinate(i,j)).getSide(k));
						}
					}
					for(k=0;k<SIDE_NUM;k++) {
						ArrayList<Integer> activeLinks = map.get(new Coordinate(i,j)).getLinkFromSide(k);
						for(Integer p : activeLinks) {
							int continueSideIndex=Methods.getLinkedSide(p, k);
							verifier[k].addCards(verifier[continueSideIndex].getList());
							verifier[k].addSides(verifier[continueSideIndex].getLatoList());
						}
						int points=0;
						if(map.get(new Coordinate(i,j)).getSide(k).getSideKind()==2) {
							points = 2;
						}
						verifier[k].assignPoints(points, players);
					}

				}
			}


		}
	}


}



