package net.jayde.app.calendar.pojo;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VFreeBusy;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.Url;

public class Ical4jLibrary {

  private static Logger logger = LogManager.getLogger(Ical4jLibrary.class.getName());
  Ical4jGroup rootGroups = new Ical4jGroup();

  public Ical4jLibrary(String filename) {
    try {
      FileInputStream fin = new FileInputStream(filename);
      CalendarBuilder builder = new CalendarBuilder();
      Calendar calendar = null;
      try {
        calendar = builder.build(fin);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParserException e) {
        e.printStackTrace();
      }
      setCalendar(calendar);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Ical4jLibrary(File file) {
    try {
      FileInputStream fin = new FileInputStream(file);
      CalendarBuilder builder = new CalendarBuilder();
      Calendar calendar = null;
      try {
        calendar = builder.build(fin);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParserException e) {
        e.printStackTrace();
      }
      setCalendar(calendar);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public Ical4jLibrary(Calendar calendar) {
    setCalendar(calendar);
  }

  public void setCalendar(Calendar calendar) {
    rootGroups.setUrlString("");
    ComponentList<CalendarComponent> cl = calendar.getComponents();
    for (CalendarComponent cc : cl) {
      moveToGroup(cc);
      switch (cc.getName()) {
        case CalendarComponent.VALARM:
          VAlarm alarm = (VAlarm) cc;
          break;
        case CalendarComponent.VEVENT:
          VEvent event = (VEvent) cc;
          Url url = event.getUrl();
          if (url != null) {
            System.out.println(url.toString());
          }
          break;
        case CalendarComponent.VFREEBUSY:
          break;
        case CalendarComponent.VJOURNAL:
          VJournal journal = (VJournal) cc;
          System.out.println(journal.getUrl());
          break;
        case CalendarComponent.VTODO:
          VToDo toDo = (VToDo) cc;
          System.out.println(((VToDo) cc).getUrl());
          break;
        case CalendarComponent.VTIMEZONE:
          VTimeZone timeZone = (VTimeZone) cc;
          break;
        default:
          break;
      }
    }
    System.out.println(toString());
  }

  void moveToGroup(CalendarComponent cc) {
    if (cc instanceof VAlarm) {
      rootGroups.getSonAlarms().add((VAlarm) cc);
    }
    if (cc instanceof VFreeBusy) {
      rootGroups.getSonFreebusys().add((VFreeBusy) cc);
    }
    if (cc instanceof VTimeZone) {
      rootGroups.getSonTimezones().add((VTimeZone) cc);
    }
    if (cc instanceof VEvent) {
      VEvent event = (VEvent) cc;
      Url url = event.getUrl();
      if (url == null) {
        rootGroups.getSonEvents().add(event);
      } else {
        moveToSonGroup(url, cc);
      }
    }
    if (cc instanceof VJournal) {
      VJournal journal = (VJournal) cc;
      Url url = journal.getUrl();
      if (url == null) {
        rootGroups.getSonJournals().add(journal);
      } else {
        moveToSonGroup(url, cc);
      }
    }
    if (cc instanceof VToDo) {
      VToDo toDo = (VToDo) cc;
      Url url = toDo.getUrl();
      if (url == null) {
        rootGroups.getSonTodos().add(toDo);
      } else {
        moveToSonGroup(url, cc);
      }
    }
  }

  void moveToSonGroup(Url url, CalendarComponent cc) {
    String groupName = url.toString();
    Map<String, Ical4jGroup> groups = rootGroups.getSonGroups();
    Ical4jGroup selectedGroup = null;
    if (groups.containsKey(groupName)) {
      selectedGroup = groups.get(groupName);
    } else {
      selectedGroup = new Ical4jGroup();
      selectedGroup.setUrlString(groupName);
      groups.put(groupName, selectedGroup);
    }

    if (cc instanceof VEvent) {
      VEvent event = (VEvent) cc;
      selectedGroup.getSonEvents().add(event);
    }
    if (cc instanceof VJournal) {
      VJournal journal = (VJournal) cc;
      selectedGroup.getSonJournals().add(journal);
    }
    if (cc instanceof VToDo) {
      VToDo toDo = (VToDo) cc;
      selectedGroup.getSonTodos().add(toDo);
    }
  }

  @Override
  public String toString() {
    return "Ical4jLibrary{" +
            "rootGroups=" + rootGroups +
            '}';
  }

  public static void main(String[] args) {
    Ical4jLibrary library = new Ical4jLibrary("d:\\IdeaProjects\\日历MAC.ics");
    JFrame jf = new JFrame();
    jf.setSize(800,600);
    JPanel jPanel = new JPanel();
    JTree jTree = library.createJtree(library.rootGroups);
    jPanel.add(new JScrollPane(jTree), BorderLayout.CENTER);
    jf.getContentPane().add(jPanel);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }

  JTree createJtree(Ical4jGroup inputRooGroup){
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(inputRooGroup);
    for(String groupName:inputRooGroup.getSonGroups().keySet()){
      Ical4jGroup sonGroup = inputRooGroup.getSonGroups().get(groupName);
     DefaultMutableTreeNode sonNode = new DefaultMutableTreeNode(sonGroup);
     rootNode.add(sonNode);
     if(sonGroup.getSonEvents().size()>0){
       for(VEvent event:sonGroup.getSonEvents()){
         DefaultMutableTreeNode eNode = new DefaultMutableTreeNode(new NodeEvent(event));
         sonNode.add(eNode);
       }
     }
      if(sonGroup.getSonJournals().size()>0){
        for(VJournal journal:sonGroup.getSonJournals()){
          DefaultMutableTreeNode jNode = new DefaultMutableTreeNode(new NodeJournal(journal));
          sonNode.add(jNode);
        }
      }
      if(sonGroup.getSonTodos().size()>0){
        for(VToDo toDo:sonGroup.getSonTodos()){
          DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(new NodeTodo(toDo));
          sonNode.add(tNode);
        }
      }
    }

    if(inputRooGroup.getSonEvents().size()>0){
      for(VEvent event:inputRooGroup.getSonEvents()){
        DefaultMutableTreeNode eNode = new DefaultMutableTreeNode(new NodeEvent(event));
        rootNode.add(eNode);
      }
    }
    if(inputRooGroup.getSonJournals().size()>0){
      for(VJournal journal:inputRooGroup.getSonJournals()){
        DefaultMutableTreeNode jNode = new DefaultMutableTreeNode(new NodeJournal(journal));
        rootNode.add(jNode);
      }
    }
    if(inputRooGroup.getSonTodos().size()>0){
      for(VToDo toDo:inputRooGroup.getSonTodos()){
        DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(new NodeTodo(toDo));
        rootNode.add(tNode);
      }
    }
    if(inputRooGroup.getSonAlarms().size()>0){
      for(VAlarm alarm:inputRooGroup.getSonAlarms()){
        DefaultMutableTreeNode aNode = new DefaultMutableTreeNode(new NodeAlarm(alarm));
        rootNode.add(aNode);
      }
    }
    if(inputRooGroup.getSonFreebusys().size()>0){
      for(VFreeBusy freeBusy:inputRooGroup.getSonFreebusys()){
        DefaultMutableTreeNode fNode = new DefaultMutableTreeNode(new NodeFreebusy(freeBusy));
        rootNode.add(fNode);
      }
    }
    if(inputRooGroup.getSonTimezones().size()>0){
      for(VTimeZone timeZone:inputRooGroup.getSonTimezones()){
        DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(new NodeTimezone(timeZone));
        rootNode.add(tNode);
      }
    }

    JTree jTree = new JTree(rootNode);
    return jTree;
  }
}
