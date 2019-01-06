package net.jayde.study.kodejava.example.tools;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CompileHello {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null,
                "src/org/kodejava/example/tools/Hello.java");

        System.out.println("Compile result code = " + result);
    }
}
