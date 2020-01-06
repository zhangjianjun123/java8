package pkq.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SpringBootTest
class TestStream02 {
    /**
     *    查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配的元素
     * findFirst-返回第一个元素
     * findAny—返回当前流中的任意元素
     * count-返回流中元素的总个数
     * max-返回流中最大值
     * min-返回滚中最小值
     */
    List<User> user = Arrays.asList(
            new User("张三",18,11111.11, User.Status.Red),
            new User("张三",18,11111.11, User.Status.Orange),
            new User("李四",28,22222.22, User.Status.Orange),
            new User("王五",38,33333.33, User.Status.Yellow),
            new User("李六",48,44444.44, User.Status.Green),
            new User("李六",48,44444.44, User.Status.Yellow)
    );

    @Test
    public void getssoom(){
        boolean b = user.stream()
                .allMatch((x) -> x.getStatus().equals(User.Status.Red));
        System.out.println(b);

    }

    public static void main(String[] args) {
        List<User> user = Arrays.asList(
                new User("张三",18,11111.11, User.Status.Red),
                new User("李四",28,22222.22, User.Status.Red),
                new User("王五",38,33333.33, User.Status.Red),
                new User("李六",48,44444.44, User.Status.Red)
        );
        boolean b = user.stream()
                //这个函数的意思就是判断所有数据指定字段是否完全一致，如果完全一直就是true，一条不一样就是false
                .allMatch((x) -> x.getStatus().equals(User.Status.Red));
        System.out.println(b);
        boolean b1 = user.stream()
                //这个函数的意思是只要匹配一个就是true,一个匹配不上就是false
                .anyMatch((x) -> x.getStatus().equals(User.Status.Red));
        System.out.println(b1);
        boolean b2 = user.stream()
                //这个函数的意思就是数据中没有配的元素就是true,有就是false
                .noneMatch((x) -> x.getStatus().equals(User.Status.Red));
        System.out.println(b2);

        Optional<User> first = user.stream()
                .sorted((x, y) -> Integer.compare(x.getAge(), y.getAge()))
                //这个函数的意思是只取第一条数据
                .findFirst();
        System.out.println(first.get()+"返回第一条元素从小");
        Optional<User> first1 = user.stream()
                .sorted((x, y) -> -Integer.compare(x.getAge(), y.getAge()))
                //这个函数的意思是只取第一条数据
                .findFirst();
        System.out.println(first1.get()+"返回第一条元素从大");
        Optional<User> any = user.stream()
                //这个函数的意思是找到匹配的一条数据
                .filter((x) -> x.getStatus().equals(User.Status.Red))
                .findAny();
        System.out.println(any.get());
        //注意看上下两者的区别 上是顺序流也就是串行 从上到下找到一个就可以
        //下面这个就是多线程的 并行流 同时多个线程找到那个是哪个
        Optional<User> any1 = user.parallelStream()
                //这个函数的意思是找到匹配的一条数据
                .filter((x) -> x.getStatus().equals(User.Status.Red))
                .findAny();
        System.out.println(any.get());
        long count = user.stream()
                //这个函数的意思是统计
                .count();
        System.out.println(count);
        Optional<User> max = user.stream()
                .max((s, ss) -> Integer.compare(s.getAge(), ss.getAge()));
        System.out.println(max.get()+"---------------max");
        Optional<Integer> min = user.stream()
                .map(User::getAge)
                .min(Integer::compare);
        System.out.println(min.get()+"---------------min");


    }


}


