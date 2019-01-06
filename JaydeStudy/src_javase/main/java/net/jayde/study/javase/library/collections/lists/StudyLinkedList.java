package net.jayde.study.javase.basic.collections.lists;

import lombok.extern.log4j.Log4j2;
import java.util.LinkedList;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections.lists
 * @ClassName: ${TYPE_NAME}
 * @Description: 学习LinkedList的用法
 * @Author: jayde
 * @CreateDate: 2018/5/21 上午11:13
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/21 上午11:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Log4j2
public class StudyLinkedList extends StudyList {

    public void testAll() {
        add_Demo(new LinkedList(), new LinkedList<>());
        get_Demo(new LinkedList(), new LinkedList<>());
        remove_Demo(new LinkedList());
        clear_Demo(new LinkedList());
        isEmpty_Demo(new LinkedList());
        indexOf_Demo(new LinkedList());
        toArray_Demo(new LinkedList(), new LinkedList<>());
    }

    public static void main(String[] args) {
        StudyLinkedList studyLinkedList = new StudyLinkedList();
        studyLinkedList.testAll();
    }

}
