package net.jayde.app.vcard;

import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.VCardWriter;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.exceptions.VCardBuildException;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VcfImport {
//    public static String filename = "C:\\Users\\zhouyj\\iCloudDrive\\文档集\\3.技术文档集\\3.1）个人项目\\iCloud vCard(无头像).vcf";
    public static String filename = "d:\\IdeaProjects\\通讯录MAC.vcf";
    private File[] vcardFiles = null;
    private VCardEngine vcardEngine = null;

    /**
     * Creates a new TestParser.
     */
    public VcfImport() {
        vcardEngine = new VCardEngine();
    }

    /**
     * Sets the compatibility mode.
     *
     * @param compatMode
     */
    public void setCompatibilityMode(CompatibilityMode compatMode) {
        vcardEngine.setCompatibilityMode(compatMode);
    }

    /**
     * Retrieve all VCard files and then parse them.
     *
     * @return {@link List}<VCard>
     */
    public List importVCards() {
        List vcards = new ArrayList();
//        vcardFiles = getFiles();
        vcardFiles = new File[1];
        vcardFiles[0] = new File(filename);
        for (int i = 0; i < vcardFiles.length; i++) {
            try {
                List<VCard> vcardsi = vcardEngine.parseMultiple(vcardFiles[i]);
                vcards.addAll(vcardsi);
            } catch (IOException ioe) {
                System.err.println("Could not read vcard file: " + vcardFiles[i].getAbsolutePath());
                ioe.printStackTrace();
            } catch (VCardParseException e) {
                e.printStackTrace();
            }
        }

        return vcards;
    }

    /**
     * Opens a file chooser dialog to select VCard files.
     *
     * @return {@link File}[]
     */
    private File[] getFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select VCards");
        chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.home")));
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".vcf") || f.isDirectory();
            }

            public @Override
            String getDescription() {

                return "VCard Files";
            }
        });

        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        try {
            File[] files = chooser.getSelectedFiles(); // get the file
            return files;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Warning! Could not load the file(s)!", "Warning!", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public VcardLibrary parseFile(File file) {
        return parseFiles(new File[]{file});
    }

    public VcardLibrary parseFiles(File[] files) {
        List<VCard> vcards = new ArrayList();
        for (File file : files) {
            vcards.addAll(parseVcards(file));
        }
        VcardLibrary library = new VcardLibrary();
        for (VCard vcard : vcards) {
            library.addVcard(vcard);
        }
        return library;
    }

    public List<VCard> parseVcards(File file) {
        List<VCard> vcards = new ArrayList();
        try {
            List<VCard> vcardsi = vcardEngine.parseMultiple(file);
            vcards.addAll(vcardsi);
        } catch (IOException ioe) {
            System.err.println("Could not read vcard file: " + file.getAbsolutePath());
            ioe.printStackTrace();
        } catch (VCardParseException e) {
            e.printStackTrace();
        }

        return vcards;
    }

    /**
     * This is the main method. Here the TestParses in created and initialized.
     * A VCardWriter is created to write the imported vcards to the System.out so
     * we can see if everything got imported and written correctly.
     *
     * @param args
     */
    public static void main(String[] args) {
        VcardLibrary library = new VcardLibrary();

        VcfImport testParser = new VcfImport();
        testParser.setCompatibilityMode(CompatibilityMode.RFC2426);
        List vcards = testParser.importVCards();
        VCardWriter writer = new VCardWriter();
        for (int i = 0; i < vcards.size(); i++) {
            VCardImpl vcard = (VCardImpl) (vcards.get(i));
            library.addVcard(vcard);
            writer.setVCard(vcard);
            String vstring = null;
            try {
                vstring = writer.buildVCardString();
            } catch (VCardBuildException e) {
//                e.printStackTrace();
            }
            if (writer.hasErrors()) {
                List errors = vcard.getErrors();
                for (int j = 0; j < errors.size(); j++) {
//                    System.out.println(errors.get(j));
                }
            }
//            System.out.println(vstring);
        }
        System.out.println(library.showAllCategories());
        System.out.println("\n-- END --");
    }
}
