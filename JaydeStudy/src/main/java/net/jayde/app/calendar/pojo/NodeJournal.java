package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VJournal;

public class NodeJournal {

    private static Logger logger = LogManager.getLogger(NodeJournal.class.getName());
private String nodeName;
private VJournal journal;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VJournal getJournal() {
        return journal;
    }

    public void setJournal(VJournal journal) {
        this.journal = journal;
    }

    @Override
    public String toString() {
        return nodeName ;
    }

    public NodeJournal(VJournal journal) {
        this.journal = journal;
        nodeName="J:"+journal.getSummary();
    }
}
