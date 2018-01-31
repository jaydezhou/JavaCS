package net.jayde.app.vcard;

import net.sourceforge.cardme.vcard.VCard;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class VcardLibrary {
    TreeMap<String, VcardCategory> treeMapCategories = new TreeMap<>();

    public TreeMap<String, VcardCategory> getTreeMapCategories() {
        return treeMapCategories;
    }

    public void setTreeMapCategories(TreeMap<String, VcardCategory> treeMapCategories) {
        this.treeMapCategories = treeMapCategories;
    }

    public VcardCategory getVcardsByKey(String key) {
        if (key == null) key = "";
//        key = key.trim();
        if (treeMapCategories.containsKey(key)) {
            return treeMapCategories.get(key);
        } else {
            VcardCategory newCategory = new VcardCategory();
            newCategory.setCategoryName(key.trim());
            treeMapCategories.put(key, newCategory);
            return newCategory;
        }
    }

    public void addVcard(VCard vcard) {
        if (vcard.getCategories() == null) {
            getVcardsByKey("").getListVcards().add(vcard);
        } else {
            if (vcard.getCategories().getCategories() == null) {
                getVcardsByKey("").getListVcards().add(vcard);
            } else {
                List<String> listC = vcard.getCategories().getCategories();
                if (listC.size() == 0) {
                    getVcardsByKey("").getListVcards().add(vcard);
                } else {
                    getVcardsByKey(listC.get(0).trim()).getListVcards().add(vcard);
                }
            }
        }
    }

    public String showAllCategories() {
        String allMessage = "";
        for (String key : treeMapCategories.keySet()) {
            VcardCategory category = treeMapCategories.get(key);
            allMessage = allMessage + "CategoryName=" + key + ",count=" +category.getListVcards().size()+ "\n{";
            for (VCard vcard:category.getListVcards()){
                allMessage=allMessage+vcard.getFN().getFormattedName()+",";
            }
            allMessage = allMessage+"}\n";
        }
        return allMessage;
    }
}
