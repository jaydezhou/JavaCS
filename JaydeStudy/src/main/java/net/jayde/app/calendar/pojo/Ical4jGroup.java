package net.jayde.app.calendar.pojo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VFreeBusy;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.component.VToDo;

public class Ical4jGroup {

  private static Logger logger = LogManager.getLogger(Ical4jGroup.class.getName());

  String urlString = "";


  Map<String,Ical4jGroup> sonGroups = new HashMap<>();

  List<VAlarm> sonAlarms = new LinkedList<>();
  List<VEvent> sonEvents = new LinkedList<>();
  List<VFreeBusy> sonFreebusys = new LinkedList<>();
  List<VJournal> sonJournals = new LinkedList<>();
  List<VToDo> sonTodos = new LinkedList<>();
  List<VTimeZone> sonTimezones = new LinkedList<>();

  public String getUrlString() {
    return urlString;
  }

  public void setUrlString(String urlString) {
    this.urlString = urlString;
  }

  public List<VAlarm> getSonAlarms() {
    return sonAlarms;
  }

  public void setSonAlarms(List<VAlarm> sonAlarms) {
    this.sonAlarms = sonAlarms;
  }

  public List<VFreeBusy> getSonFreebusys() {
    return sonFreebusys;
  }

  public void setSonFreebusys(List<VFreeBusy> sonFreebusys) {
    this.sonFreebusys = sonFreebusys;
  }

  public List<VTimeZone> getSonTimezones() {
    return sonTimezones;
  }

  public void setSonTimezones(List<VTimeZone> sonTimezones) {
    this.sonTimezones = sonTimezones;
  }

  public Map<String, Ical4jGroup> getSonGroups() {
    return sonGroups;
  }

  public void setSonGroups(Map<String, Ical4jGroup> sonGroups) {
    this.sonGroups = sonGroups;
  }


  public List<VEvent> getSonEvents() {
    return sonEvents;
  }

  public void setSonEvents(List<VEvent> sonEvents) {
    this.sonEvents = sonEvents;
  }

  public List<VJournal> getSonJournals() {
    return sonJournals;
  }

  public void setSonJournals(List<VJournal> sonJournals) {
    this.sonJournals = sonJournals;
  }

  public List<VToDo> getSonTodos() {
    return sonTodos;
  }

  public void setSonTodos(List<VToDo> sonTodos) {
    this.sonTodos = sonTodos;
  }

  @Override
  public String toString() {
    return  urlString ;
  }
}
