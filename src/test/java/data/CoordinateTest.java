package data;
import org.junit.Test;

import data.Coordinate;
import static org.junit.Assert.*;
/**la Classe CoordinataTest testa il corretto funzionamento dela Classe Coordinata
 * 
 * @author giulialarosa
 *
 */
public class CoordinateTest {

	@Test
	/**
	 * Viene testata la funzionalità HashCode della classe coordinata
	 */
	public void testHashCode() {
		
		Coordinate coordinata1 = new Coordinate(-1, 1);
		Coordinate coordinata2 = new Coordinate(-1 ,1);
	
		assertSame("Dovrebbe tornare hashcode=30", 30 , coordinata1.hashCode());
		assertSame("Dovrebbe tornare true utilizzando hashCode", coordinata1.hashCode(), coordinata2.hashCode());
		
		assertTrue("Dovrebbe tornare true utilizzando equals", coordinata1.equals(coordinata2));
	
		for(int i=-100;i<100 && i!=-1;i++) {
			for(int j=-100;j<100 && j!=1;j++) {				
				coordinata2 = new Coordinate(i ,j);
				assertFalse("Nessun hash deve essere uguale, altrimenti c'è collisione", coordinata1.hashCode()==coordinata2.hashCode());
			}
		}
	
	
	}
	
	@Test
	/**
	 * viene testata la funzionalità equals di Coordinata
	 */
	public void testEquals() {
		
		Coordinate coordinata1 = new Coordinate(0, 0);
		Coordinate coordinata2 = new Coordinate(0 ,0);
		assertTrue("", coordinata1.equals(coordinata2));
	}
	
	
}
