package net.jayde.app.ical4jVcard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.VCardBuilder;

public class Ical4jVcfImport {
  public static String filename = "d:\\IdeaProjects\\通讯录MAC.vcf";
  private static Logger logger = LogManager.getLogger(Ical4jVcfImport.class.getName());

  public Ical4jVcfImport() {
//    File file = new File(filename);

      File file = new File(filename);

      try {
          VCardBuilder builder = new VCardBuilder(new FileReader(file));
          List<VCard> cards = builder.buildAll();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ParserException e) {
          e.printStackTrace();
      }
//      assertEquals(2, cards.size());
//      assertEquals("Frank Dawson",cards.get(0).getProperty(Id.FN).getValue());
//      assertEquals("Tim Howes",cards.get(1).getProperty(Id.FN).getValue());
  }

  public static void main(String[] args) {
    Ical4jVcfImport ical4jVcfImport = new Ical4jVcfImport();
  }

}
