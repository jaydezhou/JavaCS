package net.jayde.app.music.ui;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.pojo.MusicPerson;

public class MusicLibraryTree extends JTree {

  private static Logger logger = LogManager.getLogger(MusicLibraryTree.class.getName());
  MusicLibrary library = null;

  public MusicLibrary getLibrary() {
    return library;
  }

  public void setLibrary(MusicLibrary inputLibrary) {
    library = inputLibrary;

    MusicGroup rootGroup = new MusicGroup("0", "root");
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootGroup);
    DefaultTreeModel model = new DefaultTreeModel(root);
    if (library.getGroups().size() > 0) {
      for (MusicGroup group : library.getGroups()) {
        cycleGroupTree(model, group, root);
      }
    }
    setModel(model);
  }

  private void cycleGroupTree(DefaultTreeModel model, MusicGroup currentGroup, DefaultMutableTreeNode preGroupNode) {
    DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(currentGroup);
    model.insertNodeInto(groupNode, preGroupNode, preGroupNode.getChildCount());
    if (currentGroup.getSonGroups().size() > 0) {
      for (MusicGroup group : currentGroup.getSonGroups()) {
        cycleGroupTree(model, group, groupNode);
      }
    }
    for (MusicPerson person : currentGroup.getSonPersons()) {
      DefaultMutableTreeNode personNode = new DefaultMutableTreeNode(person);
      model.insertNodeInto(personNode, groupNode, groupNode.getChildCount());
      for (MusicFavourite favourite : person.getFavouriteSet()) {
        DefaultMutableTreeNode favouriteNode = new DefaultMutableTreeNode(favourite);
        model.insertNodeInto(favouriteNode, personNode, personNode.getChildCount());
      }
    }
  }
}
