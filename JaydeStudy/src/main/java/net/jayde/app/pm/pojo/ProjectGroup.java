package net.jayde.app.pm.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProjectGroup {
  List<ProjectObject> projects = new ArrayList<ProjectObject>();
  String name = "";

  @Override
  public String toString() {
    return "PG:{" + name + '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ProjectObject> getProjects() {
    return projects;
  }

  public void setProjects(List<ProjectObject> projects) {
    this.projects = projects;
  }
}
