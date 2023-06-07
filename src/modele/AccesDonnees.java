package modele;

import java.util.ArrayList;
import java.time.LocalDate;
import presentation.*;

public class AccesDonnees {
	
	static private ArrayList<Reservation> 		lesReservations 	= new ArrayList<Reservation>();

	static public void connexion() { 
		
		
		lesReservations.add(new Reservation(1,"Dupont","Jean","0656345623",LocalDate.of(2023, 6, 10),LocalDate.of(2023, 10, 10)));
		
	}

	// méthodes de consultation : elles fournissent des listes de données
	
	static public ArrayList<Employe> getLesReservations() {
		return lesReservations;
	}

	static public ArrayList<Integer> getLesSuperieurs() {
		// fournit la liste des matricules
		ArrayList<Integer> lesSuperieurs = new ArrayList<Integer>();;
		for(int i=0 ; i<lesReservations.size() ; i++) {
			lesSuperieurs.add(lesReservations.get(i).getReservationNumber());
		}
		return lesSuperieurs;
	}
	
	// méthodes de mise à jour
	static public void ajouterEmploye(Employe e) {
		lesReservations.add(e);
	}
	static public void supprimerEmploye(Employe e) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesReservations.size()) {
			if (lesReservations.get(i).getReservationNumber()==e.getMatricule()){
				lesReservations.remove(i);
				trouve = true;
			}
			i++;
		}
	}
	static public void modifierEmploye(Employe e) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesEmployes.size()) {
			if (lesEmployes.get(i).getMatricule()==e.getMatricule()){
				lesEmployes.set(i, e);
				trouve = true;
			}
			i++;
		}
	}
}
