/**
 * La classe Methods contiene alcuni metodi statici che vengono utilizzati dalle classi.
 * 
 * @author
 * @version 1.0
 */

package data;



public class Methods implements Constants {


	/**ritorna il tipo di lato che viene passato come parametro:
	 * 0=strada,1=città,2=niente
	 * 
	 * @param side di cui vogliamo conoscere il tipo
	 * @return il tipo di lato come intero
	 */
	
	public static int getIndexSideType(char side) {

		switch(side) {

		case 'S': 
			return STREET;
		case 'C': 
			return CITY;
		case 'N': 
			return VOID;
		default: 
			return -1;
		}

	}
	
	/**ritorna il tipo di lato individuato da un carattere:
	 * S=strada,C=città,N=niente
	 * 
	 * @param name del lato di cui vogliamo il tipo
	 * @return il carattere che specifica il tipo di lato in esame
	 */
	public static char getNameSydeType(int name) {
		switch(name) {
		case STREET: return 'S';
		case CITY: return 'C';
		case VOID: return 'N';
		default: return ' ';
		}
	}
	
	/**
	 * 
	 * @param index e' una stringa che identifica il collegamento 
	 * @return l'intero che identifica il tipo di collegamento
	 */
	public static int getIndexLinkType(String index) {

		if(index.equals("NS")) {
			return NS; }
		if(index.equals("NE")) {
			return NE; }
		if(index.equals("NO")) {
			return NW; }
		if(index.equals("OE")) {
			return WE; }
		if(index.equals("SE")) {
			return SE; }
		if(index.equals("SO")) {
			return SW; }
		return -1;


	}
	
	/**
	 * 
	 * @param index e' un intero che identifica il tipo di collegamento
	 * @return una stringa associata al tipo di collegamento richiesto
	 */
	public static String getLinkName(int index) {
		
		switch(index) {
		case NS: return NORDSUD;
		case NE: return NORDEST;
		case NW: return NORDOVEST;
		case WE: return OVESTEST;
		case SE: return SUDEST;
		case SW: return SUDOVEST;
		default: return NOTHING;
		}
		
	}

	/**ritorna il nome del giocatore che viene passato come parametro
	 * 
	 * @param index che identifica di quale giocatore si vuole il nome
	 * @return il carattere che specifica il giocatore
	 */
	public static char getPlayerGame(int index) {

		switch(index) {

		case RED: return 'R';
		case BLUE: return 'B';
		case GREEN: return 'V';
		case YELLOW: return 'G';
		case BLACK: return 'N';
		default: return '0';

		}
	}
	
	/**il metodo ritorna al chiamante il nome per intero del giocatore
	 * 
	 * @param index e' l'identificativo numerico del giocatore
	 * @return il nome in forma di stringa di caratteri del giocatore
	 */
	public static String getPlayerLongName(int index) {

		switch(index) {

		case RED: return "Rosso";
		case BLUE: return "Blu";
		case GREEN: return "Verde";
		case YELLOW: return "Giallo";
		case BLACK: return "Nero";
		default: return "0";

		}
	}
	
	
	/**ritorna un carattere che identifica il lato su cui ci troviamo:
	 * 0=nord,1=sud,2=ovest,3=est
	 * 
	 * @param index del lato
	 * @return carattere riferito al lato passato come indice
	 */
	public static char getSideName(int index) {
		switch(index) {
		case NORTH: return 'N';
		case SOUTH: return 'S';
		case WEST: return 'W';
		case EAST: return 'E';
		default: return '0';
		}
	}

	/**ritorna il lato opposto rispetto a quello passato come parametro
	 * 
	 * @param side
	 * @return un intero che identifica il lato opposto al lato dato
	 */
	public static int getOppositeSide(int side) {

		
		
		switch(side) {

		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case WEST: return EAST;
		case EAST: return WEST;
		default: return -1;

		}
		
		
	}

	/**ritorna tutti i possibili collegamenti che ci possono essere dal lato passato come
	 * parametro
	 * 
	 * @param side
	 * @return array di interi dove ogni cella identifica un possibile collegamento
	 */
	public static Integer[] linkedSides(int side) {

		switch(side) {

		case NORTH: 
			return new Integer[] {NS,NE,NW};
		case SOUTH:
			return new Integer[] {NS,SE,SW};
		case WEST:
			return new Integer[] {NW,WE,SW};
		case EAST:
			return new Integer[] {NE,WE,SE};
		default:
			return new Integer[] {null, null, null};

		}

	}

	/**
	 * Riceve come parametri una colonna e una riga e restituisce un array
	 * contentente le coordinate del vicini secondo la classica disposizione
	 * NORD SUD OVEST EST
	 */
	public static Coordinate[] getNeighborsCoordinates(int column, int row) {

		Coordinate[] arrayCoordinate = new Coordinate[SIDE_NUM];
		arrayCoordinate[NORTH] =new Coordinate(column, row+1);
		arrayCoordinate[SOUTH] = new Coordinate(column, row-1);
		arrayCoordinate[WEST] = new Coordinate(column-1, row);
		arrayCoordinate[EAST] = new Coordinate(column+1, row);

		return arrayCoordinate;

	}

	/**
	 * 
	 * @param link 
	 * @param cardinalPoint di partenza del quale vogliamo conoscere il lato collegato
	 * @return un intero che identifica il lato che e' collegato al punto cardinale dato
	 */
	public static int getLinkedSide(int link, int cardinalPoint) {

		switch(link) {

		case NS: 
			if(cardinalPoint==NORTH) {
				return SOUTH; }
			return NORTH;
		case NE: 
			if(cardinalPoint==NORTH) {
				return EAST; }
			return NORTH;
		case NW: 
			if(cardinalPoint==NORTH) {
				return WEST; }
			return NORTH;
		case WE: 
			if(cardinalPoint==WEST) {
				return EAST; }
			return WEST;
		case SE:
			if(cardinalPoint==SOUTH) {
				return EAST; }
			return SOUTH;
		case SW: 
			if(cardinalPoint==SOUTH) {
				return WEST; }
			return SOUTH;
		default: return -1;
		}


	}
	

	
	

	

}