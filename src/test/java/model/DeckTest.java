package model;

import static org.junit.Assert.*;
import org.junit.Test;

import data.Constants;

/**la Classe MazzoTest verifica il funzionamento della classe Mazzo
 * 
 * @author giulialarosa
 *
 */
public class DeckTest implements Constants {
		
	@Test
	/**
	 * Test del metodo che estrae la prima tessera da posizionare.
	 */
	public void testEstraiTesseraIniziale() {
		
		Deck mazzo = new Deck();
		Card initialCard = mazzo.getInitialCard();
		assertEquals("Errore", 2, initialCard.getSide(NORTH).getSideKind());
		assertEquals("Errore", 1, initialCard.getSide(SOUTH).getSideKind());
		assertEquals("Errore", 0, initialCard.getSide(WEST).getSideKind());
		assertEquals("Errore", 0, initialCard.getSide(EAST).getSideKind());
		
	}
	
	/**
	 * testa se vengono ritornate il numero corretto di tessere rimanenti nel mazzo
	 */
	public void testTessereRimanenti() {
		
		Deck mazzo = new Deck();
		assertEquals("Errore ci sono 52 tessere in un mazzo", 52, mazzo.remanentCards());
		mazzo.getInitialCard();
		assertEquals("Errore, una tessera è stata estratta, ci sono 51 tessere nel mazzo", 51, mazzo.remanentCards());
	}
	
	/**testa se una tessera è estratta correttamente dal mazzo
	 * 
	 */
	public void testEstraiTessera() {
		
		Deck mazzo = new Deck();
		for(int i=0; i<mazzo.remanentCards();i++) {
			mazzo.getCard();
		}
		
		assertEquals("Errore", null, mazzo.getCard());
	}
	
}
