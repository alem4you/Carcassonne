/**
 * 
 * La classe Tessera memorizza le informazioni che sono contenute
 * in una tessera e specifica i metodi che servono ad utilizzarla.
 *
 * L'array lati e' cosi' fatto
 * 0 = NORD  
 * 1 = SUD	
 * 2 = OVEST
 * 3 = EST 
 * 
 * L'array collegamenti e' cosi' fatto
 * 0 = NS / 1 = NE / 2 = NO / 3 = OE / 4 = SE / 5 = SO
 * 
 * @author 
 * @version 1.0
 */


package model;
import java.util.ArrayList;

import data.Constants;
import data.Methods;


public class Card implements Constants{


	private Side[] sides = new Side[SIDE_NUM];
	private Boolean[] links = new Boolean[LINK_NUM];




	/**
	 * Metodo Costruttore della classe tessera
	 * stringa rappresenta una stringa cosi' formata 
	 * N=S|C|N S=S|C|N W=S|C|N E=S|C|N NS=0|1 NE=0|1 NW=0|1 WE=0|1 SE=0|1 SW=0|1
	 */
	public Card(String string) {

		setSide(Methods.getIndexSideType(string.charAt(SIDENORD)),NORTH);
		setSide(Methods.getIndexSideType(string.charAt(SIDESUD)),SOUTH);
		setSide(Methods.getIndexSideType(string.charAt(SIDEOVEST)),WEST);
		setSide(Methods.getIndexSideType(string.charAt(SIDEEST)),EAST);

		setLink(string.charAt(LINKNS),NS);
		setLink(string.charAt(LINKNE),NE);
		setLink(string.charAt(LINKNW),NW);
		setLink(string.charAt(LINKWE),WE);
		setLink(string.charAt(LINKSE),SE);
		setLink(string.charAt(LINKSW),SW);

	}


	/**
	 * Crea un particolare tipo di Lato a seconda del parametro indiceTipoLato che gli 
	 * viene passato.
	 * indicePosizioneLato rappresenta la posizione dell'Array in cui i lato dovra' essere
	 * inserito.
	 */
	private final void setSide(int sideKindIndex, int sidePositionIndex) {

		switch(sideKindIndex) {
		case STREET:
			sides[sidePositionIndex] = new Side(0);
			break;
		case CITY:
			sides[sidePositionIndex] = new Side(1);
			break;
		case VOID:
			sides[sidePositionIndex] = new Side(2);
			break;
		default: sides[sidePositionIndex] = null;
		}
	}

	/**
	 * Definisce o meno la presenza di un certo tipo di collegamento a seconda di indiceTipoCollegamento.
	 * linkPositionIndex rappresenta la posizione dell'Array in cui il collegamento dovra' essere
	 * inserito.
	 */	
	private void setLink(char linkKindIndex, int linkPositionIndex) {

		if(linkKindIndex=='1') {
			links[linkPositionIndex]=true; }
		else {
			links[linkPositionIndex]=false; }

	}

	/**
	 * 
	 * @param cardinalPoint
	 * @return il tipo di lato a cui e' associato il punto cardinale passato come parametro
	 */
	public Side getSide(int cardinalPoint) {

		return sides[cardinalPoint];

	}

	/**il metodo stabilisce se tra due lati esiste un collegamento
	 * 
	 * @param linkPosition indice che identifica il tipo di collegamento cercato
	 * @return true se il collegamento esiste,false altrimenti
	 */
	public boolean getLink(int linkPosition) {

		return links[linkPosition];

	}

	/**il metodo stabilisce se tra due lati esiste un collegamento
	 * 
	 * @param posizioneCollegamento e' il nome del tipo di collegamento 
	 * @return true se il collegamento esiste,false altrimenti
	 */
	public boolean isLinked(String posizioneCollegamento) {

		return links[Methods.getIndexLinkType(posizioneCollegamento)];

	}

	/**
	 * Ruota la tessera di 90° secondo queste regole
	 * N->O (0->3) O->S (3->1) S->E (1->2) E->N (2->0)
	 */	
	public void rotateCard() {

		Side[] sidesSwap=new Side[SIDE_NUM];
		sidesSwap[NORTH]=sides[WEST];
		sidesSwap[SOUTH]=sides[EAST];
		sidesSwap[WEST]=sides[SOUTH];
		sidesSwap[EAST]=sides[NORTH];


		Boolean[] linkSwap = new Boolean[LINK_NUM];

		linkSwap[NS]=links[WE];
		linkSwap[NE]=links[NW];
		linkSwap[NW]=links[SW];
		linkSwap[WE]=links[NS];
		linkSwap[SE]=links[NE];
		linkSwap[SW]=links[SE];


		System.arraycopy(sidesSwap, 0, sides, 0, SIDE_NUM);
		System.arraycopy(linkSwap, 0, links, 0, LINK_NUM);


	}


	/**
	 * Metodo che scorre l'array collegamenti alla ricerca dei collegamenti attivi
	 * @return una ArratList contentente le posizioni nell'Array Collegamenti dei soli
	 * collegamenti attivi
	 */
	public ArrayList<Integer> getLinksArray() {

		ArrayList<Integer> activeLinks = new ArrayList<Integer>();

		for(int i=0;i<LINK_NUM;i++) {

			if(links[i]) {
				activeLinks.add(i); }

		}
		if(activeLinks.size()>0) {
			return activeLinks; }
		return null;

	}


	/**
	 * Ricve un punto cardinale e restituisce un ArrayList di indici dell'array collegamenti
	 * che sono impostati a true
	 */
	public ArrayList<Integer> getLinkFromSide(int cardinalPoint) {

		ArrayList<Integer> linksToReturn = new ArrayList<Integer>();

		Integer[] linkedSides = Methods.linkedSides(cardinalPoint);

		for(Integer p: linkedSides) {
			if(links[p]) {
				linksToReturn.add(p);
			}
		}

		return linksToReturn;

	}
	
	
	
	/**il metodo costruisce una stringa di caratteri dove si memorizzano le info di una tessera sulla mappa:
	 * per ogni singolo lato si dice se ha collegamenti e quali e se ci sono pedine su di esso
	 * 
	 * @return la stringa di informazioni
	 */
	public String[] string() {

		String[] string = new String[6];

		int countStreets=1;
		int countCities=1;
		int count=0;

		for(int i=0; i<SIDE_NUM;i++) {
			ArrayList<Integer> collegamentiDaRestituire = this.getLinkFromSide(i);
			if(this.getSide(i).getSideKind()==0) {
				count=countStreets; }
			if(this.getSide(i).getSideKind()==1) {
				count=countCities;
			}
			string[i] = Character.toString(Methods.getNameSydeType(this.getSide(i).getSideKind())) + count;
			if(this.getSide(i).getSideKind()==0) {
				countStreets++; }
			if(this.getSide(i).getSideKind()==1) {
				countCities++;
			}
			if(this.getSide(i).containsPiece()) {
				string[i] = string[i] + "(" + Methods.getPlayerGame(this.getSide(i).getPlayerPiece()) + ")";
			}  
			for(Integer p: collegamentiDaRestituire) {
				int indice = Methods.getLinkedSide(p, i);
				if(i>indice) {
					String appoggio;
					try {
						appoggio = string[i].substring(2, 5);
					}
					catch(StringIndexOutOfBoundsException e) {
						appoggio = "";
					}
					string[i] = string[indice].substring(0, 2) + appoggio;
				}
			}
		}
		for(int i=0; i<SIDE_NUM;i++) {
			if(string[i].charAt(0)=='N') {
				string[i] = "  ";
			}
		}

		return string;
	}


}
