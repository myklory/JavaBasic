# 反射

标签（空格分隔）： Java

---

##**1. 类类型**
一个类可以有一个实例,如:
```java
Class Reflect.Foo(){} 

Foo foo1 = new Foo();
```
一个类本身也是一个实例,它是Class类的实例.这个实例就是该类的类类型,取得一个类的类类型有三种方法:
```java
//1.
Class c1 = Foo.class;
//2.
Class c2 = foo1.getClass();
//3.
Class c3 = Class.forName("Reflect.Foo");//要包括包名
//c1,c2,c3都指向同一个类类型,即Foo只有一个类类型.可以把Foo的定义class Foo(){}解读成为Class类定义一个名字叫Foo的实例,而c1,c2,c3都指向这个实例
```
可以通过一个类的类类型创建一个类的实例:
```java
Foo foo2 = c2.newInstance();//需要Foo有无参的构造方法
```

##**2. 类的静态加载和动态加载**
在编译时加载是静态加载,在运行时加载是动态加载.

new对象是静态加载类,在编译时刻就需要加载所有的可能使用到的类.

使用Class类类型通过类名字创建类的对象就是动态加载类.
比如有类Office
```java
class Office{
    public static void main(String[] args]){
        Class c = Class.forName(args[1]);
        OfficeAble oa = (OfficeAble)c.newInstance();
        oa.start();
    }
}
```
其中oa就是动态创建的类对象, 只需要参数传入需要创建对象的类的名字,并且是OfficeAble的派生类,就可以调用start方法.

##**3. 获取方法属性的信息**
详见代码 :
```java
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
        }

        //6. 成员变量信息
        //Field[] fields = c.getFields();
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getType().getName() + ": " + field.getName());
        }
```
##**4. 通过反射调用方法**
```java
class Foo{
    public void print(int a, int b){System.out.println(a + b);}
    public void print(String a, String b){System.out.println(a + ", " + b);}
    public String print(){System.out.println("Hello World!"); return "return Hello World";}
}

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
```





