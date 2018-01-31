package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VAlarm;

public class NodeAlarm {

  private static Logger logger = LogManager.getLogger(NodeAlarm.class.getName());
  private String nodeName;
  private VAlarm alarm;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VAlarm getAlarm() {
        return alarm;
    }

    public void setAlarm(VAlarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return nodeName ;
    }

    public NodeAlarm(VAlarm alarm) {
        this.alarm = alarm;
        nodeName = "A:"+alarm.getSummary();
    }
}
