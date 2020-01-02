package pkq.com;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.Supplier;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Stream 三步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作【终端操作】
 */
@SpringBootTest
class TestStream {

    @Test
    public void test01(){
        //1.可以通过collection系列集合提供的stream()parallelStream()
        List<Integer> li = new ArrayList<>();
        Stream<Integer> stream = li.stream();
        //2.通过Arrays中的静态方法stream()获取数组流
        User[] us = new User[10];
        Stream<User> stream1 = Arrays.stream(us);
        //3.通过Stream 类中的静态方法of()
        Stream<String> of1 = Stream.of("抽烟", "喝酒", "烫头");

        //4.创建无限流 迭代
        Stream<Integer> iteratea = Stream.iterate(0, (s) -> s + 4);

        // 这个是终止
        iteratea
                .limit(20)//这是中间操作
                .forEach(System.out::println);

    }

}


