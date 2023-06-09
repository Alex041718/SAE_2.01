package vue;


import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.*;

public class FenListeReservation extends Stage {
	static private ObservableList<Reservation> lesReservations = FXCollections.observableArrayList();
	// les composants de la fenetre
	private AnchorPane  		racine			= new AnchorPane();
	private TableView<Reservation> 	tableReservations	= new TableView<Reservation>();
	private Button 				bnAjouter 		= new Button("Ajouter...");
	private Button 				bnModifier 		= new Button("Modifier...");
	private Button 				bnSupprimer 	= new Button("Supprimer");
	private Button 				bnFermer 		= new Button("Fermer");
	
	// constructeur : initialisation de la fenetre
	public FenListeReservation(){
		this.setTitle("Gestion des réservations");
		this.setResizable(true);
		//this.sizeToScene();
		this.setMinHeight(450);
		this.setMinWidth(500);
		this.setScene(new Scene(creerContenu()));	
	}
	
	// creation du Scene graph
	private Parent creerContenu() {
			
		TableColumn<Reservation,Integer> colonne1 = new TableColumn<Reservation,Integer>("N° de réservation");
		colonne1.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("reservationNumber"));	
		tableReservations.getColumns().add(colonne1);
		
		TableColumn<Reservation, String> colonne2 = new TableColumn<Reservation,String>("Nom");
		colonne2.setCellValueFactory(new PropertyValueFactory<Reservation, String>("lastName"));
		tableReservations.getColumns().add(colonne2);
		
		TableColumn<Reservation, String> colonne3 = new TableColumn<Reservation,String>("Prenom");
		colonne3.setCellValueFactory(new PropertyValueFactory<Reservation, String>("firstName"));
		tableReservations.getColumns().add(colonne3);
		
		TableColumn<Reservation,Integer> colonne4 = new TableColumn<Reservation,Integer>("N° de téléphone");
		colonne4.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("phoneNumber"));
		tableReservations.getColumns().add(colonne4);
		
		TableColumn<Reservation,LocalDate> colonne5 = new TableColumn<Reservation,LocalDate>("Début de période");
		colonne5.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("startDate"));
		tableReservations.getColumns().add(colonne5);
		
		TableColumn<Reservation,LocalDate> colonne6 = new TableColumn<Reservation,LocalDate>("Fin de période");
		colonne6.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("endDate"));
		tableReservations.getColumns().add(colonne6);
		
		TableColumn<Reservation, String> colonne7 = new TableColumn<Reservation,String>("Catégorie");
		colonne7.setCellValueFactory(new PropertyValueFactory<Reservation, String>("categorie"));
		tableReservations.getColumns().add(colonne7);
		
		TableColumn<Reservation, ArrayList<Integer>> colonne8 = new TableColumn<Reservation,ArrayList<Integer>>("Liste des chambres");
		colonne8.setCellValueFactory(new PropertyValueFactory<Reservation, ArrayList<Integer>>("listChamber"));
		tableReservations.getColumns().add(colonne8);
		
		TableColumn<Reservation,Integer> colonne9 = new TableColumn<Reservation,Integer>("N° d'occupants");
		colonne9.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("nbOccupants"));
		tableReservations.getColumns().add(colonne9);
				
		tableReservations.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		BooleanBinding rien = Bindings.equal(tableReservations.getSelectionModel().selectedIndexProperty(), -1);
		
		bnAjouter.setPrefWidth(100);
		
		bnModifier.setPrefWidth(100);
		
		bnSupprimer.setPrefWidth(100);

		bnFermer.setPrefWidth(100);

		AnchorPane.setTopAnchor(tableReservations, 10.0);
		AnchorPane.setLeftAnchor(tableReservations, 10.0);
		AnchorPane.setRightAnchor(tableReservations, 120.0);
		AnchorPane.setBottomAnchor(tableReservations, 10.0);
		AnchorPane.setTopAnchor(bnAjouter, 30.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setTopAnchor(bnModifier, 80.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setTopAnchor(bnSupprimer, 130.0);
		AnchorPane.setRightAnchor(bnSupprimer, 10.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		racine.getChildren().addAll(tableReservations, bnAjouter, bnModifier, bnSupprimer, bnFermer);
		
		// Button Action
		
		bnFermer.setOnAction(e -> System.exit(0));
		
		BooleanBinding nothing = Bindings.equal(-1,tableReservations.getSelectionModel().selectedIndexProperty());
		
		bnSupprimer.disableProperty().bind(Bindings.when(nothing).then(true).otherwise(false));
		bnModifier.disableProperty().bind(Bindings.when(nothing).then(true).otherwise(false));
		
		bnSupprimer.setOnAction(e -> {
				
				Alert alertSuppression = new Alert(AlertType.CONFIRMATION,
						"On supprime ?",
						ButtonType.YES,
						ButtonType.NO);
				
				alertSuppression.setTitle("Supression d'un employé");
				
				Optional<ButtonType> result = alertSuppression.showAndWait();
				
				if (result.isPresent() && result.get() == ButtonType.YES) {
					Principale.supprimerReservation(tableReservations.getSelectionModel().getSelectedItem());
				}
				
				}
				
		);
		
		bnAjouter.setOnAction(e -> Principale.ouvrirNouvelReservation());
		
		bnModifier.setOnAction(e -> Principale.ouvrirDetailReservation(tableReservations.getSelectionModel().getSelectedItem()));
		
		
		return racine;
	}
	
	public void init(ArrayList<Reservation> liste) {
		lesReservations.clear();
		for (int i=0; i<liste.size() ; i++) {
			lesReservations.add(liste.get(i));
		}
		tableReservations.setItems(lesReservations);

	}
	public void ajouterReservation(Reservation e) {
		lesReservations.add(e);
	}
	public void modifierReservation(Reservation e) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesReservations.size()) {
			if (lesReservations.get(i).getReservationNumber()==e.getReservationNumber()){
				lesReservations.set(i, e);
				trouve = true;
			}
			i++;
		}
	}
	public void supprimerReservation(Reservation e) {
		lesReservations.remove(e);
	}
	
	
	
	
	
}
