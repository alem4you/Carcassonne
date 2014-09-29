/**
 * la Classe CardParser si occupa della stampa delle informazioni
 * racchiuse in ogni tessera.Implementa l'interfaccia Costants 
 * 
 * @author 
 *@version 1.0
 */

package view;
import java.io.PrintStream;
import data.Constants;


public class CardParser implements Constants{

	String[] card;
	static PrintStream out = System.out;

	/**
	 * metodo costruttore della classe
	 * 
	 * @param cardToParse e' la tessera di cui vogliamo conoscere le informazioni
	 */
	public CardParser(String[] cardToParse) {

		card = cardToParse.clone();


	}

	/**
	 * stampa le info della tessera corrente
	 * 
	 */
	public void print() {

		for(int i=0;i<=LINE_NUMBER;i++) {

			switch(i) {

			case ZERO_LINE: case SEI_LINE: out.printf("%s", ROW_PLUS_POINTS_PLUS);
			break;
			case UNO_LINE: out.printf(".    %5s    .", card[NORTH]);
			break;
			case DUE_LINE: case QUATTRO_LINE: out.printf("%s", ROW_PLUS_VOID_POINTS);
			break;
			case TRE_LINE: out.printf(".%-5s   %5s.", card[WEST], card[EAST]);
			break;
			case CINQUE_LINE: out.printf(".    %5s    .", card[SOUTH]);
			break;
			default: out.printf("");
			break;

			}
			out.println("");

		}


	}



}
