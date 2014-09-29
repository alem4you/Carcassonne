/**
 * L'interfaccia Costants contiene i riferimenti alle Costanti globali che vengono utilizzate nel gioco,
 * essa e' richiamata da quasi tutte le classi.
 */

package data;

public interface Constants {

	int NORTH = 0;
	int SOUTH = 1;
	int WEST = 2;
	int EAST = 3;

	String FILE_POSITION = "src/main/java/data/carcassonne.txt";
	
	int MAX_PIECE = 7;
	int SIDE_NUM= 4;
	int LINK_NUM= 6;
	int MAX_GIOCATORI= 5;
	int MIN_GIOCATORI= 2;
	
	int STREET = 0;
	int CITY = 1;
	int VOID = 2;

	int NS = 0;
	int NE = 1;
	int NW = 2;
	int WE = 3;
	int SE = 4;
	int SW = 5;
	
	int SIDENORD= 2;
	int SIDESUD= 6;
	int SIDEOVEST= 10;
	int SIDEEST= 14;
	
	int LINKNS= 19;
	int LINKNE= 24;
	int LINKNW= 29;
	int LINKWE= 34;
	int LINKSE= 39;
	int LINKSW= 44;
	
	String NORDSUD= "NS";
	String NORDEST= "NE";
	String NORDOVEST= "NW";
	String OVESTEST= "OE";
	String SUDEST= "SE";
	String SUDOVEST= "SW";
	String NOTHING= "";
	
	int MAX_PLAYERS = 5;
	int RED = 0;
	int BLUE = 1;
	int GREEN = 2;
	int YELLOW = 3;
	int BLACK = 4;
	
	int ZERO_LINE= 0;
	int UNO_LINE=1;
	int DUE_LINE= 2;
	int TRE_LINE= 3;
	int QUATTRO_LINE= 4;
	int CINQUE_LINE= 5;
	int SEI_LINE= 6;
	
	int POINTS_STREET = 1;
	int POINTS_CITY = 2;
	
	int COLOUMN_SIGN_POSITION = 30;
	int ROW_SIGN_POSITION = 36;
	int COLOUMN_POSITION = 31;
	int ROW_POSITION = 37;
	
	int INDEX_1_START = 2;
	int INDEX_1_STOP = 6;
	int INDEX_2_START = 9;
	int INDEX_2_STOP = 13;
	int INDEX_3_START = 16;
	int INDEX_3_STOP = 20;
	int INDEX_4_START = 23;
	int INDEX_5_STOP = 26;
	
	int COLUMN = 4;
	int ROW = 5;
	int LINE_NUMBER = 6;
	
	String ROW_POINTS_PLUS = "..............+";
	String RIGA_CANCELLETTI_PIU = "##############+";
	String RIGA_NIENTE = "               ";
	String ROW_VOID_POINT = "              .";
	String ROW_VOID_PLUS = "              +";
	String ROW_PLUS_POINTS_PLUS = "+.............+";
	String ROW_PLUS_VOID_POINTS = ".             .";
	
	int TIMEOUT_START = 5000;
	int TIMEOUT_CARD = 2500;

	
}