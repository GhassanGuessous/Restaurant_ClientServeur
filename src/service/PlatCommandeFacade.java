/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.ItemsAvailable;
import bean.PlatCommande;
import bean.PlatCommandeItems;
import static bean.PlatCommandeItems_.platCommande;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class PlatCommandeFacade extends AbstractFacade<PlatCommande>{
    
    private ClientFacade clientFacade = new ClientFacade();
    private PlatCommandeItemsFacade itemsFacade = new PlatCommandeItemsFacade();
    
    public PlatCommandeFacade() {
        super(PlatCommande.class);
    }

    public PlatCommande createCommande(List<ItemsAvailable> itemsAvailables) {
        
        Long idClient = clientFacade.generateId("Client", "id");
        Long idCommande = generateId("PlatCommande", "id");
        
        Client client = new Client(idClient);
        clientFacade.create(client);
        
        PlatCommande platCommande = new PlatCommande(idCommande, client);
        create(platCommande);
        
        for (ItemsAvailable itemsAvailable : itemsAvailables) {
            PlatCommandeItems platCommandeItems = new PlatCommandeItems(itemsAvailable.getItemName(), itemsAvailable.getType(), 
                    itemsAvailable.getPrix(), platCommande);
            platCommande.setPrixTotal(platCommande.getPrixTotal() + platCommandeItems.getPrix());
            itemsFacade.create(platCommandeItems);
        }
        
        edit(platCommande);
        return platCommande;
    }
    
}
