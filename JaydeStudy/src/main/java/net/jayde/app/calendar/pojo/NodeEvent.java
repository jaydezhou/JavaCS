package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VEvent;

public class NodeEvent {

  private static Logger logger = LogManager.getLogger(NodeEvent.class.getName());
  private String nodeName;
  private VEvent event;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VEvent getEvent() {
        return event;
    }

    public void setEvent(VEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return nodeName;
    }

    public NodeEvent(VEvent event) {
        this.event = event;
        nodeName = "E:"+event.getSummary().getValue();
    }
}
