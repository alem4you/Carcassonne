/**la classe Coordinata si occupa della memorizzazione delle coordinate di
 * ogni tessera
 *  
 * @author  Alessandro
 * @version 1.0
 */
package data;


public class Coordinate {

	
	private final int row;
	private final int column;

	/** 
	 * Metodo costruttore della Classe Coordinata
	 * 
	 */
	public Coordinate(int column, int row) {

		this.row=row;
		this.column=column;

	}

	/** 
	 * Implementazione della funzione equals per l'oggetto Coordinata
	 * 
	 */
	public boolean equals(Object coordinate){
		if(coordinate == null) {
			return false;
		}
		if(this.hashCode()==coordinate.hashCode()) {
			return true; }
		return false;
	}

	/** 
	 * Implementazione della funzione hashCode
	 * 
	 */
	public int hashCode(){
		return (row*31)+column;
	}

	/**
	 * @return ritorna la riga corrente
	 * 
	 */
	public int getRow(){

		return this.row;
	}

	/**
	 * @return ritorna la colonna corrente
	 * 
	 */
	public int getColumn() {

		return this.column;

	}

}
