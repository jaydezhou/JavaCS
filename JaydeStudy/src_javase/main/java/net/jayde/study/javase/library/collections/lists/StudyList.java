package net.jayde.study.javase.basic.collections.lists;

import lombok.extern.log4j.Log4j2;
import net.jayde.study.javase.basic.collections.PersonUO;

import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections.lists
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/5/21 下午4:45
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/21 下午4:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Log4j2
public class StudyList {
    void add_Demo(List list1, List<String> list2) {

        //不用泛型
//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add("java");
        list1.add("world");
        list1.add(Integer.valueOf(1));
        list1.add(mike);
        //添加同一个对象
        list1.add("world");
        list1.add(mike);
        //只会删除查找到的第一个对象,如需全部删除，则需要多次remove
        list1.remove(mike);
        list1.remove(mike);

        //泛型
//        ArrayList<String> arrayList2 = new ArrayList();
        list2.add("java");
        list2.add("world");

    }

    void get_Demo(List list1, List<String> list2) {

        //不用泛型
//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add("java");
        list1.add("world");
        list1.add(Integer.valueOf(1));
        list1.add(mike);
        //添加同一个对象
        list1.add("world");
        list1.add(mike);
        //随机访问，速度最快
        log.warn("随机访问，速度最快");
        log.info(list1.get(0));
        log.info(list1.get(1));
        log.warn("for循环，随机访问，速度最快");
        for (int i = 0; i < list1.size(); i++) {
            log.info(list1.get(i));
        }

        //泛型
//        ArrayList<String> arrayList2 = new ArrayList();
        list2.add("java");
        list2.add("world");
        log.warn("for泛型循环");
        for (String str : list2) {
            log.info(str);
        }
    }

    void remove_Demo(List list1) {
//        ArrayList list1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add(mike);
        //添加同一个对象
        list1.add(mike);
        log.warn("remove后的列表长度");
        //只会删除查找到的第一个对象,如需全部删除，则需要多次remove
        log.info(list1.size());
        list1.remove(mike);
        log.info(list1.size());
        list1.remove(mike);
        log.info(list1.size());
    }

    void clear_Demo(List list1) {
//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add(mike);
        //添加同一个对象
        list1.add(mike);
        log.warn("clear后的列表长度");
        log.info(list1.size());
        list1.clear();
        log.info(list1.size());
    }

    void isEmpty_Demo(List list1) {
//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        log.warn("isEmpty判断列别是否为空");
        log.info(list1.isEmpty());
        list1.clear();
        log.info(list1.isEmpty());
    }

    void indexOf_Demo(List list1) {

//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add("java");
        list1.add("world");
        list1.add(Integer.valueOf(1));
        list1.add(mike);

        log.warn("indexOf 对象在列表中的定位");
        log.info(list1.indexOf(mike));
        log.info(list1.indexOf("world"));
        log.info(list1.indexOf("notexist"));
    }

    void toArray_Demo(List list1, List<String> list2) {

//        ArrayList arrayList1 = new ArrayList();
        PersonUO mike = new PersonUO("111", "mike");
        list1.add("java");
        list1.add("world");
        list1.add(Integer.valueOf(1));
        list1.add(mike);

        log.warn("toArray 列表转数组");
        Object[] objs = list1.toArray();
        for (int i = 0; i < objs.length; i++) {
            log.info(objs[i]);
        }

        log.warn("toArray 泛型列表转数组");
//        ArrayList<String> arrayList2 = new ArrayList();
        list2.add("java");
        list2.add("world");
        String[] obj2s = new String[list2.size()];
        list2.toArray(obj2s);
        for (String str : obj2s) {
            log.info(str);
        }
    }
}
