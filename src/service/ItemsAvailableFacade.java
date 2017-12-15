/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ItemsAvailable;
import java.util.List;

/**
 *
 * @author Ghassan
 */
public class ItemsAvailableFacade extends AbstractFacade<ItemsAvailable>{
    
    public ItemsAvailableFacade() {
        super(ItemsAvailable.class);
    }
    
    public List<ItemsAvailable> findByType(int type){
        if(type != 0)
            return getEntityManager().createQuery("SELECT i FROM ItemsAvailable i WHERE i.type = " + type).getResultList();
        return findAll();
    }
}
