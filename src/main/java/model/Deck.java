/**
 *
 * La classe mazzo apre il file carcassonne.txt e estrae una riga alla volta.	 
 * Dopo aver creato un oggetto Casella la infila in un ArrayList
 *
 * @author 
 * @version 1.0
 */

package model;
import java.io.*;
import java.util.*;

import data.Constants;



public class Deck implements Constants{

	private ArrayList<Card> deck = new ArrayList<Card>();


	/**
	Metodo costruttore di Mazzo, legge dal file carcassonne.txt, 
	lancia eccezioni in caso di file non utilizzabile.
	 * 
	 */

	public Deck() {


		File file = new File(FILE_POSITION);

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				deck.add(new Card(line));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.print("Not found carcassonne.txt. Take a look of carcassonne.txt's position in Constants Interface");
		}

	}

	/**
	Questo metodo estrae la prima tesserache inizializza il gioco e che 
	si deve trovare obbligatoriamente nella prima posizione dell'arrayList
	 */
	public Card getInitialCard() {


		Card returnedCard=deck.get(0);
		deck.remove(0);
		return returnedCard;
	}

	/**
	Questo metodo estrae casualmente una tessera dal mazzo e la restituisce
	al chiamante, in caso di errore termina il programma.
	 */
	public Card getCard() {

		Random in=new Random();
		int cardIndex;

		try {
			cardIndex=in.nextInt(deck.size());
			Card returnedCard=deck.get(cardIndex);
			deck.remove(cardIndex);
			return returnedCard;
		}
		catch (IndexOutOfBoundsException e) {
			return null;
		}

	}


	/**
	Ritorna il numero di tessere ancora presenti nell'ArrayList
	 */
	public int remanentCards() {
		return deck.size();
	}

}
