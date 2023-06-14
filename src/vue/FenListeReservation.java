package vue;


import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import modele.AccesDonnees;
import presentation.*;
import javafx.scene.layout.*;

public class FenListeReservation extends Stage {
	static private ObservableList<Reservation> lesReservations = FXCollections.observableArrayList();
	
	
	// les composants de la fenetre
	
	private BorderPane  		racine			= new BorderPane();
	private TableView<Reservation> 	tableReservations	= new TableView<Reservation>();
	private Button 				bnAjouter 		= new Button("Ajouter...");
	private Button 				bnModifier 		= new Button("Modifier...");
	
	
	// Supprimer et Fermer :
	
	private Button 				bnSupprimer 	= new Button("Supprimer");
	private Button 				bnFermer 		= new Button("Fermer");
	
	private HBox supFermerHBox = new HBox();
	
	
	// Des boites horizontal qui vont contenir les TextField et les Label ensemble.
	
	private HBox lastNameHBox = new HBox();
	private HBox firstNameHBox = new HBox();
	private HBox phoneNumberHBox = new HBox();
	private HBox reservationNumberHBox = new HBox();
	private HBox startDateHBox = new HBox();
	private HBox endDateHBox = new HBox();
	
	private TextField lastNameInput = new TextField();
	private TextField firstNameInput = new TextField();
	private TextField phoneNumberInput = new TextField();
	private TextField reservationNumberInput = new TextField();
	
	
	private DatePicker startDatePicker = new DatePicker();
	private DatePicker endDatePicker = new DatePicker();
	private Label startDateLabel = new Label("Date de début : ");
	private Label endDateLabel = new Label("Date de fin : ");
	
	private Label lastNameLabel = new Label("Nom :");
	private Label firstNameLabel = new Label("Prénom : ");
	private Label phoneNumberLabel = new Label("N° de téléphone : ");
	private Label reservationNumberLabel = new Label("N° de réservation : ");
	
	
	
	// un titre
	
	private Label searchTitle = new Label("Rechercher une réservation :");
	
	private Button bnSearch = new Button("Rechercher");
	
	// Une boite verticale qu'on mettra à droite qui contiendra les Hbox des champs de recherches.
	private VBox searchVBox = new VBox();
	
	
	// une boite pour juste la droite du borderPane
	
	private VBox rightVBox = new VBox();
	
	
	
	
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
		
		TableColumn<Reservation,String> colonne10 = new TableColumn<Reservation,String>("État");
		colonne10.setCellValueFactory(new PropertyValueFactory<Reservation, String>("state"));
		tableReservations.getColumns().add(colonne10);
		
		tableReservations.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		BooleanBinding rien = Bindings.equal(tableReservations.getSelectionModel().selectedIndexProperty(), -1);
		
		
		//Taille des éléments  : 
		
		
		bnAjouter.setPrefWidth(100);
		
		bnModifier.setPrefWidth(100);
		
		bnSupprimer.setPrefWidth(160);

		bnFermer.setPrefWidth(160);
		
		bnSearch.setPrefWidth(100);
		
		
		double tailleLabel = 150.0;
		
		reservationNumberLabel.setPrefWidth(tailleLabel);
		lastNameLabel.setPrefWidth(tailleLabel);
		firstNameLabel.setPrefWidth(tailleLabel);
		phoneNumberLabel.setPrefWidth(tailleLabel);
		startDateLabel.setPrefWidth(tailleLabel);
		endDateLabel.setPrefWidth(tailleLabel);
		
		
		// Padding et Margin : 
		
		searchVBox.setPadding(new Insets(40, 50, 50, 50));
		searchVBox.setSpacing(15);
		searchTitle.setPadding(new Insets(0, 0, 20, 0));
		
		supFermerHBox.setPadding(new Insets(50, 50, 50, 50));
		supFermerHBox.setSpacing(50);
		
		// Style : 
		
		searchTitle.setStyle("-fx-font-size:20 ; -fx-font-weight:BOLD");
		
		
		
		// Les fameuses boites pour les champs de recherche :
		
		lastNameHBox.getChildren().addAll(lastNameLabel,lastNameInput);
		firstNameHBox.getChildren().addAll(firstNameLabel,firstNameInput);
		phoneNumberHBox.getChildren().addAll(phoneNumberLabel,phoneNumberInput);
		reservationNumberHBox.getChildren().addAll(reservationNumberLabel,reservationNumberInput);
		startDateHBox.getChildren().addAll(startDateLabel,startDatePicker);
		endDateHBox.getChildren().addAll(endDateLabel,endDatePicker);
		
		
		searchVBox.getChildren().addAll(searchTitle,reservationNumberHBox,lastNameHBox,firstNameHBox,phoneNumberHBox, startDateHBox, endDateHBox, bnSearch);
		
		
		
		
		
		supFermerHBox.getChildren().addAll(bnSupprimer,bnFermer);
		
		rightVBox.getChildren().addAll(searchVBox,supFermerHBox);
		
		racine.setLeft(tableReservations);
		racine.setRight(rightVBox);
		
		
		//racine.getChildren().addAll(tableReservations,searchVBox,bnSupprimer, bnFermer);
		
		
		
		
		
		
		// Button Action
		
		bnSearch.setOnAction(e -> {
		    //variable temporaire pour réccupérer les inputs
		    String lastNameTemp;
		    String firstNameTemp;
		    String phoneNumberTemp;
		    Integer reservationNumberTemp = null;
		    LocalDate startDateTemp = null;
		    LocalDate endDateTemp = null;

		    String reservationNumberInputTemp = reservationNumberInput.getText();
		    lastNameTemp = lastNameInput.getText();
		    firstNameTemp = firstNameInput.getText();
		    phoneNumberTemp = phoneNumberInput.getText();
		    startDateTemp = startDatePicker.getValue();
		    endDateTemp = endDatePicker.getValue();
		    
		    if(startDateTemp != null && endDateTemp != null){
		        if(startDateTemp.isAfter(endDateTemp)) {
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Erreur de dates");
		            alert.setHeaderText(null);
		            alert.setContentText("La date de début ne peut pas être après la date de fin.");
		            alert.showAndWait();
		            return;
		        }
		    }

		    if (!reservationNumberInputTemp.isEmpty()) {
		        try {
		            reservationNumberTemp = Integer.parseInt(reservationNumberInputTemp);
		        } catch (NumberFormatException ex) {
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Erreur de format");
		            alert.setHeaderText(null);
		            alert.setContentText("Le champ 'reservationNumberInput' doit contenir uniquement des chiffres.");
		            alert.showAndWait();
		            reservationNumberInput.clear();
		            return;
		        }
		    }

		    if (lastNameTemp.isEmpty()) {
		        lastNameTemp = null;
		    }

		    if (firstNameTemp.isEmpty()) {
		        firstNameTemp = null;
		    }

		    if (phoneNumberTemp.isEmpty()) {
		        phoneNumberTemp = null;
		    }

		    //System.out.println(lastNameTemp+firstNameTemp+phoneNumberTemp);
		    
		    
		    
		    ArrayList<Reservation> newListe = AccesDonnees.searchReservations(reservationNumberTemp,lastNameTemp, firstNameTemp, phoneNumberTemp, startDateTemp, endDateTemp);
		    
		    if (newListe.isEmpty()) {
		    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Résultat vide");
	            alert.setHeaderText(null);
	            alert.setContentText("Il n'y a aucun résultat pour cette recherche avec ces filtres ");
	            alert.showAndWait();
	            reservationNumberInput.clear();
	            lastNameInput.clear();
	            firstNameInput.clear();
	            phoneNumberInput.clear();
	            
	            this.init(AccesDonnees.getLesReservations());
		    } else {
		    	this.init(newListe);
		    }
		    
		    
		    
		});


		
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
		
		//bnAjouter.setOnAction(e -> Principale.ouvrirNouvelReservation());
		
		//bnModifier.setOnAction(e -> Principale.ouvrirDetailReservation(tableReservations.getSelectionModel().getSelectedItem()));
		
		
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
