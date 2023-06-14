package modele;

//truc v2

import java.util.ArrayList;
import java.time.LocalDate;
import presentation.*;

public class AccesDonnees {
	
	static private ArrayList<Reservation> 		lesReservations 	= new ArrayList<Reservation>();

	static public void connexion() { 
		
		ArrayList<Integer> listeChambre1 = new ArrayList<Integer>();
		String close = "Close";
		String valided = "Validée";
		String arrived = "Arrivée enregistrée";
		
		listeChambre1.add(102);
		listeChambre1.add(103);
		lesReservations.add(new Reservation(1, "Dupont", "Jean", "0656345623", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(2, "Dupont", "Hugo", "0656345623", LocalDate.of(2023, 12, 20), LocalDate.of(2023, 12, 26), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(3, "Dupont", "Hagrid", "0656345623", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(4, "Le Marec", "Alexandre", "0656345623", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(5, "Dupont", "Gilbert", "0656345623", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(6, "Dupont", "Aude", "0656345623", LocalDate.of(2022, 6, 10), LocalDate.of(2022, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(7, "Dupont", "Cathrine", "0656345623", LocalDate.of(2022, 6, 10), LocalDate.of(2022, 10, 10), "Truc", listeChambre1, 4, close));
		lesReservations.add(new Reservation(8, "Dupont", "Christophe", "0656345623", LocalDate.of(2022, 10, 11), LocalDate.of(2022, 10, 15), "Truc", listeChambre1, 4, close));
		
	}
	
	// méthode de Recherche par paramètre
	
	
	
	
	static public ArrayList<Reservation> searchReservations(Integer reservationNumber, String lastName, String firstName, String phoneNumber, LocalDate startDate, LocalDate endDate) {
	    ArrayList<Reservation> matchingReservations = new ArrayList<Reservation>();
	    
	    for(Reservation r : lesReservations) {
	        boolean matches = true;
	        
	        if (reservationNumber != null && !(r.getReservationNumber() == reservationNumber)) {
	            matches = false;
	        }
	        if (lastName != null && !r.getLastName().startsWith(lastName)) {
	            matches = false;
	        }
	        if (firstName != null && !r.getFirstName().startsWith(firstName)) {
	            matches = false;
	        }
	        if (phoneNumber != null && !r.getPhoneNumber().startsWith(phoneNumber)) {
	            matches = false;
	        }
	        if (startDate != null && (r.getStartDate().isBefore(startDate) || r.getStartDate().isAfter(endDate))) {
	            matches = false;
	        }
	        if (endDate != null && (r.getEndDate().isBefore(startDate) || r.getEndDate().isAfter(endDate))) {
	            matches = false;
	        }
	        
	        if(matches) {
	            matchingReservations.add(r);
	        }
	    }
	    
	    return matchingReservations;
	}


	// méthodes de consultation : elles fournissent des listes de données
	
	static public ArrayList<Reservation> getLesReservations() {
		return lesReservations;
	}
	
	static public ArrayList<Integer> getLesReservationNumber() {
		// fournit la liste des ReservationNumber
		ArrayList<Integer> lesReservationNumber = new ArrayList<Integer>();;
		for(int i=0 ; i<lesReservations.size() ; i++) {
			lesReservationNumber.add(lesReservations.get(i).getReservationNumber());
		}
		return lesReservationNumber;
	}
	
	
	// méthodes de mise à jour
	static public void ajouterReservation(Reservation r) {
		lesReservations.add(r);
	}
	static public void supprimerReservation(Reservation r) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesReservations.size()) {
			if (lesReservations.get(i).getReservationNumber()==r.getReservationNumber()){
				lesReservations.remove(i);
				trouve = true;
			}
			i++;
		}
	}
	static public void modifierReservation(Reservation r) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesReservations.size()) {
			if (lesReservations.get(i).getReservationNumber()==r.getReservationNumber()){
				lesReservations.set(i, r);
				trouve = true;
			}
			i++;
		}
	}
}
