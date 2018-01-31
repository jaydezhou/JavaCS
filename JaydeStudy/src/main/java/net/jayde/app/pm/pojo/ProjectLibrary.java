package net.jayde.app.pm.pojo;

import java.util.List;
import java.util.ArrayList;

public class ProjectLibrary {
	List<ProjectGroup> groups = new ArrayList<ProjectGroup>();

	public List<ProjectGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ProjectGroup> groups) {
		this.groups = groups;
	}
}
