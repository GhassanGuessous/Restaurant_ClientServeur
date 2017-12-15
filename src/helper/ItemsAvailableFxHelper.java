/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.ItemsAvailable;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author Ghassan
 */
public class ItemsAvailableFxHelper extends AbstractFxHelper<ItemsAvailable>{
    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "itemName"),
            new AbstractFxHelperItem("Prix", "prix")
        };
    }

    public ItemsAvailableFxHelper(TableView<ItemsAvailable> table, List<ItemsAvailable> list) {
        super(titres, table, list);
    }

    public ItemsAvailableFxHelper(TableView<ItemsAvailable> table) {
        super(titres, table);
    }
}
