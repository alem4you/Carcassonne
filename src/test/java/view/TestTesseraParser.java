package view;

import org.junit.Test;

/**la classe TestTesseraParser verifica il corretto funzionamento della classe TesseraParser
 * 
 * @author giulialarosa
 *
 */
public class TestTesseraParser {

	@Test
	/**
	 * testa se le informazioni della tessera vengono stampate nel modo giusto
	 */
	public void TestStampa() {
		
		String[] tessera = new String[6];
		tessera[0] = "S1";
		tessera[1] = "S1(V)";
		tessera[2] = "C";
		tessera[3] = "  ";
		
		CardParser tesseraParser= new CardParser(tessera);
		tesseraParser.print();
		
		
	}
	
	
}
