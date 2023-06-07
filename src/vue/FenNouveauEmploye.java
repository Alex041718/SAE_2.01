package vue;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.binding.*;

public class FenNouveauEmploye extends Stage {
	// les composants du Scene Graph
	private GridPane			racine			= new GridPane();
	private HBox				zoneBoutons		= new HBox();
	private Label 				lblMatricule	= new Label("Matricule (*) :");
	private Label 				lblNom			= new Label("Nom employé (*) : ");
	private Label 				lblPoste		= new Label("Poste : ");
	private Label 				lblSuperieur	= new Label("Supérieur :");
	private Label 				lblEmbauche		= new Label("Date embauche : ");
	private Label 				lblSalaire		= new Label("Salaire : ");
	private Label 				lblEurosSalaire	= new Label("euros");
	private Label 				lblPrime		= new Label("Prime :");
	private Label 				lblEurosPrime	= new Label("euros");
	private Label 				lblDepartement	= new Label("Département (*) : ");
	private TextField			txtMatricule	= new TextField();
	private TextField			txtNom			= new TextField();
	private TextField			txtPoste		= new TextField();
	private ComboBox<String>	cbSuperieur		= new ComboBox<String>();
	private DatePicker			dpEmbauche		= new DatePicker();
	private TextField			txtSalaire		= new TextField();
	private TextField			txtPrime		= new TextField();
	private ComboBox<String>	cbDept			= new ComboBox<String>();
	private Button 				bnOK 			= new Button("OK");
	private Button 				bnAnnuler 		= new Button("Annuler");
	
	
	// constructeur : initialisation de la fenetre
	public FenNouveauEmploye(){		
		this.setTitle("Nouvel employé");
		this.sizeToScene();
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));	
	}
	
	// creation du contenu de la fenetre
	private Parent creerContenu() {
		dpEmbauche.setEditable(false);

		BooleanBinding manque = Bindings.or(
			Bindings.or(txtMatricule.textProperty().isEmpty(), txtNom.textProperty().isEmpty()),
			Bindings.equal(cbDept.getSelectionModel().selectedIndexProperty(), -1)
			);
		
		bnOK.setPrefWidth(100);
		
		bnAnnuler.setPrefWidth(100);
		
		
		// construction du Scene Graph
		zoneBoutons.getChildren().addAll(bnOK, bnAnnuler);
		zoneBoutons.setSpacing(10);
		racine.addRow(0,  lblMatricule, txtMatricule);
		racine.addRow(1,  lblNom, txtNom);
		racine.addRow(2,  lblPoste, txtPoste);
		racine.addRow(3,  lblSuperieur, cbSuperieur);
		racine.addRow(4,  lblEmbauche, dpEmbauche);
		racine.addRow(5,  lblSalaire, txtSalaire, lblEurosSalaire);
		racine.addRow(6,  lblPrime, txtPrime, lblEurosPrime);
		racine.addRow(7,  lblDepartement, cbDept);
		racine.add(zoneBoutons, 1, 10);
		racine.setHgap(10);
		racine.setVgap(15);
		racine.setPadding(new Insets(10));
		
		
		//Button Action
		
		bnAnnuler.setOnAction(e -> close());
		bnOK.setOnAction(e -> {
		
		});
		
		
		
		
		return racine;
	}
	
	public void init(ArrayList<Integer> lesSup, ArrayList<Integer> lesDep) {
		// initialise tous les champs du formulaire
		txtMatricule.clear();
		txtNom.clear();
		txtPoste.clear();
		dpEmbauche.setValue(LocalDate.now());
		txtSalaire.clear();
		txtPrime.clear();
		this.cbSuperieur.getItems().clear();
		for (int i=0; i<lesSup.size() ; i++) {
			this.cbSuperieur.getItems().add(""+lesSup.get(i));
		}
		this.cbSuperieur.getItems().add(0, "- aucun -");
		this.cbSuperieur.setValue(cbSuperieur.getItems().get(0));
		this.cbDept.getItems().clear();
		for (int i=0; i<lesDep.size() ; i++) {
			this.cbDept.getItems().add(""+lesDep.get(i));
		}
		this.cbDept.setValue(cbDept.getItems().get(0));
	}
	
}











