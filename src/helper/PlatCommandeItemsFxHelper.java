/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.PlatCommandeItems;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Ghassan
 */
public class PlatCommandeItemsFxHelper extends AbstractFxHelper<PlatCommandeItems>{
    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "itemName"),
            new AbstractFxHelperItem("Prix", "prix")
        };
    }

    public PlatCommandeItemsFxHelper(TableView<PlatCommandeItems> table, List<PlatCommandeItems> list) {
        super(titres, table, list);
    }

    public PlatCommandeItemsFxHelper(TableView<PlatCommandeItems> table) {
        super(titres, table);
    }
    
}
