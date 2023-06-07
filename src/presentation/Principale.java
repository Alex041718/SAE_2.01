package presentation;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;
import vue.*;

public class Principale extends Application{
	static private FenNouveauEmploye fNouvEmp = new FenNouveauEmploye();
	static private FenDetailEmploye fDetailEmp = new FenDetailEmploye();
	static private FenListeEmployes fListeEmp = new FenListeEmployes();
	static private FenListeReservation fListeRes = new FenListeReservation();
	
	public void start(Stage f) throws Exception {
		AccesDonnees.connexion();
		fNouvEmp.initModality(Modality.APPLICATION_MODAL);
		fDetailEmp.initModality(Modality.APPLICATION_MODAL);
		fListeRes.init(AccesDonnees.getLesEmployes());
		fListeRes.show();
	} 
	
	static public void main(String[] args) { 
		Application.launch(args); 
	}
	
	// gestion des fenêtres
	static public void ouvrirNouvelEmploye() {
		fNouvEmp.init(AccesDonnees.getLesSuperieurs(), AccesDonnees.getLesDepartements());
		fNouvEmp.show();
	}
	static public void ouvrirDetailEmploye(Employe e) {
		fDetailEmp.init(AccesDonnees.getLesSuperieurs(), AccesDonnees.getLesDepartements(), e.getMatricule(), e.getNom(), e.getPoste(), e.getSuperieur(),e.getEmbauche(),e.getSalaire(), e.getPrime(), e.getDept());
		fDetailEmp.show();
	}
	
	// gestion des données : les modifications
	static public void ajouterEmploye(Employe e) {
		// actualiser l'ObservableList dans la fenetre liste des employes
		fListeEmp.ajouterEmploye(e);
		// enregistrer l'ajout
		AccesDonnees.ajouterEmploye(e);	
		
	}
	static public void modifierEmploye(Employe e) {
		// actualiser l'ObservableList dans la fenetre liste des employes
		fListeEmp.modifierEmploye(e);
		// enregistrer la modif
		AccesDonnees.modifierEmploye(e);	
	}
	static public void supprimerEmploye(Employe e) {
		// actualiser l'ObservableList dans la fenetre liste des employes
		fListeEmp.supprimerEmploye(e);
		// enregistrer la suppression
		AccesDonnees.supprimerEmploye(e);
	}

	// gestion des données : les consultations
	static public ArrayList<Integer> getLesSuperieurs(){
		return AccesDonnees.getLesSuperieurs();
	}
	static public ArrayList<Integer> getLesDepartements(){
		return AccesDonnees.getLesDepartements();
	}
}
