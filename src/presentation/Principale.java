package presentation;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;
import vue.*;

public class Principale extends Application{
	static private FenNouveauReservation fNouvRes = new FenNouveauReservation();
	static private FenDetailReservation fDetailRes = new FenDetailReservation();
	static private FenListeReservation fListeRes = new FenListeReservation();
	
	public void start(Stage f) throws Exception {
		AccesDonnees.connexion();
		fNouvRes.initModality(Modality.APPLICATION_MODAL);
		fDetailRes.initModality(Modality.APPLICATION_MODAL);
		fListeRes.init(AccesDonnees.getLesReservations());
		fListeRes.show();
	} 
	
	static public void main(String[] args) { 
		Application.launch(args); 
	}
	
	// gestion des fenêtres
	static public void ouvrirNouvelReservation() {
		fNouvRes.init(AccesDonnees.getLesReservationNumber());
		fNouvRes.show();
	}
	static public void ouvrirDetailReservation(Reservation r) {
		fDetailRes.init(AccesDonnees.getLesReservationNumber(), r.getReservationNumber(), r.getLastName(), r.getFirstName(), r.getPhoneNumber(),r.getStartDate(), r.getEndDate(), r.getCategorie(), r.getListChamber(), r.getNbOccupants());
		fDetailRes.show();
	}
	
	// gestion des données : les modifications
	static public void ajouterReservation(Reservation e) {
		// actualiser l'ObservableList dans la fenetre liste des Reservations
		fListeRes.ajouterReservation(e);
		// enregistrer l'ajout
		AccesDonnees.ajouterReservation(e);	
		
	}
	static public void modifierReservation(Reservation e) {
		// actualiser l'ObservableList dans la fenetre liste des Reservations
		fListeRes.modifierReservation(e);
		// enregistrer la modif
		AccesDonnees.modifierReservation(e);	
	}
	static public void supprimerReservation(Reservation e) {
		// actualiser l'ObservableList dans la fenetre liste des Reservations
		fListeRes.supprimerReservation(e);
		// enregistrer la suppression
		AccesDonnees.supprimerReservation(e);
	}

	// gestion des données : les consultations
	static public ArrayList<Integer> getLesSuperieurs(){
		return AccesDonnees.getLesSuperieurs();
	}
	static public ArrayList<Integer> getLesDepartements(){
		return AccesDonnees.getLesDepartements();
	}
}
