package net.jayde.study.javase.basic.collections.lists;

import java.util.Stack;
import java.util.Vector;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections.lists
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/5/21 下午5:03
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/21 下午5:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class StudyVector extends StudyList{

    public void testAll() {
        add_Demo(new Vector(), new Vector<>());
        get_Demo(new Vector(), new Vector<>());
        remove_Demo(new Vector());
        clear_Demo(new Vector());
        isEmpty_Demo(new Vector());
        indexOf_Demo(new Vector());
        toArray_Demo(new Vector(), new Vector<>());
    }

    public static void main(String[] args) {
        StudyVector studyStack = new StudyVector();
        studyStack.testAll();
    }
}
