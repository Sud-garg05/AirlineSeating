package AirlineSeating;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AirPlane {
	//containing a 2D array for blocks of seats present in plane
	private int[][] layout;
	
	//containing the details of seat in plane
	private Seat[][] seatLayout;
	
	//maximum number of columns Airplane, calculated while assigning seatType
	private int maxC = 0;
	//maximum number of rows in Airplane, calculated while assigning seatType
	private int maxR = 0;

	/** 
	 * Constructor to construct the layout accouding to blocks present in plane
	 * @param layout : containing a 2D array for blocks of seats present in plane
	 */
	public AirPlane(int[][] layout) {
		this.layout = layout;
		assignSeatType();
		//printSeatType();
	}
	
	/** 
	 * assign seat type for each block
	 * 
	 */
	private void assignSeatType() {
		for (int i = 0; i < layout.length; i++) {
			maxC += layout[i][0];
			maxR = Math.max(maxR, layout[i][1]);
		}
		seatLayout = new Seat[maxR][maxC];

		int colNum = 0;
		for (int[] block : layout) {
			assignSeatTypeToBlock(colNum, block[1], block[0]);
			colNum += block[0];
		}
	}

	/** 
	 * Assiging the seat type according to the block
	 * 		W --> Window Seat
	 * 		C --> Center Seat
	 * 		A --> Asile Seat
	 * 		N --> No Seat Available
	 * 
	 * @param start : defines start of the block
	 * @param rows	: number of rows in block
	 * @param cols	: number of columns in block
	 * 
	 */
	private void assignSeatTypeToBlock(int start, int rows, int cols) {
		for (int i = 0; i < maxR; i++) {
			for (int j = start; j < start + cols; j++) {
				if (i>rows-1)
					seatLayout[i][j] = new Seat("N");
				else if (j == 0 || j == maxC-1)
					seatLayout[i][j] = new Seat("W");
				else if (j == start || j == start+cols-1)
					seatLayout[i][j] = new Seat("A");
				else
					seatLayout[i][j] = new Seat("C");

			}
		}
	}

	/**
	 * private method to be used inside this class only
     * allocate the seats to passenger from left to right
     * @param type as the type of seat to be assigns based on priority
     * @param pass as passenger number
     * @return true if seat available
     */
	private boolean allocateOneSeat(String type, int pass) {
		for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxC; j++) {
				Seat seat = seatLayout[i][j];
				if(seat.getSeatType().equals(type))
				{	
					if(!seat.isOccupied)
					{
						seat.isOccupied = true;
						seat.passengerNumber = pass;
						return true;
					}	
				}
			}
		}
		return false;
	}

	/**
	 * public method to be used in Manager class
     * allocate the seats to passenger with type priority
     * first the ASILE seats are allocated then WINDOW seats and then the CENTER seats are allocated
     * @param num as number of passenger
     */
	public void allocateSeats(int num) {
		for (int j = 1; j < num+1; j++) 
		{
			if (!cheakAndAllocate(j)) 
			{	
				System.out.println("Plane Already Full..Cannot accomodate more then " + (j-1));
				return;
			}
		}	
		System.out.println("All the passeners are accomodated...!");
	}
	
	/**
	 * check if the seat is available and allocate with priority if available
     * @return
     */
	public boolean cheakAndAllocate(int s)
	{
		return allocateOneSeat("A", s) || allocateOneSeat("W", s) || allocateOneSeat("C", s);
	}
	

	/**
     * Printing the seats type on Console 
     */
	public void printSeatType() {
		for (int i = 0; i < seatLayout.length; i++) {
			for (int j = 0; j < seatLayout[i].length; j++) {
				System.out.print(seatLayout[i][j].getSeatType() + " ");
			}
			System.out.println();
		}
	}
	
	/**
     * Printing the seatLayout with passenger on Console
     */
	public void printSeatOccupancy() {
		
	    
		for (int i = 0; i < seatLayout.length; i++) {
			int col = 0;
			int block = 0;
			for (int j = 0; j < seatLayout[i].length; j++) {
				if(j==layout[block][0]+col) 
				{
					System.out.print("|");
					col = j;
					block++;
				}
				if (seatLayout[i][j].isOccupied)
					System.out.print(seatLayout[i][j].getPassengerNumber() + " ");
				else
					System.out.print(seatLayout[i][j].getSeatType() + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
     * Printing the seatLayout to a HTML file
     * @throws IOException
     */
	public void printAirplaneHTML() throws IOException {
	    PrintWriter pw = new PrintWriter(new FileWriter("Plane.html"));
	    pw.println("<TABLE BORDER>");

	    for (int i = 0; i < seatLayout.length; i++) {
	    	int col = 0;
			int block = 0;
	    	pw.println("<TR ALIGN=CENTER>");
			for (int j = 0; j < seatLayout[i].length; j++) 
			{
				String str = "";
				if(j==layout[block][0]+col) 
				{
					pw.println("<TD BGCOLOR=white> |");
					col = j;
					block++;
				}
				Seat seat = seatLayout[i][j];
				str = str.concat("<TD BGCOLOR=" + seat.getColor() +">");
				if (seatLayout[i][j].isOccupied)
					str = str.concat(seat.getPassengerNumber() + "");
				else
					str = str.concat(seatLayout[i][j].getSeatType());
				pw.println(str);
			}
	    }
	    pw.println("</TABLE>");
	    pw.close(); 
	    System.out.println("The Plane Layout displayed at "+System.getProperty("user.dir")+"/Plane.html");

	  }

}
