package AirlineSeating;

public class Seat {
	String seatType;
	int passengerNumber = -1;
	boolean isOccupied = false;
	
	public Seat(String type) {
		this.seatType = type;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}	
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public int getPassengerNumber() {
		return passengerNumber;
	}
	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	
	/**
	 * used to return color for HTML page
	 * @return : color as String
	 */
    public String getColor()
    {
        String color;
        switch (this.seatType) {
            case "A":
                color = "blue";
                break;
            case "W":
                color = "green";
                break;
            case "C":
                color = "red";
                break;
            default:
                color = "white";
                break;
        }

        return color;
    }

}
