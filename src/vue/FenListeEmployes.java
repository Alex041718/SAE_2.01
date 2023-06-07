package vue;

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

public class FenListeEmployes extends Stage {
	static private ObservableList<Employe> lesEmployes = FXCollections.observableArrayList();
	// les composants de la fenetre
	private AnchorPane  		racine			= new AnchorPane();
	private TableView<Employe> 	tableEmployes	= new TableView<Employe>();
	private Button 				bnAjouter 		= new Button("Ajouter...");
	private Button 				bnModifier 		= new Button("Modifier...");
	private Button 				bnSupprimer 	= new Button("Supprimer");
	private Button 				bnFermer 		= new Button("Fermer");
	
	// constructeur : initialisation de la fenetre
	public FenListeEmployes(){
		this.setTitle("Gestion des employés");
		this.setResizable(true);
		//this.sizeToScene();
		this.setMinHeight(450);
		this.setMinWidth(500);
		this.setScene(new Scene(creerContenu()));	
	}
	
	// creation du Scene graph
	private Parent creerContenu() {
			
		TableColumn<Employe,Integer> colonne1 = new TableColumn<Employe,Integer>("Matricule");
		colonne1.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("matricule"));	
		tableEmployes.getColumns().add(colonne1);
		TableColumn<Employe, String> colonne2 = new TableColumn<Employe,String>("Nom");
		colonne2.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
		tableEmployes.getColumns().add(colonne2);
		TableColumn<Employe, String> colonne3 = new TableColumn<Employe,String>("Poste");
		colonne3.setCellValueFactory(new PropertyValueFactory<Employe, String>("poste"));
		tableEmployes.getColumns().add(colonne3);
		TableColumn<Employe,Integer> colonne4 = new TableColumn<Employe,Integer>("Département");
		colonne4.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("dept"));
		tableEmployes.getColumns().add(colonne4);
				
		tableEmployes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		BooleanBinding rien = Bindings.equal(tableEmployes.getSelectionModel().selectedIndexProperty(), -1);
		
		bnAjouter.setPrefWidth(100);
		
		bnModifier.setPrefWidth(100);
		
		bnSupprimer.setPrefWidth(100);

		bnFermer.setPrefWidth(100);

		AnchorPane.setTopAnchor(tableEmployes, 10.0);
		AnchorPane.setLeftAnchor(tableEmployes, 10.0);
		AnchorPane.setRightAnchor(tableEmployes, 120.0);
		AnchorPane.setBottomAnchor(tableEmployes, 10.0);
		AnchorPane.setTopAnchor(bnAjouter, 30.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setTopAnchor(bnModifier, 80.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setTopAnchor(bnSupprimer, 130.0);
		AnchorPane.setRightAnchor(bnSupprimer, 10.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		racine.getChildren().addAll(tableEmployes, bnAjouter, bnModifier, bnSupprimer, bnFermer);
		
		// Button Action
		
		bnFermer.setOnAction(e -> System.exit(0));
		
		BooleanBinding nothing = Bindings.equal(-1,tableEmployes.getSelectionModel().selectedIndexProperty());
		
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
					Principale.supprimerEmploye(tableEmployes.getSelectionModel().getSelectedItem());
				}
				
				}
				
		);
		
		bnAjouter.setOnAction(e -> Principale.ouvrirNouvelEmploye());
		
		bnModifier.setOnAction(e -> Principale.ouvrirDetailEmploye(tableEmployes.getSelectionModel().getSelectedItem()));
		
		
		return racine;
	}
	
	public void init(ArrayList<Employe> liste) {
		lesEmployes.clear();
		for (int i=0; i<liste.size() ; i++) {
			lesEmployes.add(liste.get(i));
		}
		tableEmployes.setItems(lesEmployes);

	}
	public void ajouterEmploye(Employe e) {
		lesEmployes.add(e);
	}
	public void modifierEmploye(Employe e) {
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
	public void supprimerEmploye(Employe e) {
		lesEmployes.remove(e);
	}
	
	
	
	
	
}
