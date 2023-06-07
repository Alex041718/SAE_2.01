package presentation;

import java.time.LocalDate;

public class Reservation {
    private int reservationNumber;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Integer> listChamber;
    private int nbOccupants;

   public Reservation(int reservationNumber, String lastName, String firstName, String phoneNumber,
			LocalDate startDate, LocalDate endDate, String categorie, ArrayList<Integer> listChamber, int nbOccupants) {
		this.reservationNumber = reservationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categorie = categorie;
		this.listChamber = listChamber;
		this.nbOccupants = nbOccupants;
	}

    // Getters and Setters
    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    

    @Override
    public String toString() {
        return "Reservation #" + reservationNumber + ": " + firstName + " " + lastName + ", Tel: " + phoneNumber +
                ", from " + startDate + " to " + endDate;
    }
}
