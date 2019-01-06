package net.jayde.study.javase.basic.collections.lists;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections.lists
 * @ClassName: ${TYPE_NAME}
 * @Description: 学习ArrayList的用法
 * @Author: jayde
 * @CreateDate: 2018/5/19 上午12:41
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/19 上午12:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Log4j2
public class StudyArrayList extends StudyList {

    public void testAll() {
        add_Demo(new ArrayList(), new ArrayList<>());
        get_Demo(new ArrayList(), new ArrayList<>());
        remove_Demo(new ArrayList());
        clear_Demo(new ArrayList());
        isEmpty_Demo(new ArrayList());
        indexOf_Demo(new ArrayList());
        toArray_Demo(new ArrayList(), new ArrayList<>());
    }

    public static void main(String[] args) {
        StudyArrayList studyArrayList = new StudyArrayList();
        studyArrayList.testAll();
    }
}

/**
 * a、对于任何一个集合来说，集合中存放的是对象的引用，而不是对象本身。
 * b、ArrayList底层采用数组实现，当使用不带参数的构成方法生成ArrayList对象的时候，实际上会在底层生成一个长度为10的Object类型数组。简单来说ArrayList内部实现是数组。
 * c、如果增加的元素个数超过了10个，那么ArrayList底层会新生成一个数组，长度为原来数组的1.5倍+1，然后将原数组的内容复制到新数组中，并且后续增加的内容都会放到新数组当中，当新数组无法容纳增加的元素时，重复该过程。集合中不能放入原生数据类型，只能放置对象的引用，我们需要使用原生数据类型的包装类才能加入到集合当中。
 * ArrayList的本质就是数组，ArrayList就是对数组进行动态的扩展，其add, get , remove 等等操作就是对数组的操作。ArrayList的一些特性都来源于数组：有序、元素可重复、插入慢、 索引快 等等一系列的属性
 * <p>
 * ArrayList的优点:
 * a、ArrayList底层以数组实现，是一种随机访问模式，再加上它实现了RandomAccess接口，因此查找速度快；
 * b、ArrayList在顺序添加一个元素的时候非常方便，只是往数组里面添加了一个元素而已；
 * <p>
 * ArrayList的缺点：
 * a、删除元素的时候，涉及到一次元素复制，如果要复制的元素很多，那么就会比较耗费性能
 * b、插入元素的时候，涉及到一次元素复制，如果要复制的元素很多，那么就会比较耗费性能
 * 因此，ArrayList比较适合顺序添加、随机访问的场景。
 * <p>
 * ArrayList线程
 * 线程安全就是多线程访问的时候，采用了加锁机制，当一个线程访问该类的某个数据的时候，进行保护，其他线程不能进行访问直到该线程读取完，其他线程才可以使用，不会出现数据不一致或者数据污染。
 * 线程不安全就是不提供数据访问保护，有可能出现多个线程先后更改数据造成所得到的数据是脏数据。
 * ArrayList在添加一个元素的时候，需要两个步骤，第一步在Items[Size]的位置存放此元素，第二步增大Size的值
 * 所以在多线程情况下，ArrayList不安全。
 * 解决ArrayList中线程不安全的问题：
 * a、继承Arraylist，然后重写或按需求编写自己的方法，这些方法要写成synchronized，在这些synchronized的方法中调用ArrayList的方法。
 * b、List list = Collections.synchronizedList(new ArrayList());
 */
