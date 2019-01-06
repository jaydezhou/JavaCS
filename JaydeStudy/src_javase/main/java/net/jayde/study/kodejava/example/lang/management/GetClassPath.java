package net.jayde.study.kodejava.example.lang.management;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class GetClassPath {
    public static void main(String[] args) {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String classPath = bean.getClassPath();
        System.out.println("classPath = " + classPath);
    }
}
