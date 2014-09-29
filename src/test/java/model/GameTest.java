package model;
/**la classe GiocoTest testa il funzionamento della classe Gioco
 * 
 */

import org.junit.Test;

import data.Constants;
import static org.junit.Assert.*;

public class GameTest implements Constants {

	/*


	@Test
	/**testa il corretto funzionamento dell'inserimento dei giocatori
	 * 
	 */
	public void TestInserisciGiocatore() {

		Game gioco = new Game();

		assertSame("Nessun giocatore, deve tornare 0, primo giocatore", 0, gioco.insertPlayer());
		gioco.insertPlayer();
		gioco.insertPlayer();
		gioco.insertPlayer();
		gioco.insertPlayer();
		assertSame("Giocatori pieni, deve tornare -1, ", -1, gioco.insertPlayer());


	}

	@Test
	/**testa se una tessera è inseribile in un determinata posizione
	 * 
	 */
	public void TestVerificaInseribilitaPosizione() {

		Game gioco = new Game();
		assertFalse("Deve tornare false, (0,0) è già stata inserita", gioco.checkInsertabilityPosition(0, 0, new Card("N=C S=C O=N E=N NS=1 NE=0 NO=0 OE=0 SE=0 SO=0")));
		assertTrue("Deve tornare vero, sopra c'è erba", gioco.checkInsertabilityPosition(0, 1, new Card("N=N S=N W=C E=C NS=0 NE=0 NW=0 WE=1 SE=0 SW=0")));
		assertFalse("Deve tornare falso, soprà c'è strada", gioco.checkInsertabilityPosition(0, 1, new Card("N=C S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0")));
		assertTrue("Deve tornare vero, a ovest c'è strada", gioco.checkInsertabilityPosition(1, 0, new Card("N=C S=S W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0")));
		assertTrue("Deve tornare vero, a est c'è strada", gioco.checkInsertabilityPosition(-1, 0, new Card("N=C S=S W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0")));
		assertTrue("Deve tornare vero, a sud c'è città", gioco.checkInsertabilityPosition(0, -1, new Card("N=C S=C W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0")));
		assertFalse("Deve tornare false, non presente in mappa", gioco.checkInsertabilityPosition(10, -10, new Card("N=C S=C W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0")));


	}

	@Test
	/**testa se viene impostato correttamente il prossimo giocatore del turno
	 * 
	 */
	public void TestImpostaProssimoGiocatore() {
		Game gioco = new Game();
		gioco.insertPlayer();
		gioco.insertPlayer();
		gioco.setNextPlayer();
		assertSame("Nessun giocatore, deve tornare 0, primo giocatore", 0, gioco.getCurrentPlayer());
		gioco.setNextPlayer();
		assertSame("Nessun giocatore, deve tornare 1, secondo giocatore", 1, gioco.getCurrentPlayer());
		gioco.setNextPlayer();


	}


	@Test
	/**testa se la tessera pescata è inseribile nella mappa
	 * 
	 */
	public void TestTesseraApplicabileInMappa() {

		Game gioco = new Game();
		gioco.setCurrentCoordinate(0, 1);
		gioco.setCurrentCard();

		assertTrue("Deve essere vero", gioco.checkCurrentCardMapInsertability());



	}


	/**
	 * Testa la possibilità di inserire una pedina in un circuito 
	 * a strada chiusa su se stessa
	 */
	@Test
	public void TestControlloEsistenzaPedina() {


		Game gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.setNextPlayer();
		gioco.setCurrentCoordinate(1, 0);


		Card tesseraA = new Card("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1"); // (1, 0)
		Card tesseraB = new Card("N=S S=N W=S E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0"); // (1, -1)
		Card tesseraC = new Card("N=C S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (0, -1)
		Card tesseraD = new Card("N=S S=N W=N E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)
		Card tesseraE = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1, 0)




		gioco.insertCard(1, 0, tesseraA);
		gioco.insertCard(1, -1, tesseraB);
		gioco.insertCard(0, -1, tesseraC);
		gioco.insertCard(-1, -1, tesseraD);
		gioco.insertCard(-1, 0, tesseraE);
		gioco.setCurrentCard(tesseraA);


		assertTrue("Nessuna pedina presente nel percorso", gioco.checkPiecePresence(1,0, SOUTH));
		tesseraE.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());
		assertFalse("Pedina già presente nel percorso", gioco.checkPiecePresence(1, 0, SOUTH));
		gioco.setNextPlayer();
		assertTrue("Pedina già presente nel percorso, ma di un altro giocatore", gioco.checkPiecePresence(1, 0, SOUTH));

		gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.insertPlayer(); //giocatore 2
		gioco.setNextPlayer(); //giocatore 0
		gioco.setCurrentCoordinate(-1, 0);


		tesseraA = new Card("N=N S=N W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (1, 0)
		tesseraB = new Card("N=N S=N W=S E=S NS=0 NE=0 NW=9 WE=1 SE=0 SW=0"); // (-1, 0)
		tesseraC = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-2, 0)
		tesseraD = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)


		gioco.insertCard(1, 0, tesseraA);
		gioco.insertCard(-1, 0, tesseraB);
		gioco.insertCard(-2, 0, tesseraC);
		gioco.insertCard(-1, -1, tesseraD);
		gioco.setCurrentCard(tesseraB);


		assertTrue("Nessuna pedina presente e collegamento terminale", gioco.checkPiecePresence(-1, 0, EAST));
		assertTrue("Nessuna pedina presente e collegamento vuoto", gioco.checkPiecePresence(-1, 0, WEST));
		tesseraA.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		assertFalse("Pedina presente e collegamento vuoto", gioco.checkPiecePresence(-1, 0, EAST));
		gioco.setNextPlayer();
		assertTrue("Pedina presente ma di un altro colore", gioco.checkPiecePresence(-1, 0, EAST));

	}

	@Test
	/**
	 * Testa la possibilità di inserire una pedina in un circuito 
	 * tra strada aperta e strada aperta
	 */
	public void TestSituazioneInseribilitaPedina() {

		Game gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.insertPlayer(); //giocatore 2
		gioco.setNextPlayer(); //giocatore 0
		gioco.setCurrentCoordinate(1, 0);

		Card tesseraA = new Card("N=C S=S W=S E=C NS=0 NE=1 NW=0 WE=0 SE=0 SW=1"); // (1,0)
		Card tesseraB = new Card("N=N S=C W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (1,1)
		Card tesseraC = new Card("N=N S=C W=C E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1"); // (2,0)

		gioco.insertCard(1,0,tesseraA);
		gioco.insertCard(1,1,tesseraB);
		gioco.insertCard(2,0,tesseraC);

		gioco.setCurrentCard(tesseraA);

		assertTrue("Pedina inseribile, nessuna pedina presente nella città a nord", gioco.pieceInsertionSituation()[0]);
		assertTrue("Pedina inseribile, nessuna pedina presente nella strada a ovest", gioco.pieceInsertionSituation()[2]);

		//Inserisco una pedina
		tesseraC.getSide(SOUTH).putPiece(gioco.getCurrentPlayerObject());

		assertFalse("Pedina già presente a SUD di (2,0)", gioco.pieceInsertionSituation()[0]);

		gioco.setNextPlayer();
		assertTrue("Si può usufruire del collegamento! C'è una pedina a sud di (2,0) ma è di un altro giocatore!", gioco.pieceInsertionSituation()[3]);


	}

	@Test
	/**
	 * Verifica la terminazione di una strada su se stessa
	 */
	public void TestControlloTerminazioneStrada() {

		Game gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.setNextPlayer();
		gioco.setCurrentCoordinate(1, 0);


		Card tesseraA = new Card("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1"); // (1, 0)
		Card tesseraB = new Card("N=S S=N W=S E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0"); // (1, -1)
		Card tesseraC = new Card("N=C S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (0, -1)
		Card tesseraD = new Card("N=S S=N W=N E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)
		Card tesseraE = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1, 0)

		gioco.insertCard(1, 0, tesseraA);
		gioco.insertCard(1, -1, tesseraB);
		gioco.insertCard(0, -1, tesseraC);
		gioco.insertCard(-1, -1, tesseraD);

		gioco.setCurrentCard(tesseraA);


		assertFalse("Strada non terminata manca la tesseraE", gioco.checkFinishObject(1, 0 , SOUTH).getState());

		gioco.insertCard(-1, 0, tesseraE);
		assertTrue("Strada terminata", gioco.checkFinishObject(1,0, SOUTH).getState());

		gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.setNextPlayer();
		gioco.setCurrentCoordinate(1, 0);


		tesseraA = new Card("N=C S=N W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (1, 0)
		tesseraB = new Card("N=N S=N W=N E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1, 0)


		gioco.insertCard(1, 0, tesseraA);

		gioco.setCurrentCard(tesseraA);

		assertFalse("Tessera -1,0 non presente, oggetto non terminato", gioco.checkFinishObject(1, 0, WEST).getState());
		gioco.insertCard(-1, 0, tesseraB);
		assertTrue("Strada finita in un campo!", gioco.checkFinishObject(1, 0, WEST).getState());


	}





	//@Test
	/**
	 * Verifica la terminazione di una città
	 */

	public void TestControlloTerminazioneCitta1() {

		Game gioco = new Game();
		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.setNextPlayer();
		gioco.setCurrentCoordinate(-1, 0);


		Card tesseraA = new Card("N=S S=C W=N E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, 0)
		Card tesseraB = new Card("N=C S=N W=N E=C NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)
		Card tesseraC = new Card("N=C S=C W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=1"); // (0, -1)


		gioco.insertCard(-1, 0, tesseraA);
		gioco.insertCard(-1, -1, tesseraB);		
		gioco.insertCard(0, -1, tesseraC);

		gioco.setCurrentCard(tesseraA);

		assertFalse("La città non è terminata a causa di una biforcazione a 3", gioco.checkFinishObject(-1, 0, SOUTH).getState());
		tesseraC = new Card("N=C S=N W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0"); // (0, -1)
		gioco.insertCard(0, -1, tesseraC);
		assertTrue("La città è terminata!", gioco.checkFinishObject(-1, 0, SOUTH).getState());

		tesseraA = new Card("N=S S=C W=N E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, 0)
		tesseraB = new Card("N=C S=N W=N E=C NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)
		tesseraC = new Card("N=C S=C W=C E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=1"); // (0, -1)
		Card tesseraD = new Card("N=C S=N W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (0, -2)

		gioco.insertCard(-1, 0, tesseraA);
		gioco.insertCard(-1, -1, tesseraB);		
		gioco.insertCard(0, -1, tesseraC);
		gioco.insertCard(0, -2, tesseraD);
		gioco.setCurrentCard(tesseraA);
		assertTrue("La città è terminata!", gioco.checkFinishObject(-1, 0, SOUTH).getState());

	}




	@Test
	/**verifica l'assegnamento dei punti in caso di percorso completato
	 * 
	 */
	public void TestAssegnaPunti() {


		Game gioco = new Game();


		gioco.insertPlayer(); // giocatore 0
		gioco.insertPlayer(); // giocatore 1
		gioco.setNextPlayer();
		gioco.setCurrentCoordinate(-1, 0);

		Card tesseraA = new Card("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1"); // (1, 0)
		Card tesseraB = new Card("N=S S=N W=S E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0"); // (1, -1)
		Card tesseraC = new Card("N=C S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (0, -1)
		Card tesseraD = new Card("N=S S=N W=N E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=0"); // (-1, -1)
		Card tesseraE = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1, 0)

		gioco.insertCard(1, 0, tesseraA);
		gioco.insertCard(1, -1, tesseraB);
		gioco.insertCard(0, -1, tesseraC);
		gioco.insertCard(-1, -1, tesseraD);
		gioco.insertCard(-1, 0, tesseraE);
		gioco.setCurrentCard(tesseraE);

		tesseraD.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());
		assertSame("Numero di pedine disponibili = 6 poichè 1 usata", 6, gioco.getCurrentPlayerPiecesNumber());
		gioco.assignPoints();
		assertSame("Punteggio del giocatore corrente = 6 (6 tessere x 1 punto/tessera)", 6,gioco.getCurrentPlayerScore());
		assertSame("Numero di pedine disponibili = 7 (usata ma restituita)", 7, gioco.getCurrentPlayerPiecesNumber());
		gioco.setNextPlayer();
		assertSame("Numero di pedine disponibili giocatore 1 = 7", 7, gioco.getCurrentPlayerPiecesNumber());
		assertSame("Punteggio giocatore 1 = 0", 0, gioco.getCurrentPlayerScore());


		gioco=new Game();
		gioco.insertPlayer(); //giocatore 0
		gioco.insertPlayer(); //giocatore 1
		gioco.insertPlayer(); //giocatore 2

		gioco.setNextPlayer(); // giocatore 0

		gioco.setCurrentCoordinate(-2, 0);


		Card tesseraF = new Card("N=N S=N W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); //(1,0)
		Card tesseraH = new Card("N=N S=N W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); //(-1,0)
		Card tesseraI = new Card("N=N S=S W=C E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); //(-2,0)
		Card tesseraL = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); //(-2,-1)
		Card tesseraM = new Card("N=S S=N W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); //(-2,-2)

		gioco.insertCard(1, 0, tesseraF);
		gioco.insertCard(-1, 0, tesseraH);
		gioco.insertCard(-2, 0, tesseraI);
		gioco.insertCard(-2, -1, tesseraL);
		gioco.insertCard(-2, -2, tesseraM);
		gioco.setCurrentCard(tesseraI);

		tesseraF.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		tesseraH.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer(); // giocatore 1
		tesseraI.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		tesseraM.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer(); // giocatore 2
		tesseraL.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer(); // giocatore 0

		gioco.assignPoints();

		assertSame("Punteggio del giocatore 0 è = 6", 6, gioco.getCurrentPlayerScore());
		gioco.setNextPlayer(); //giocatore 1
		assertSame("il numero di pedine del giocatore 1 è = 6", 6, gioco.getCurrentPlayerPiecesNumber());
		assertSame("punteggio giocatore corrente=0",0,gioco.getCurrentPlayerScore());
		gioco.setNextPlayer();
		assertSame("punteggio giocatore corrente=0",0,gioco.getCurrentPlayerScore());

		gioco = new Game();
		gioco.insertPlayer(); //giocatore 0
		gioco.insertPlayer(); //giocatore 1
		gioco.insertPlayer(); //giocatore 2

		gioco.setNextPlayer(); // giocatore 0

		gioco.setCurrentCoordinate(-1, -1);

		Card tesseraN = new Card("N=C S=S W=C E=S NS=0 NE=1 NW=1 WE=0 SE=1 SW=0"); //(0,-1)
		Card tesseraO = new Card("N=N S=C W=N E=C NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); //(-1,-1)
		Card tesseraP = new Card("N=S S=C W=C E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=1"); //(0,-2)
		Card tesseraQ = new Card("N=C S=N W=C E=C NS=0 NE=1 NW=1 WE=1 SE=0 SW=0"); //(-1,-2)
		Card tesseraR = new Card("N=N S=N W=N E=C NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); //(-2,-2)
		Card tesseraS = new Card("N=C S=N W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); //(0,-3)

		gioco.insertCard(0, -1, tesseraN);
		gioco.insertCard(-1, -1, tesseraO);
		gioco.insertCard(0, -2, tesseraP);
		gioco.insertCard(-1, -2, tesseraQ);
		gioco.insertCard(-2, -2, tesseraR);
		gioco.insertCard(0, -3, tesseraS);
		gioco.setCurrentCard(tesseraO);

		tesseraN.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		tesseraR.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());
		tesseraS.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer(); // giocatore 1
		tesseraO.getSide(SOUTH).putPiece(gioco.getCurrentPlayerObject());
		tesseraP.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer(); // giocatore 2
		tesseraQ.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());

		gioco.setNextPlayer();
		gioco.assignPoints();

		assertSame("il giocatore corrente ha 14 punti",14,gioco.getCurrentPlayerScore());
		gioco.setNextPlayer();
		assertSame("punteggio giocatore corrente=0",0,gioco.getCurrentPlayerScore());
		assertSame("numero pedine giocatore=6",6,gioco.getCurrentPlayerPiecesNumber());
		gioco.setNextPlayer();
		assertSame("numero pedine giocatore=7",7,gioco.getCurrentPlayerPiecesNumber());

	}


	@Test
	/**verifica l'assegnamento dei punti al termine della partita anche di tutti 
	 * i percorsi non completati
	 * 
	 */
	public void assegnaPuntiFinali(){

		Game gioco = new Game();

		gioco.insertPlayer(); //giocatore0
		gioco.insertPlayer(); //giocatore1
		gioco.insertPlayer();
		gioco.setNextPlayer(); // giocatore 0
		gioco.setCurrentCoordinate(-1, 0);

		Card tesseraA = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1,0)
		Card tesseraB = new Card("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (1,0)
		Card tesseraC = new Card("N=C S=C W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (1,-1)
		Card tesseraD = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-1)		

		gioco.insertCard(-1, 0, tesseraA);
		gioco.insertCard(1, 0, tesseraB);
		gioco.insertCard(1, -1, tesseraC);
		gioco.insertCard(-1, -1, tesseraD);
		gioco.setCurrentCard(tesseraA);

		tesseraA.getSide(SOUTH).putPiece(gioco.getCurrentPlayerObject());
		gioco.setNextPlayer();

		tesseraC.getSide(SOUTH).putPiece(gioco.getCurrentPlayerObject());		
		gioco.assignFinalPoints();
		assertSame("Giocatore 1 ha 1 pedina x 2 tessere città = 2 punti", 2, gioco.getCurrentPlayerScore());
		gioco.setNextPlayer();
		assertSame("Giocatore 2 ha 0 punti", 0, gioco.getCurrentPlayerScore());
		gioco.setNextPlayer();
		assertSame("Giocatore 0 ha 1 pedina in 4 tessere strada = 4 punti", 4 , gioco.getCurrentPlayerScore());
		assertSame("Giocatore 0 ha 7 pedine", 7, gioco.getCurrentPlayerPiecesNumber());


		gioco = new Game();

		gioco.insertPlayer(); //giocatore0
		gioco.insertPlayer(); //giocatore1
		gioco.insertPlayer(); //giocatore2
		gioco.setNextPlayer(); // giocatore 0


		Card tesseraE = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1,0)
		Card tesseraF = new Card("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (1,0)
		Card tesseraG = new Card("N=C S=C W=C E=N NS=1 NE=0 NW=1 WE=0 SE=0 SW=1"); // (1,-1)
		Card tesseraH = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-1)		

		gioco.insertCard(-1, 0, tesseraE);
		gioco.insertCard(1, 0, tesseraF);
		gioco.insertCard(1, -1, tesseraG);
		gioco.insertCard(-1, -1, tesseraH);

		tesseraF.getSide(SOUTH).putPiece(gioco.getCurrentPlayerObject());
		tesseraH.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());
		gioco.setNextPlayer();

		tesseraG.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		gioco.setNextPlayer();

		tesseraE.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());

		gioco.assignFinalPoints();

		assertSame("Giocatore2 ha 4 punti=4 tessere strada",4,gioco.getCurrentPlayerScore());
		assertSame("Giocatore2 ha 7 pedine",7,gioco.getCurrentPlayerPiecesNumber());
		gioco.setNextPlayer();
		assertSame("Giocatore0 ha 6 punti=4 tessere strada e 2 città",6,gioco.getCurrentPlayerScore());
		gioco.setNextPlayer();
		assertSame("Giocatore1 ha 2 punti=2 tessere città",2,gioco.getCurrentPlayerScore());
		assertSame("Giocatore1 ha 7 pedine",7,gioco.getCurrentPlayerPiecesNumber());


		gioco = new Game();

		gioco.insertPlayer(); //giocatore0
		gioco.insertPlayer(); //giocatore1
		gioco.insertPlayer(); //giocatore2
		gioco.setNextPlayer(); // giocatore 0
		
	
		Card tesseraO = new Card("N=C S=N W=C E=C NS=0 NE=1 NW=1 WE=1 SE=0 SW=0"); // (0, -1)
		Card tesseraP = new Card("N=N S=N W=C E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0"); // (1, -1)
		gioco.insertCard(0, -1, tesseraO);
		gioco.insertCard(1, -1, tesseraP);
		tesseraP.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		
		gioco.assignFinalPoints();
		
		assertSame("Punteggio: ", 3, gioco.getCurrentPlayerScore());
		
		gioco.setNextPlayer();
		
		assertSame("Punteggio: ", 0, gioco.getCurrentPlayerScore());
		
	}

	/**
	 * vengono testate le operazioni finali da compiere quando le tessere sono esaurite
	 */
	public void operazioniFinali(){
		Game gioco = new Game();

		gioco.insertPlayer(); //giocatore0
		gioco.insertPlayer(); //giocatore1
		gioco.setNextPlayer(); // giocatore 0
		gioco.setCurrentCoordinate(1, 0);

		Card tesseraA = new Card("N=N S=S W=N E=S NS=0 NE=0 NW=0 WE=0 SE=1 SW=0"); // (-1,0)
		Card tesseraB = new Card("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"); // (1,0)
		Card tesseraD = new Card("N=S S=S W=N E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0"); // (-1,-1)		

		gioco.insertCard(-1, 0, tesseraA);
		gioco.insertCard(1, 0, tesseraB);
		gioco.insertCard(-1, -1, tesseraD);
		gioco.setCurrentCard(tesseraB);

		tesseraA.getSide(EAST).putPiece(gioco.getCurrentPlayerObject());		
		tesseraB.getSide(WEST).putPiece(gioco.getCurrentPlayerObject());
		gioco.setNextPlayer(); //giocatore 1

		tesseraD.getSide(NORTH).putPiece(gioco.getCurrentPlayerObject());
		gioco.setNextPlayer(); //giocatore 0


		assertFalse("il percorso non è terminato",gioco.finalOperations(1, 0, EAST, tesseraB).getState());
		assertSame("il numero di tessere attraversate è:4",4,gioco.finalOperations(1, 0, EAST, tesseraB).howManyCards());

	}

}
