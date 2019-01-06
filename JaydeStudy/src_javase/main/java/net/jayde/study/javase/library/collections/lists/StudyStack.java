package net.jayde.study.javase.basic.collections.lists;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections.lists
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/5/21 下午5:00
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/21 下午5:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class StudyStack extends StudyList {
    public void testAll() {
        add_Demo(new Stack(), new Stack<>());
        get_Demo(new Stack(), new Stack<>());
        remove_Demo(new Stack());
        clear_Demo(new Stack());
        isEmpty_Demo(new Stack());
        indexOf_Demo(new Stack());
        toArray_Demo(new Stack(), new Stack<>());
    }

    public static void main(String[] args) {
        StudyStack studyStack = new StudyStack();
        studyStack.testAll();
    }
}
