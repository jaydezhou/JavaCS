package net.jayde.study.kodejava.example.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvaluatingScript {
    public static void main(String[] args) {
        //
        // Obtaining JavaScript' ScriptEngine from the ScriptManager
        // using the engine extension.
        //
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine js = manager.getEngineByExtension("js");

        try {
            //
            // Evaluating a simple script
            //
            js.eval("print('Hello World')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
