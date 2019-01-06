package net.jayde.study.javase.expert.Generic;

import lombok.extern.log4j.Log4j2;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.expert.Generic
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/5/30 下午3:41
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/30 下午3:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

@Log4j2
public class studyGeneric {
    ArrayListNoGeneric arrayListNoGeneric = new ArrayListNoGeneric();
}


class ArrayListNoGeneric {
    //泛型之前的通用程序设计
    private Object[] elements = new Object[0];

    public Object get(int i) {
        return elements[i];
    }

    public void add(Object o) {
        //这里的实现，只是为了演示，不具有任何参考价值
        int length = elements.length;
        Object[] newElments = new Object[length + 1];
        for (int i = 0; i < length; i++) {
            newElments[i] = elements[i];
        }
        newElments[length] = o;
        elements = newElments;
    }

    public static void main(String[] args) {
        ArrayListNoGeneric stringValues = new ArrayListNoGeneric();
        stringValues.add(1);
        //可以向数组中添加任何类型的对象
        //问题1——获取值时必须强制转换
        String str = (String) stringValues.get(0);
        //问题2——上述强制转型编译时不会出错，而运行时报异常java.lang.ClassCastException
    }
}

//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
class GenericClass<T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public GenericClass(T key) {
        //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() {
        //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        GenericClass<Integer> genericInteger = new GenericClass<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        GenericClass<String> genericString = new GenericClass<String>("key_vlaue");
        System.out.println("泛型测试  " + "key is " + genericInteger.getKey());
        System.out.println("泛型测试  " + "key is " + genericString.getKey());

        //如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型(包括简单类型)。
        GenericClass genericClass1 = new GenericClass("abc");
        GenericClass genericClass2 = new GenericClass(false);
        GenericClass genericClass3 = new GenericClass(123);
        System.out.println(genericClass1.getKey());
        System.out.println(genericClass2.getKey());
        System.out.println(genericClass3.getKey());
    }
}

interface GenericInterface<T>{
    public T next();
}
class GenericInterfaceClassWithNoParam<T> implements GenericInterface<T>{

    @Override
    public T next() {
        return null;
    }

    public static void main(String[] args) {

    }
}
//class GenericInterfaceClassWithParam implements GenericInterface<String>{
//
//    @Override
//    public T next() {
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//    }
//}

class GenericMethod{
    /**
     * 泛型方法的基本介绍
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
            IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }

    public static void main(String[] args) {

    }
}