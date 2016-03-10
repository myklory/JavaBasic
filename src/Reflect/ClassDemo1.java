package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by myklory on 2016/3/9.
 */
public class ClassDemo1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //一般的Java类使用new 关键字就新建了一个类的实例对象
        Foo foo1 = new Foo();
        //在Java中,类本身也是一个实例对象,Foo就是一个实例对象,它是Class的实例对象,这个实例对象我们称为该类的类类型
        //要表示一个Class的实例对象有三种方式
        //1.
        Class c1 = Foo.class;
        //2.
        Class c2 = foo1.getClass();

        //不管以哪种方式取得,一个类只可能是Class类的一个实例对象,它是具有唯一的实例
        //c1,c2,c3都指向同一个类类型,即Foo只有一个类类型.可以把Foo的定义Class Foo(){}解读成为Class类定义一个名字叫Foo的实例,而c1,c2,c3都指向这个实例
        System.out.println(c1 == c2); //true;

        //3.
        Class c3 = Class.forName("Reflect.Foo");

        //可以通过一个类的类类型创建该类的对象,即可通过c1,c2,c3创建Foo的实例
        Foo foo2 = (Foo) c2.newInstance(); //需要有无参数的构造方法

        //调用Foo方法
        //获取方法1
        //Method mth1 = c1.getMethod("print", int.class, int.class);
        //获取方法2
        Method mth1 = c1.getMethod("print", new Class[]{int.class, int.class});
        Object foo3 = c2.newInstance();
        mth1.invoke(foo3, 10,12);

        Method mth2 = c1.getMethod("print", String.class, String.class);
        mth2.invoke(foo1, "Hello", "World");

        //Method mth3 = c1.getMethod("print", new Class[]{});
        Method mth3 = c1.getMethod("print");

        //获取返回值
        String rt = (String) mth3.invoke(foo3);
        System.out.println(rt);
    }
}

class Foo{
    public void print(int a, int b){System.out.println(a + b);}
    public void print(String a, String b){System.out.println(a + ", " + b);}
    public String print(){System.out.println("Hello World!"); return "return Hello World";}
}
