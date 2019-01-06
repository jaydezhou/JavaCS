package com.jayde.codesamples.jdk.lang.mainMethod;

import com.jayde.codesamples.jdk.AbstractCommandSample;

/**
 * Created by jayde on 2016-11-27 16:54.
 * 版权所有
 */
public class DemoOfMainMethod extends AbstractCommandSample {
    static String[] tips = {"代码示例DemoOfMainMethod", "程序入口为main", "简单输出helloWorld"};

    public static void main(String[] args) {
        DemoOfMainMethod demo = new DemoOfMainMethod();
        demo.setTips(tips);
        System.out.println("Hello,World");
    }
}
