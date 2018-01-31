package net.jayde.study.kodejava.example.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GettingScriptEngineByName {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();

        //
        // Obtain an instance of ScriptEngine using the engine name. For
        // example we get a JavaScript ScriptEngine instance.
        //
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            engine.eval("print('Hello World');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
