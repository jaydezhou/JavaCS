package net.jayde.app.pm.ui;

import net.jayde.app.pm.pojo.ProjectGroup;
import net.jayde.app.pm.pojo.ProjectLibrary;
import net.jayde.app.pm.pojo.ProjectObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class PmGroupsTree  extends JTree {
    ProjectLibrary library = null;

    public ProjectLibrary getLibrary() {
        return library;
    }

    public void setLibrary(ProjectLibrary inputLibrary) {
        library = inputLibrary;

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultTreeModel model = new DefaultTreeModel(root);

        for (ProjectGroup group: library.getGroups()) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(group);
            model.insertNodeInto(categoryNode,root,root.getChildCount());
            for (ProjectObject project:group.getProjects()){
                DefaultMutableTreeNode vcardNode = new DefaultMutableTreeNode(project);
                model.insertNodeInto(vcardNode,categoryNode,categoryNode.getChildCount());
            }
        }


        setModel(model);
    }
}
