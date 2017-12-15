/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class PlatCommandeItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int type; //1 Boisson, 2 Dessert, 3 plat
    private double prix;
    @ManyToOne
    private PlatCommande platCommande;

    public PlatCommandeItems() {
    }

    public PlatCommandeItems(String itemName, int type, double prix, PlatCommande platCommande) {
        this.itemName = itemName;
        this.type = type;
        this.prix = prix;
        this.platCommande = platCommande;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public PlatCommande getPlatCommande() {
        return platCommande;
    }

    public void setPlatCommande(PlatCommande platCommande) {
        this.platCommande = platCommande;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlatCommandeItems)) {
            return false;
        }
        PlatCommandeItems other = (PlatCommandeItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlatCommandeItems{" + "id=" + id + ", itemName=" + itemName + ", type=" + type + ", prix=" + prix + ", platCommande=" + platCommande + '}';
    }
    
    
}
