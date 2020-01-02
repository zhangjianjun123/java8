package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.Supplier;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.*;
import java.util.function.*;

/**
 * 一.方法引用 若lambda 体中得内容有方法已经实现，我们可以使用 【方法引用】
 * （可以理解为方法引用是lambda 表达式得是另一种表现形式）
 * 主要有三种方式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 二.构造器引用
 *  ClassName::new
 *   注意：需要调用的构造器参数列表要与函数接口中抽象方法的参数列表一致；
 */
@SpringBootTest
class lambdaTest04 {



//    对象::实例方法名

    //这个的意思就是简写  直接就能使用  直接调用输出println
        @Test
    public void test1(){
            Consumer<String> con = (x) -> System.out.println(x);
            con.accept("中华人民共和国");
            Consumer<String> con1 = System.out::println;
            con1.accept("犯我中华者虽远必诛");

        }
//    类::静态方法名
    @Test
    public void  test2(){
        Comparator<Integer> co =(x,y) -> Integer.max(x,y);
        int compare = co.compare(20, 10);
        System.out.println(compare);
        Comparator<Integer> co1 = Integer::compare;
        int compare1 = co1.compare(20, 10);
        System.out.println(compare1);
    }
//    类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> bp =(x,y) -> x.equals(y);
        boolean test = bp.test("张三", "李四");
        System.out.println(test);


        BiPredicate<Integer,Integer> in = Integer::equals;
        boolean test1 = in.test(10, 20);
        System.out.println(test1);
    }

    /**
     * 构造器引用
     *   默认是那个构造器和你得函数接口是有关写  你的函数接口是一个参数救火匹配一个参数
     *   的构造器，如果是两个那就匹配两个参数的构造器。
     */
    @Test
    public void test4(){
            //这是无参构造器
      Supplier<User> us = User::new;
        User user = us.get();
        System.out.println(user);

    }
    @Test
    public void test5(){
            //二个参数得构造器
        BiFunction<String,Integer,User> fu  = User::new;
        User user = fu.apply("中原一点红", 20);
        System.out.println(user);
    }
    //数组引用

    @Test
    public void test6(){
        Function<Integer,String[]> fu =(x) -> new String[x];
        String[] apply = fu.apply(10);
        System.out.println(apply.length);
        Function<Integer,String[]> fu1 =String[] :: new;
        String[] apply1 = fu1.apply(30);
        System.out.println(apply1);
    }
}


