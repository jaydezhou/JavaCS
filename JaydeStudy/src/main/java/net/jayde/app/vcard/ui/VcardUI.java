package net.jayde.app.vcard.ui;

import net.jayde.app.baseui.baseLibraryFrame.BaseSwingLibraryPanel;
import net.jayde.app.vcard.VcardLibrary;
import net.jayde.app.vcard.VcfImport;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VcardUI extends BaseSwingLibraryPanel {
    VcardCategoriesTree categoriesTree = new VcardCategoriesTree();

    public VcardUI() {
        super();

        VcfImport vcfImport = new VcfImport();
        VcardLibrary library = vcfImport.parseFile(new File(VcfImport.filename));
        categoriesTree.setLibrary(library);

        tabsTree.addTab("Categories", new JScrollPane(categoriesTree));
    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(100, 100, 800, 600);
        jFrame.setContentPane(new VcardUI());
        jFrame.setVisible(true);
    }
}
