package net.jayde.app.pm.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.pm.PmReadDb;
import net.jayde.app.pm.pojo.ProjectGroup;
import net.jayde.app.pm.pojo.ProjectLibrary;
import net.jayde.app.pm.pojo.ProjectObject;

public class PmExportToMd {

  private static Logger logger = LogManager.getLogger(PmExportToMd.class.getName());

  PmReadDb pmReadDb = new PmReadDb();
  ProjectLibrary library = pmReadDb.readByDb();

  public PmExportToMd() {
    for (ProjectGroup pg : library.getGroups()) {
      for (ProjectObject po : pg.getProjects()) {
        System.out.println("\n\n-----------------------------------------------------------------\n"+po);
        System.out.println(po.formatProjectMd());

      }
    }
  }

  public static void main(String[] args) {
    PmExportToMd pmExportToMd = new PmExportToMd();
  }
}
