package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VTimeZone;

public class NodeTimezone {

    private static Logger logger = LogManager.getLogger(NodeTimezone.class.getName());
private String nodeName;
private VTimeZone timeZone;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VTimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(VTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return  nodeName ;
    }

    public NodeTimezone(VTimeZone timeZone) {
        this.timeZone = timeZone;
        nodeName = "TZ:"+timeZone.getTimeZoneUrl();
    }
}
