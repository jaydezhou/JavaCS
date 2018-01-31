package net.jayde.app.vcard.ui;

import net.jayde.app.vcard.VcardCategory;
import net.jayde.app.vcard.VcardLibrary;
import net.sourceforge.cardme.vcard.VCard;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class VcardCategoriesTree extends JTree{
    VcardLibrary library = null;

    public VcardLibrary getLibrary() {
        return library;
    }

    public void setLibrary(VcardLibrary inputLibrary) {
        this.library = inputLibrary;

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultTreeModel model = new DefaultTreeModel(root);

        for (String key : library.getTreeMapCategories().keySet()) {
            VcardCategory category = library.getTreeMapCategories().get(key);
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(key);
            model.insertNodeInto(categoryNode,root,root.getChildCount());
            for (VCard vcard:category.getListVcards()){
                DefaultMutableTreeNode vcardNode = new DefaultMutableTreeNode(vcard.getFN().getFormattedName());
                model.insertNodeInto(vcardNode,categoryNode,categoryNode.getChildCount());
            }
        }


        setModel(model);
    }
}
