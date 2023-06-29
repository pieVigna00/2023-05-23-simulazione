package it.polito.tdp.baseball;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.baseball.model.Grado;
import it.polito.tdp.baseball.model.Model;
import it.polito.tdp.baseball.model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    
    
    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    	txtResult.setText("Ci sono "+model.getConnesse()+"componenti connesse");
    }

    
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	try {
    	String salario=this.txtSalary.getText();
    	int salarioNum=Integer.parseInt(salario);
    	String anno= this.txtYear.getText();
    	int annoNum= Integer.parseInt(anno);
    	if(annoNum<1871 || annoNum>2019) {
    		txtResult.setText("Devi inserire un anno compreso tra il 1871 eil 2019");
    	}
    	this.model.buildGraph(annoNum, salarioNum* 1000000);
    	txtResult.setText("Grafo creato con successo \n");
    	txtResult.appendText("Il grafo ha "+model.getNumVertici()+" vertici \n");
    	txtResult.appendText("Il grafo ha "+model.getNumArchi()+" archi \n");
    	}catch(Exception e) {
    		e.printStackTrace();
    		txtResult.setText("Devi inserire un valore numerico valido per l'anno e uno valido per il salario");
    	}
    }
    

    
    @FXML
    void doDreamTeam(ActionEvent event) {
    }

    
    @FXML
    void doGradoMassimo(ActionEvent event) {
    	txtResult.setText("Il vertice di grado max è : " +model.getGradoMax()+"\n");
    	txtResult.appendText("Con grado : "+model.getValoreGradoMax());
    }

    
    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
