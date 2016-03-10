package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by myklory on 2016/3/9.
 * 打印任意对象的方法,属性
 */
public class ClassUtil {
    /**
     * 打印任意对象信息,包括类的方法,属性
     * @param obj  类的实例
     */
    public static void printClass(Object obj){
        //1.获取类的类类型,c是obj对象的类类型
        Class c = obj.getClass();
        //2.获取类的名称
        System.out.println("类名称: " + c.getName());
        //3.不包括包名的类名称
        System.out.println("类短名称: " + c.getSimpleName());
        //4.类的所有方法
        /*
         * Method类,是成方法的对象,类obj每一个方法都是它的一个实例
         * getMethods()方法获取所有public方法,包括继承的方法
         * getDeclaredMethods()方法获取所有自己声明的所有访问权限的方法
         */
        Method methods[] = c.getMethods();
        for(int i = 0; i < methods.length; i++){
            //方法的返回类型的类类型
            System.out.print(methods[i].getReturnType().getName() + " ");
            //得到方法名
            System.out.print(methods[i].getName() + "(");
            //得到方法参数类型
            Class parameterTypes[] = methods[i].getParameterTypes();
            //
            Parameter parameter[] =  methods[i].getParameters();
            for (int j = 0; j < parameterTypes.length; ++j){
                System.out.print(parameterTypes[j].getName() + " " + parameter[j].getName() + ", ");
            }
            System.out.println(");");
        }


        //5. 构造方法的信息
        Constructor constructors[] = c.getConstructors();
        System.out.println("构造方法:");
        for (int i = 0; i < constructors.length; i++){
            //构造方法参数
            Class parameterTypes[] = constructors[i].getParameterTypes();
            for(Class param : parameterTypes){
                System.out.print(param.getName() + ", ");
            }
            System.out.println();
        }

        //6. 成员变量信息
        //Field[] fields = c.getFields();
        System.out.println("变量:");
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getType().getName() + ": " + field.getName());
        }
        System.out.println();

    }
}
