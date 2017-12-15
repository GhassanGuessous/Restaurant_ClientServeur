/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import bean.Client;
import bean.Facture;
import bean.PlatCommande;
import bean.PlatCommandeItems;
import helper.PlatCommandeItemsFxHelper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import serveur.Cuisinier;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class ReglerFactureViewController implements Initializable {

    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    private PlatCommande platCommande;
    private PlatCommandeItemsFxHelper itemsFxHelper;
    private List<PlatCommandeItems> commandeItemses = new ArrayList<>();
    private List<Object> clientEtFacture = new ArrayList<>();
    
    @FXML
    private TableView ItemsTabView = new TableView();
    @FXML
    private Label total = new Label();
    @FXML
    private TextField nom = new TextField();
    @FXML
    private TextField prenom = new TextField();
    @FXML
    private Button reglerFacture = new Button();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            platCommande = (PlatCommande) Session.getAttribut("commande");
            Cuisinier.createServerSocketFacture(platCommande);
            socket = new Socket("Localhost", 7777);
            
            initHelper();
            total.setText(platCommande.getPrixTotal() + " DH");
        } catch (IOException ex) {
            Logger.getLogger(ReglerFactureViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void initHelper() {
        try {
            itemsFxHelper = new PlatCommandeItemsFxHelper(ItemsTabView);
            
            ois = new ObjectInputStream(socket.getInputStream());
            commandeItemses = (List<PlatCommandeItems>) ois.readObject();
            itemsFxHelper.setList(commandeItemses);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReglerFactureViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void reglerFacture(ActionEvent actionEvent){
        if(!testerChamps()){
            alertChamps(actionEvent);
        }else{
            try {
                Client client = platCommande.getClient();
                client.setPrenom(prenom.getText());
                client.setNom(nom.getText());
                
                Facture facture = new Facture(new Date(), platCommande);
                clientEtFacture.add(client);
                clientEtFacture.add(facture);
                
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(clientEtFacture);
                oos.flush();
                alertSuccess(actionEvent);
                reglerFacture.setDisable(true);
            } catch (IOException ex) {
                Logger.getLogger(ReglerFactureViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    private void alertChamps(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides !");
        alert.setContentText("Veuillez remplire les champs !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertSuccess(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success !");
        alert.setContentText("Facture reglée avec succes !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    public boolean testerChamps(){
        if(nom.getText().equals("")) return false;
        else if(prenom.getText().equals("")) return false;
        else return true;
    }
}
