/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import bean.ItemsAvailable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ItemsAvailableFacade;

/**
 *
 * @author Ghassan
 */
public class Recherche extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private ObjectOutputStream oos;
    
    private ItemsAvailableFacade availableFacade = new ItemsAvailableFacade();
    private List<ItemsAvailable> itemsAvailables = new ArrayList<>();
    
    int type = 0;
    
    public Recherche(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                String typeRecu = dataInputStream.readUTF();

                switch (typeRecu) {
                    case "--All--" :
                        type = 0;
                        break;
                    case "Boissons":
                        type = 1;
                        break;
                    case "Desserts":
                        type = 2;
                        break;
                    case "Plats":
                        type = 3;
                        break;
                }
                itemsAvailables = availableFacade.findByType(type);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(itemsAvailables);
                oos.flush();
            } catch (IOException ex) {
                Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}
