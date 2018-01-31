package net.jayde.app.vcard.ui;

import net.sourceforge.cardme.vcard.VCard;

import java.util.Observable;

public class SelectVcard extends Observable {
    private VCard currentVcard = null;

    public VCard getCurrentVcard() {
        return currentVcard;
    }

    public void setCurrentVcard(VCard currentVcard) {
        this.currentVcard = currentVcard;
    }


}
