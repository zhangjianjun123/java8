package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.tomcat.util.http.ResponseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;
import pkq.conf.UserTest;
import pkq.conf.UserTestIMP;
import pkq.interfaceCs.NumEric;

import java.awt.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/***
 * lambda 表达式的基础语法：Java8中引入一个新的操作符 -> 该操作符称为箭头操作符 或者labda操作符
 * 箭头操作吧labda表达式分为两个部分
 * 左侧：左侧是参数列表  【（）->无参，（q）->有参】
 * 右侧：表达式执行的功能  又叫labda体
 *【labda表达式需要接口的支持】
 * 语法格式：
 *     1.)无参数无返回值
 *      ()-> System.out.println("hello labda");
 *     2.)有一个参数无返回值
 *        Consumer<String> com =(x) -> System.out.println(x);
 *         com.accept("我来抓人了");
 *
 *         com =y -> System.out.println(y);
 *         com.accept("我是一个参数不要加括号");
 *     3.）多个参数多个返回值 多个参数返回单个值
 *       Comparator<Integer> com = (x,y) ->{
 *             System.out.println("我是基本函数");
 *             System.out.println(x-y);
 *             return Integer.compare(x,y);
 *         };
 *         com.compare(30,22);
 *         //如果只有一条语句  {} 和return 可以不用写  具体如下
 *
 *         com= (x,y)-> Integer.compare(x,y);
 *         com.compare(99,22);
 *         }
 *       4.) labda表达是数据类型可以不用写，因为jvm编译器可以通过上下文推断数据类型，【类型推断】
 *
 *      5.）左侧或者右侧有一个参数或者一个表达式的时候可以不用写（）或者{}
 *               左侧测得类型也可以省  可以通过类型推断
 *  lambda表达式需要函数接口得支持
 *      函数接口：接口中有一个抽象方法得接口就叫函数接口，也可以通过@FunctionalInterface修饰看
 *       此接口是不是函数接口
 */

@SpringBootTest
class lambdaTest01 {
//    无参数无返回值
    @Test
    public void test1(){
        //

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
       r.run();
        System.out.println("----------上面是正常写法------------下面是labda写法----------");
        Runnable rn = () -> System.out.println("hello labda");
        rn.run();

    }
//    有一个参数无返回值  【一个参数（）可以不用加】
    @Test
    public void test2(){
        Consumer<String> com =(x) -> System.out.println(x);
        com.accept("我来抓人了");

        com =y -> System.out.println(y);
        com.accept("我是一个参数不要加括号");
    }
//    多个参数多个返回值
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) ->{
            System.out.println("我是基本函数");
            System.out.println(x-y);
            return Integer.compare(x,y);
        };
        com.compare(30,22);
        //如果只有一条语句  {} 和return 可以不用写  具体如下

        com= (x,y)-> Integer.compare(x,y);
        com.compare(99,22);
        }

        //测试进行运算
    @Test
    public void  getValue(){

        Integer eric = eric(10, x -> x * x);
        System.out.println(eric);
    }


    public Integer eric(Integer num, NumEric er){

        return er.getValue(num);
    }
}
