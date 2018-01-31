package net.jayde.app.calendar.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.component.VToDo;

public class NodeTodo {

    private static Logger logger = LogManager.getLogger(NodeTodo.class.getName());
private String nodeName;
private VToDo toDo;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public VToDo getToDo() {
        return toDo;
    }

    public void setToDo(VToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public String toString() {
        return  nodeName ;
    }

    public NodeTodo(VToDo toDo) {
        this.toDo = toDo;
        nodeName = "TD:"+toDo.getSummary();
    }
}
