package model;
import org.junit.Test;
import static org.junit.Assert.*;

/**la classe LatoTest testa il funzionamento della classe Lato
 * 
 * @author 
 *
 */
public class SideTest {

	
	@Test
	/**
	 * testa se la pedina può essere inserita su un lato
	 */
	public void testInserisciPedina() {
		
		Side lato = new Side(2);
		Player giocatore = new Player(0);
		

		
		/**
		 * Provo a inserire una pedina in un lato Niente
		 */
		assertSame("Errore, deve restituire false perchè è un lato niente", false, lato.putPiece(giocatore));
		
		/**
		 * Provo a inserire una pedina normalmente
		 */
		lato = new Side(0);
		assertSame("Errore, deve restituire true perchè il lato è vuoto", true, lato.putPiece(giocatore));
		
		/**
		 * Provo a inserire una pedina in un lato che già ne contiene una
		 */
		assertSame("Errore, deve restituire false perchè il lato ha una pedina", false, lato.putPiece(giocatore));
		
		/**
		 * Consumo tutte le pedine di un giocatore e verifico che non può inserirne altre
		 */
		
		for(int i=0; i<6;i++) {
			
			lato = new Side(0);
			lato.putPiece(giocatore);
			
		}
		
		lato = new Side (0);
		assertSame("Errore, deve restituire false perchè non può più mettere pedine", false, lato.putPiece(giocatore));
		
	}
	
	@Test
	/**
	 * testa se la pedina èrimossa correttamente dal lato
	 */
	public void testRimuoviPedina() {
		Side lato = new Side(0);
		Player giocatore = new Player(0);
		/**
		 * Provo a rimuovere una pedina dove non esiste
		 */
		assertSame("Errore, pedina non esistente", false, lato.removePiece(giocatore));
		
		lato.putPiece(giocatore);
		
		assertTrue("Pedina presente", lato.removePiece(giocatore));
		
		lato.putPiece(giocatore);
		assertFalse("Non puoi inserire una pedina se è già presente", lato.putPiece(giocatore));
		
	}
	
	@Test
	/**
	 * controlla se un certo lato ha una pedina o no
	 */
	public void testLatoConPedina() {
		
		Side lato = new Side(1);
		assertFalse("Non ha una pedina", lato.containsPiece());
		
		assertSame("Non ha una pedina", -1, lato.getPlayerPiece());
		lato.putPiece(new Player(0));
		
		assertTrue("Ha una pedina", lato.containsPiece());
		assertSame("Pedina di 0",0,lato.getPlayerPiece());
		
	}
	
	
}
