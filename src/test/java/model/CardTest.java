package model;
import org.junit.Test;
import static org.junit.Assert.*;
import data.*;

/**La classe TesseraTest verifica il corretto funzionamento della classe Tessera
 * 
 * @author  Alessandro
 */
public class CardTest implements Constants{
	
	/**
	 * @uml.property  name="tessera"
	 * @uml.associationEnd  
	 */
	Card tessera;
	
	@Test
	/**
	 * Viene testata l'inizializzazione di una nuova Tessera
	 */
	public void testCreaTessera() {
		tessera=new Card("N=S S=N O=S E=C NS=0 NE=0 NO=1 OE=0 SE=1 SO=0");
		assertSame("Dovrebbe tornare strada=0", 0 , tessera.getSide(0).getSideKind());
		assertSame("Dovrebbe tornare niente=2", 2 , tessera.getSide(1).getSideKind());
		assertSame("Dovrebbe tornare strada=0", 0 , tessera.getSide(2).getSideKind());
		assertSame("Dovrebbe tornare città=1", 1 , tessera.getSide(3).getSideKind());
		
		assertSame("Dovrebbe tornare false", false , tessera.getLink(0));
		assertSame("Dovrebbe tornare true", false , tessera.getLink(1));
		assertSame("Dovrebbe tornare true", true , tessera.getLink(2));
		
		
	}
	
	@Test
	/**
	 * Viene testata la funzionalità ruota tessera
	 */
	public void testRuotaTessera() {
	
		tessera=new Card("N=S S=N O=S E=C NS=0 NE=0 NO=1 OE=0 SE=1 SO=0");
		
		
		for(int i=0; i<4;i++) {
			tessera.rotateCard();
		}
		
		assertSame("Dovrebbe tornare strada=0", 0 , tessera.getSide(0).getSideKind());
		assertSame("Dovrebbe tornare niente=2", 2 , tessera.getSide(1).getSideKind());
		assertSame("Dovrebbe tornare strada=0", 0 , tessera.getSide(2).getSideKind());
		assertSame("Dovrebbe tornare città=1", 1 , tessera.getSide(3).getSideKind());
		
		
		
		assertSame("Dovrebbe tornare false", false , tessera.getLink(0));
		assertSame("Dovrebbe tornare true", false , tessera.getLink(1));
		assertSame("Dovrebbe tornare true", true , tessera.getLink(2));
		
	}
	
	
	@Test
	/**
	 * testa la stampa della tessera
	 */
	public void testStampa() {
		
		tessera=new Card("N=S S=N O=S E=C NS=0 NE=0 NO=1 OE=0 SE=0 SO=0");
		tessera.getSide(NORTH).putPiece(new Player(0));
		
		String[] result = {"S1(R)", "  ", "S1", "C1", null, null}; 
		
		for(int i=0;i<6;i++) {
			assertEquals("Errore nell'array di stringhe", result[i], tessera.string()[i]);
		}
	}
	


}
