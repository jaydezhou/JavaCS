package net.jayde.app.vcard;

import net.sourceforge.cardme.vcard.VCard;

import java.util.LinkedList;

public class VcardCategory {
    String CategoryName = "NULL";
    LinkedList<VCard> listVcards = new LinkedList<>();

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public LinkedList<VCard> getListVcards() {
        return listVcards;
    }

    public void setListVcards(LinkedList<VCard> listVcards) {
        this.listVcards = listVcards;
    }
}
