package modele;

//truc v2




import java.util.ArrayList;
import java.time.LocalDate;
import presentation.*;

public class AccesDonnees {
	
	static private ArrayList<Reservation> 		lesReservations 	= new ArrayList<Reservation>();

	static public void connexion() { 
		
		ArrayList<Integer> listeChambre1 = new ArrayList<Integer>();
		listeChambre1.add(102);
		listeChambre1.add(103);
		lesReservations.add(new Reservation(1, "Dupont", "Jean", "0656345623", LocalDate.of(2023, 6, 10), LocalDate.of(2023, 10, 10), "Truc", listeChambre1, 4));
		
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
