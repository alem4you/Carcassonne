/**
 * 
 * La classe LocalInterface contiene l'interfaccia locale con cui e' possibile 
 * giocare a Carcassonne tramite l'ausilio di una tastiera condivisa tra i giocatori
 * e una rappresentazione grafica testuale delle tessere e della mappa.
 * 
 * 
 * @author 
 * @version 1.0
 */

package controller;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.Methods;

import view.MapParser;
import view.CardParser;

import model.Game;
import data.Constants;


public class LocalInterface implements Constants{

	private static Game game= new Game();
	private static PrintStream out = System.out;
	
	
	/**il metodo stampa la mappa nello stato attuale di gioco
	 * 
	 */

	public static  void printMap() {

		out.printf("\n");
		out.println("Current map: ");
		MapParser mapParser = new MapParser(game.returnStringedMap());
		mapParser.print();
		out.printf("\n");

	}

	/**
	 * il metodo stampa una singola tessera
	 * 
	 */
	public static void printCard() {
		out.printf("\n");
		CardParser cardParser = new CardParser(game.riturnStringedCard());
		cardParser.print();
		out.printf("\n");
	}

	/**
	 * il metodo stampa un messaggio d'errore quando l'utente digita un valore non ammesso
	 */

	public static void printCommonError() {
		
		out.println("Error! Retry and don't try to cheat :P !");
	}
	
	/**
	 * il metodo stampa tutte le informazioni aggiornate del giocatore corrente:
	 * i suoi punti e le sue pedine
	 */
	public static void printCurrentPlayerState () {

		out.printf("%-6s has got %d points and %d pieces!\n", Methods.getPlayerLongName(game.getCurrentPlayer()), game.getCurrentPlayerScore(), game.getCurrentPlayerPiecesNumber());

	}
	
	/**
	 * Stampa la lista dei giocatori e il relativo stato.
	 */
	public static void printPlayerList() {
		for(int i=0; i<game.getPlayersNumber();i++) {
			game.setNextPlayer();
			printCurrentPlayerState();
		}
	}
	
	
	/**
	 * Interfaccia di gioco per Console
	 */
	public static void main(String args[]) {

		int numberOfPlayers=0;
		Scanner scanner;


		out.println("CARCASSONNE BY ALESSANDRO MARETTI AND GIULIA LA ROSA - WELCOME!");
		out.println("CARCASSONNE IS A GAME FOR 2 TO 5 PLAYER! ENJOY!");



		// richiede il numero di giocatori e li inserisce nel gioco

		boolean completed;
		do {
			completed = false;
			out.println("How many people will play? (min 2 - max 5): ");
			try {
				scanner = new Scanner(System.in);
				numberOfPlayers=scanner.nextInt();
				completed = true;}
			catch(InputMismatchException e2) {
				out.println("Error, you have inserted a wrong value, remember that only 2 to 5 players are allowed.");
			}
		}
		while(!completed || numberOfPlayers<MIN_GIOCATORI || numberOfPlayers>MAX_GIOCATORI);


		for(int i=0;i<numberOfPlayers;i++) {
			game.insertPlayer();
		}

		// Visualizza lo stato iniziale del gioco, mappa, giocatori presenti e segnalini
		out.printf("\n");
		out.printf("\n");
		out.println("Game starts in 5 seconds...: ");
	
		printPlayerList();
		
		try {
			Thread.sleep(TIMEOUT_START);
		} catch (InterruptedException e1) {
			System.exit(0);
		}



		while(game.getRemainingCards()>0) {


			out.printf("\n");
			out.printf("\n");
			game.setNextPlayer();
			out.println("Is " + Methods.getPlayerLongName(game.getCurrentPlayer()) + "'s round. Take keyboard control!");
			printCurrentPlayerState();

			out.printf("\n");
			printMap();	
			out.printf("\n");

			try {
				Thread.sleep(TIMEOUT_CARD);
			} catch (InterruptedException e1) {
				System.exit(0);
			}

			do{
				game.setCurrentCard(); }
			while(!game.checkCurrentCardMapInsertability());
			
			out.printf("\nTHERE ARE STILL %d CARDS IN THE DECK...\n", game.getRemainingCards());
			out.printf("You drew this card: ");
			

			String choice;
			completed=false;
			do {
				printCard();
				out.printf("You can: \n");
				out.printf("Digit \"insert\" to insert your card in a position \n");
				out.printf("Digit \"rotate\" to rotate 90° your card  \n");
				out.printf("Digit \"pass\" to discard your card and pass your round \n");
				out.printf("Digir \"map\" to see the map \n");
				out.printf("Insert your choice: \n");

				scanner = new Scanner(System.in);
				try {
					choice = scanner.next();
				}
				catch(InputMismatchException e) {
					choice="";
					printCommonError();
				}

				choice=choice.toUpperCase();
				
				if(choice.equals("INSERT")) {

					int column;
					int row;

					out.printf("You decided to insert your card!\n");


					Boolean correct=false;
					do {
						out.print("Insert column: ");
						try{
							scanner = new Scanner(System.in); 
							column = scanner.nextInt();
							correct=true;
						}
						catch(InputMismatchException e1) {
							printCommonError();
							column=0;
						}

					}
					while(!correct);

					correct=false;
					do {
						out.print("Inserisci row: ");
						try{
							scanner = new Scanner(System.in); 
							row = scanner.nextInt();
							correct=true;
						}
						catch(InputMismatchException e1) {
							printCommonError();
							row=0;
						}

					}
					while(!correct);

					game.setCurrentCoordinate(column, row);

					if(game.checkCurrentCardInsertability()) {
						out.printf("Your card has been inserted correctly in this position: (%s,%s)\n", column, row);
						game.insertCurrentCard();
						completed=true;
					}
					else {
						out.printf("You can't insert your card in this position (%s,%s), retry! \n", column, row);
					}



				}
				else {
					if(choice.equals("ROTATE")) {
						game.rotateCurrentCard();
					}
					else {

						if(choice.equals("PASS")) {
							out.printf("Do you really want to pass your round? (yes/no) \n");
							scanner = new Scanner(System.in);
							String conferma = scanner.next();
							if(conferma.equals("yes")) {
								completed=true;
							}

						}
						else {

							if(choice.equals("MAP")) {
								printMap();
							}
							else {
								printCommonError();
							}
						}
					}

				}


			}
			while(!completed);

			if(!choice.equals("PASS") && game.pieceInsertable()) {
			
					out.printf("You can insert a piece! Do you want to pursue this opportunity (yes/no)?\n");
					scanner = new Scanner(System.in);
					String confirmation = scanner.next();
					if(confirmation.equals("yes")) {

						Boolean[] isInsertable = game.pieceInsertionSituation();
						Boolean checkerFinished=false;
						do {
							out.println();
							printMap();
							out.println();

							out.println("Select the side where you want to insert a piece (e.g. C1, C2, S1, etc.) ");
							out.println("You can also digit \"pass\" if you changed your mind!");
							
							scanner = new Scanner(System.in);
							String position = scanner.next();
							position=position.toUpperCase();
							
							int posix=-1;
							int i=0;
							while(posix==-1 && i<6) {
								
								if(position.equals(game.riturnStringedCard()[i])) {
									posix=i;
								}
								i++;
								
							}

							if(posix!=-1) {

								if(isInsertable[posix]) {
									out.printf("Piece inserted correctly at %s", position);
									checkerFinished=true;
									game.insertPiece(posix);
								}
								else {
									out.printf("A piece cannot ne inserted in this side, retry or digit PASS to pass your round. \n");
								}
							}

							if(position.equals("PASS")) {
								checkerFinished=true;
							}



						}
						while(!checkerFinished);

					}
			
				
			}
			
			Boolean result = game.assignPoints();
			if(result) {
				out.printf("\nVery good %s! You finished a road or a city, some point will be assigned!", Methods.getPlayerLongName(game.getCurrentPlayer()));
			}
			
			out.printf("\nThis is your situation at the end of this round : \n");
			printCurrentPlayerState();
			out.printf("\n");
			out.printf("\n");

			out.printf("Next round...");
			out.printf("\n");
			out.printf("\n");

			try {
				Thread.sleep(TIMEOUT_START);
			} catch (InterruptedException e) {
			}
		}
	
		out.println("\nCards are finished! Now final points will be assigned!\n\n");
		game.assignFinalPoints();
		
		out.print("\n\nFINAL POINTS: \n\n");
		printPlayerList();
		
		out.println("Thank you for playing Carcassonne, we are sure you appreciated our efforts :) ");
	
	}
	
}



