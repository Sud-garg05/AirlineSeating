package AirlineSeating;

import java.io.IOException;
import java.util.Scanner;

public class AirlineManager {
	
	public static void main(String[] args) {
		System.out.println("Hi...! Welcome to Airline Seating Application");
		AirPlane plane = new AirPlane(getSeats());
		plane.allocateSeats(getPassenger());
		//plane.printSeatOccupancy();
		
		try {
			plane.printAirplaneHTML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * Auto input the seating arrangement available in AirPlane
	 * @return : return the 2D array of block of seats present in plane.
	 */
	private static int[][] getSeatsAuto() {
		int[][] res = {{3,2},{4,3},{2,3},{3,4}};
		System.out.println("{{3,2},{4,3},{2,3},{3,4}}");
		return res;
		//{ {3,2}, {4,3}, {2,3}, {3,4}};
		//{ [3,2], [4,3], [2,3], [3,4] };
	}


	/** 
	 * User input the seating arrangement available in AirPlane
	 * @return : return the 2D array of block of seats present in plane.
	 */
	private static int[][] getSeats() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter number of block in airplane: ");
		int n = scanner.nextInt();

		int res[][] = new int[n][n];
		System.out.println("Please enter space seperated columns and rows (Ex. 3 2 for 3 column and 2 rows) ");
		for (int i = 0; i < n; i++) {
			System.out.print("Enter for block no. " + (i + 1) + ": ");
			int w = scanner.nextInt();
			int b = scanner.nextInt();
			int[] block = {w, b};
			res[i] = block;
		}
		//print2D(res);
		return res;
	}

	/** 
	 * User input the number of passengers available in queue
	 * @return : return the 2D array of block of seats present in plane.
	 */
	private static int getPassenger() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter number of Passengers in queue: ");
		int n = scanner.nextInt();
		return n;
	}
	/** 
	 * print the 2D array input
	 * @param : return the 2D array of block of seats present in plane.
	 */
	private static void print2D(int mat[][]) {
		// Loop through all rows
		for (int i = 0; i < mat.length; i++) {
			// Loop through all elements of current row
			for (int j = 0; j < mat[i].length; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
	}

}
