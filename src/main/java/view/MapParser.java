/**
 * La classe MapParser riceve una rappresentazione a stringhe della mappa
 * e ne stampa a video una rappresentazione testuale
 * 
 * @author giulialarosa
 * @version 1.0
 */

package view;
import data.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MapParser implements Constants{

	private HashMap<Coordinate, String[]> map = new HashMap<Coordinate, String[]>(); 
	private int minorColumn = 0;
	private int majorColumn = 0;
	private int minorRow = 0;
	private int majorRow = 0;
	private static PrintStream out = System.out;

	/**
	 * Metodo costruttore della classe MappaParser
	 */
	public MapParser(ArrayList<String[]> mapData) {

		// da migliorare (tipo inserire nel prossimo ciclo)
		for(String[] p: mapData) {

			if(Integer.parseInt(p[COLUMN])<minorColumn) {
				minorColumn=Integer.parseInt(p[COLUMN]);
			}
			if(Integer.parseInt(p[COLUMN])>majorColumn) {
				majorColumn=Integer.parseInt(p[COLUMN]);
			}
			if(Integer.parseInt(p[ROW])<minorRow) {
				minorRow=Integer.parseInt(p[ROW]);
			}
			if(Integer.parseInt(p[ROW])>majorRow) {
				majorRow=Integer.parseInt(p[ROW]);
			}

		}

		for(String[] p : mapData) {

			String[] insertString = new String[SIDE_NUM];
			
			System.arraycopy(p, 0, insertString, 0, SIDE_NUM);
			
			map.put(new Coordinate(Integer.parseInt(p[COLUMN]),Integer.parseInt(p[ROW])), insertString);

		}
		for(int i=minorRow;i<=majorRow;i++) {
			for(int j=minorColumn;j<=majorColumn;j++) {
				if(map.containsKey(new Coordinate(j,i)) && map.get(new Coordinate(j,i))!=null) {
					if(!map.containsKey(new Coordinate(j+1,i))) {
						map.put(new Coordinate(j+1,i), null);
					}
					if(!map.containsKey(new Coordinate(j-1,i))) {
						map.put(new Coordinate(j-1,i), null);
					}
					if(!map.containsKey(new Coordinate(j,i+1))) {
						map.put(new Coordinate(j,i+1), null);
					}
					if(!map.containsKey(new Coordinate(j,i-1))) {
						map.put(new Coordinate(j,i-1), null);
					}
				}

			}
		}



	}

	/**
	 * Il metodo stampa() si occupa di stampare in modo formattato la mappa di gioco
	 * utilizzando dei simboli
	 */
	public void print() {

		for(int i = majorRow+1;i>=minorRow-1;i--) {
			for(int k=0;k<LINE_NUMBER;k++) {
				for(int j = minorColumn-1; j<=majorColumn+1;j++) {

					if(j==minorColumn-1) {
						if(k==0) {
							out.print("+");
						}
						else {
							out.print(".");
						}
					}

					if(map.containsKey(new Coordinate(j,i))) {
						String[] informations = map.get(new Coordinate(j,i));
						String stringToPrint;
						String symbolToPrint;

						if( (map.get(new Coordinate(j,i+1))==null && map.get(new Coordinate(j,i))!=null) || (map.get(new Coordinate(j,i+1))!=null && map.get(new Coordinate(j,i))==null)) {
							stringToPrint = RIGA_CANCELLETTI_PIU;
						}
						else {
							stringToPrint = ROW_POINTS_PLUS;
						}

						if((map.get(new Coordinate(j+1,i))==null && map.get(new Coordinate(j,i))!=null) || (map.get(new Coordinate(j+1,i))!=null && map.get(new Coordinate(j,i))==null)) {
							symbolToPrint = "#";
						}
						else {
							symbolToPrint = ".";
						}

						if(informations==null) {
							if(k==0) {
								out.printf("%s", stringToPrint);
							}
							else {
								if(k!=3) {
									out.printf("              %s", symbolToPrint);
								}
								else {
									out.printf("    %7s   %s", "("+j+","+i+")", symbolToPrint);
								}
							}
						}
						else {

							switch(k) {

							case ZERO_LINE: out.printf("%s", stringToPrint);
							break;
							case UNO_LINE: out.printf("    %5s     %s", informations[NORTH], symbolToPrint);
							break;		
							case TRE_LINE: out.printf("%-5s    %5s%s", informations[WEST], informations[EAST], symbolToPrint);
							break;
							case CINQUE_LINE: out.printf("    %5s     %s", informations[SOUTH], symbolToPrint);
							break;
							default: out.printf("              %s", symbolToPrint);
							break;	

							}

						}


					}

					else {
						if(k==0) {
							out.printf(ROW_POINTS_PLUS);
						}
						else {
							out.printf(ROW_VOID_POINT);	
						}

					}
				}

				out.printf("\n");
			}



		}
		out.print("+");
		for(int i=minorColumn-1;i<=majorColumn+1;i++) {
			out.printf(ROW_POINTS_PLUS);
		}

	}




}











