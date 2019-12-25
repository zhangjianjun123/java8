package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.zip.ZipOutputStream;

/**
 * java8 内置四大核心函数接口
 * 1.） Consumer<T> :消费型
 *         void accept(T t);
 *         没有返回值 只做处理
 * 2.） Supplier<T> 提供型接口
 *      T get();  就是获取
 * 3.） Function<T,R> : 函数型接口
 *      R apply(T t);传进来一个T  经过处理返回一个R 类型
 * 4.） Predicate<T> :断言行接口
 *       boolean test(T t); 传进来一个T 经过处理返回一个boolean值
 */
@SpringBootTest
class lambdaTest03 {
    /**
     * predicate  返回boolean值
     */
    @Test
    public void test4(){
        List<Integer> ll = Arrays.asList(123,124,125,126,127,128);
        List<Integer> integers = test4cs(ll, (x) -> x > 125);
        integers.stream()
                .forEach(System.out::println);
    }

    public List<Integer> test4cs(List<Integer> l,Predicate<Integer> p){
        List<Integer> ll = new ArrayList<>();

        for (Integer o : l) {
            if(p.test(o)){
                ll.add(o);
            }
        }
        return ll;
    }

    /**
     * function 函数型接口
     */
    @Test
    public void test3(){
        Integer integer = test3cs("12334", (x) -> x.length());
        System.out.println(integer);
        Integer integer1 = test3cs("12334", (x) -> Integer.valueOf(x.substring(1, 3)));
        System.out.println(integer1);
    }

    public Integer test3cs(String s,Function<String,Integer> f){

        return  f.apply(s);
    }
    /**
     * 提供接口没有参数
     */
    @Test
    public void test2(){
        List<Integer> integers = test2cs(10, () -> (int) ((Math.random() * 9 + 1) * 1000));
        System.out.println(integers.toString());
    }
    public List<Integer> test2cs(int i, Supplier<Integer> s){
        List<Integer>  li = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            Integer integer = s.get();
            li.add(integer);
        }
        return li;
    }
    /**
     * consumer<T> 是一个有参处理但是没有返回值得
     */
    @Test
    public void test1(){
        test1cs("asdrfg,", (x) -> System.out.println(x.toUpperCase()));
    }

    public void test1cs(String s,Consumer<String> css){
    css.accept(s);
    }

}


