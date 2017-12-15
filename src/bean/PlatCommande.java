/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ghassan
 */
@Entity
public class PlatCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prixTotal;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "platCommande")
    private List<PlatCommandeItems> platCommandeItemss;
    @OneToOne(mappedBy = "platCommande")
    private Facture facture;

    public PlatCommande() {
    }

    public PlatCommande(Long id, Client client) {
        this.id = id;
        this.client = client;
    }
    
    public PlatCommande(double prixTotal, Client client) {
        this.prixTotal = prixTotal;
        this.client = client;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<PlatCommandeItems> getPlatCommandeItemss() {
        return platCommandeItemss;
    }

    public void setPlatCommandeItemss(List<PlatCommandeItems> platCommandeItemss) {
        this.platCommandeItemss = platCommandeItemss;
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
        if (!(object instanceof PlatCommande)) {
            return false;
        }
        PlatCommande other = (PlatCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlatCommande{" + "id=" + id + ", prixTotal=" + prixTotal + ", client=" + client + '}';
    }
    
}
