/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import bean.ItemsAvailable;
import bean.PlatCommande;
import client.CommandeEmission;
import client.Reception;
import helper.ItemsAvailableFxHelper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import serveur.Cuisinier;
import service.ClientFacade;
import service.ItemsAvailableFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Ghassan
 */
public class ClientViewController implements Initializable {

    Socket socket;
    ObjectInputStream ois;
    DataOutputStream dos;

    private ClientFacade clientFacade = new ClientFacade();
    private ItemsAvailableFacade availableFacade = new ItemsAvailableFacade();

    private List<ItemsAvailable> itemsAvailables = new ArrayList<>();
    private List<ItemsAvailable> commandeItemses = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    private ItemsAvailableFxHelper commandeItemsFxHelper;
    private ItemsAvailableFxHelper availableFxHelper;

    private PlatCommande platCommande;

    @FXML
    private ComboBox<String> typeCombo = new ComboBox<>();
    @FXML
    private TableView availableItemsTabView = new TableView();
    @FXML
    private TableView selectedItemsTabView = new TableView();
    @FXML
    private Button facture = new Button();

    private void initComboBox() {
        typeCombo.setItems(FXCollections.observableArrayList(Arrays.asList("--All--", "Boissons", "Desserts", "Plats")));
        typeCombo.getSelectionModel().select(0);
    }

    private void initHelper() {
        commandeItemsFxHelper = new ItemsAvailableFxHelper(selectedItemsTabView);
        availableFxHelper = new ItemsAvailableFxHelper(availableItemsTabView);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
        facture.setDisable(true);
        connexionCuisinier();
    }

    //Connexion avec serveur et remplissage du tableau des elements disponibles
    @FXML
    public void connexionCuisinier() {
        try {
            Cuisinier.createServerSocket();
            socket = new Socket("Localhost", 7777);

            ois = new ObjectInputStream(socket.getInputStream());
            itemsAvailables = (List<ItemsAvailable>) ois.readObject();
            availableFxHelper.setList(itemsAvailables);

        } catch (IOException | ClassNotFoundException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    public void searchByType(ActionEvent actionEvent) {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(typeCombo.getValue());
            dos.flush();

            Reception receptionThread = new Reception(socket, availableFxHelper);
            receptionThread.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void commander(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ObservableList<ItemsAvailable> items = commandeItemsFxHelper.getTable().getItems();
        if(!items.isEmpty()){
            commandeItemses = transform(items);

            CommandeEmission commandeEmissionThread = new CommandeEmission(socket, commandeItemses, platCommande);
            commandeEmissionThread.start();

            ois = new ObjectInputStream(socket.getInputStream());
            platCommande = (PlatCommande) ois.readObject();
            if (platCommande.getId() != null) {
                alertCommandeSuccess(actionEvent);
                facture.setDisable(false);
            }
            else alertCommandeFailure(actionEvent);
        }else{
            alertBlank(actionEvent);
        }
    }
    
    @FXML
    public void versFacture(ActionEvent actionEvent) throws IOException{
        Session.setAttribut(platCommande, "commande");
        Session.setAttribut(socket, "socket");
        ViewLauncher.forward(actionEvent, "ReglerFactureView.fxml", this.getClass());
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        ItemsAvailable available = availableFxHelper.getSelected();
        commandeItemses.add(available);
        commandeItemsFxHelper.setList(commandeItemses);
    }

    @FXML
    public void remove(ActionEvent actionEvent) {
        ItemsAvailable available = commandeItemsFxHelper.getSelected();
        commandeItemses.remove(available);
        commandeItemsFxHelper.setList(commandeItemses);
    }

    private void alertCommandeSuccess(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success !");
        alert.setContentText("Commande passée avec success");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertCommandeFailure(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur !");
        alert.setContentText("Commande echouée");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void alertBlank(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur !");
        alert.setContentText("Choisissez un element a commandé !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private List<ItemsAvailable> transform(ObservableList<ItemsAvailable> availables) {
        List<ItemsAvailable> list = new ArrayList<>();
        for (ItemsAvailable available : availables) {
            list.add(available);
        }
        return list;
    }
}
