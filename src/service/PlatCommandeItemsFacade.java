/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.PlatCommandeItems;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class PlatCommandeItemsFacade extends AbstractFacade<PlatCommandeItems>{
    
    public PlatCommandeItemsFacade() {
        super(PlatCommandeItems.class);
    }

    public List<PlatCommandeItems> findByPlatCommande(Long id) {
        return getEntityManager().createQuery("SELECT i FROM PlatCommandeItems i WHERE i.platCommande.id = " + id).getResultList();
    }
    
}
