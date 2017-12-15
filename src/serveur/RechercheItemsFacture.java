/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import bean.Client;
import bean.Facture;
import bean.PlatCommande;
import bean.PlatCommandeItems;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ClientFacade;
import service.FactureFacade;
import service.PlatCommandeItemsFacade;

/**
 *
 * @author Ghassan
 */
public class RechercheItemsFacture extends Thread {

    private List<PlatCommandeItems> commandeItemses = new ArrayList<>();
    private List<Object> clientEtFacture = new ArrayList<>();
    private PlatCommandeItemsFacade itemsFacade = new PlatCommandeItemsFacade();
    private ClientFacade clientFacade = new ClientFacade();
    private FactureFacade factureFacade = new FactureFacade();
    private PlatCommande platCommande;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serverSocket;

    public RechercheItemsFacture(PlatCommande platCommande, ServerSocket serverSocket) {
        this.platCommande = platCommande;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            commandeItemses = itemsFacade.findByPlatCommande(platCommande.getId());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(commandeItemses);
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            clientEtFacture = (List<Object>) ois.readObject();
            clientFacade.edit((Client) clientEtFacture.get(0));
            factureFacade.create((Facture) clientEtFacture.get(1));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RechercheItemsFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
