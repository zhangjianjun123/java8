package pkq.com;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class TestStream03 {

    List<User> user = Arrays.asList(
            new User("张三",18,11111.11, User.Status.Red),
            new User("张三",18,11111.11, User.Status.Orange),
            new User("李四",28,22222.22, User.Status.Orange),
            new User("王五",38,33333.33, User.Status.Yellow),
            new User("李六",48,44444.44, User.Status.Green),
            new User("李六",48,44444.44, User.Status.Yellow)
    );

    /**
     * 收集
     * collect-将流转换为其他形式。接收一个collector接口的实现,用于给Stream中元素做汇总的方法
     *
     */
    public static void main(String[] args) {
        List<User> user = Arrays.asList(
                new User("张三", 18, 11111.11, User.Status.Red),
                new User("张三", 18, 11111.11, User.Status.Orange),
                new User("李四", 28, 22222.22, User.Status.Orange),
                new User("王五", 38, 33333.33, User.Status.Yellow),
                new User("李六", 48, 44444.44, User.Status.Green),
                new User("李六", 49, 44444.44, User.Status.Yellow)
        );
        //分组
        Map<User.Status,List<User>> us = user.stream()
                .collect(Collectors.groupingBy(User::getStatus));
        System.out.println(us);
        //多级分组
        Map<User.Status,Map<String,List<User>>> abc = user.stream()
                .collect(Collectors.groupingBy(User::getStatus,Collectors.groupingBy((x) ->{
                    if(((User)x).getAge()<20){
                        return "少年";
                    }else if(((User)x).getAge()<30){
                        return "青年";
                    }else{
                        return "中年";
                    }
                })));
        System.out.println(abc);

        //分区  分片
        Map<Boolean,List<User>> dd = user.stream()
                .collect(Collectors.partitioningBy((x) -> x.getAge()>20));
        System.out.println(dd);

        //聚合总和
        IntSummaryStatistics co = user.stream()
                .collect(Collectors.summarizingInt(User::getAge));


        System.out.println(co.getMax());
        System.out.println(co.getMin());
        System.out.println(co.getSum());
        System.out.println(co.getAverage());
        System.out.println(co.toString());
    }
    public static void main3(String[] args) {
        List<User> user = Arrays.asList(
                new User("张三",18,11111.11, User.Status.Red),
                new User("张三",18,11111.11, User.Status.Orange),
                new User("李四",28,22222.22, User.Status.Orange),
                new User("王五",38,33333.33, User.Status.Yellow),
                new User("李六",48,44444.44, User.Status.Green),
                new User("李六",49,44444.44, User.Status.Yellow)
        );
        //总数
        Long collect = user.stream()
                .collect(Collectors.counting());
        System.out.println(collect+"-------总数");
        //平均值
        Double collect1 = user.stream()
                .collect(Collectors.averagingLong(User::getAge));
        System.out.println(collect1.intValue()+"-----------平均值");
        System.out.println(collect1+"-----------平均值");
        //和
        Integer aaa = user.stream()
                .collect(Collectors.summingInt((x) -> x.getAge()));
        System.out.println(aaa+"-------和");
        //最大值
        Optional<Integer> aa = user.stream()
                .map(User::getAge)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(aa.get()+"-----------最大值");
        //最小值
        Optional<User> aa1 =  user.stream()
                   .collect(Collectors.minBy((x,y) -> Integer.compare(x.getAge(),y.getAge())));

        System.out.println(aa1.get()+"-----------最小值");
    }
    public static void main2(String[] args) {
        List<User> user = Arrays.asList(
                new User("张三",18,11111.11, User.Status.Red),
                new User("张三",18,11111.11, User.Status.Orange),
                new User("李四",28,22222.22, User.Status.Orange),
                new User("王五",38,33333.33, User.Status.Yellow),
                new User("李六",48,44444.44, User.Status.Green),
                new User("李六",48,44444.44, User.Status.Yellow)
        );
        List<String> collect = user.stream()
                .map(User::getNaem)
                .collect(Collectors.toList());
        System.out.println(collect+"----------转list");

        Set<String> collect1 = user.stream()
                .map(User::getNaem)
                .collect(Collectors.toSet());
        System.out.println(collect1+"----------set");
        LinkedList<String> collect3 = user.stream()
                .map(User::getNaem)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect3+"----------LinkedList");
        HashSet<String> collect2 = user.stream()
                .map(User::getNaem)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect2+"----------HashSet");


    }
    /**
     *     归约
     *     reduce(T identity, BinaryOperator) / reduce(Binaryoperator)-可以将流中元素反复结合起来,得到一个值。
     */
    public static void main1(String[] args) {
        List<User> user = Arrays.asList(
                new User("张三",18,11111.11, User.Status.Red),
                new User("张三",18,11111.11, User.Status.Orange),
                new User("李四",28,22222.22, User.Status.Orange),
                new User("王五",38,33333.33, User.Status.Yellow),
                new User("李六",48,44444.44, User.Status.Green),
                new User("李六",48,44444.44, User.Status.Yellow)
        );
        List<Integer> li =  Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer reduce = li.stream()
                .reduce(0, (s, x) -> s + x);
        System.out.println(reduce);
        Optional<Integer> reduce1 = user.stream()
                .map(User::getAge)
                .reduce(Integer::max);
        Integer integer = reduce1.get();
        System.out.println(integer);
    }

}


