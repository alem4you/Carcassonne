package view;

import org.junit.Test;

import model.Player;
import model.Game;
import model.Card;

/**la classe TestMappaParser verifica se la classe MappaParser funziona nel modo giusto
 * 
 * @author 
 *
 */
public class TestMappaParser {

	@Test
	/**
	 * testa se la mappa viene stampata correttamente
	 */
	public void testMappa() {
		
	Game gioco = new Game ();
	
	
	Card tesseraE = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1,0)
	Card tesseraF = new Card("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (1,0)
	Card tesseraG = new Card("N=C S=C W=C E=N NS=1 NE=0 NW=1 WE=0 SE=0 SW=1"); // (1,-1)
	Card tesseraH = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-2)		
	Card tesseraI = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-3)		
	Card tesseraL = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-4)
	
	
	gioco.insertCard(-1, 0, tesseraE);
	gioco.insertCard(1, 0, tesseraF);
	gioco.insertCard(1, -1, tesseraG);
	gioco.insertCard(-1, -1, tesseraH);
	gioco.insertCard(-1, -2, tesseraI);
	gioco.insertCard(-1, -3, tesseraL);
	tesseraH.getSide(0).putPiece(new Player(0));
	
	
	MapParser mappaParser = new MapParser(gioco.returnStringedMap());
	mappaParser.print();
	
}

}
