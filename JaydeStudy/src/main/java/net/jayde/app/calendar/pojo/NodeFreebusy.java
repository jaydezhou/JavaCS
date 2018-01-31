package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VFreeBusy;

public class NodeFreebusy {

    private static Logger logger = LogManager.getLogger(NodeFreebusy.class.getName());
private String nodeName;
private VFreeBusy freeBusy;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VFreeBusy getFreeBusy() {
        return freeBusy;
    }

    public void setFreeBusy(VFreeBusy freeBusy) {
        this.freeBusy = freeBusy;
    }

    @Override
    public String toString() {
        return nodeName ;
    }

    public NodeFreebusy(VFreeBusy freeBusy) {
        this.freeBusy = freeBusy;
        nodeName = "F:"+freeBusy.getUid();
    }
}
