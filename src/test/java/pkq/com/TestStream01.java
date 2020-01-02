package pkq.com;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 中间操作
 *      筛选与切片
 *      filter -接受lambda，从流中排除某些元素。
 *      limit -截断流，就是不能超过指定的数量
 *      skip -跳过元素，返回一个扔掉前n个元素流。若流中元素不足n个，返回空流。与limit（n）互补
 *      distinct-筛选，通过流所生成的元素的hashcode()和equals去重复元素  【和集合判断对象是否存在类似】
 */

@SpringBootTest
class TestStream01 {

    List<User> user = Arrays.asList(
            new User("张三",18,11111.11),
            new User("张二",18,11111.11),
            new User("张二",18,11111.11),
            new User("李四",28,22222.22),
            new User("拔四",28,22222.22),
            new User("拔四",28,22222.22),
            new User("王五",38,33333.33),
            new User("李六",48,44444.44),
            new User("卢七",58,55555.55)
    );
    //filter  排除某些元素
    @Test
    public void test01(){

        Stream<User> userStream = user.stream()
                .filter((x) -> {
                    System.out.println("过滤");
                    return x.getGz() < 30000;
                });
        userStream.forEach(System.out::println);

    }
    //limit
    @Test
    public void test02(){

        Stream<User> userStream = user.stream()
                .filter((x) -> {
                    System.out.println("过滤");
                    return x.getGz() < 30000;
                });
        userStream.limit(2).forEach(System.out::println);

    }
    //skip
    @Test
    public void test03(){

        Stream<User> userStream = user.stream()
                .filter((x) -> {
                    System.out.println("过滤");
                    return x.getGz() < 30000;
                });
        userStream.skip(2).forEach(System.out::println);

    }
        //distinct

    @Test
    public void test04(){
        //distinct 去重就行list判断对象一样必须在实体类重写equals  和 hashcode方法
        //这个和java list
        Stream<User> userStream = user.stream();
        userStream.distinct().forEach(System.out::println);
    }

    public static void main1(String[] args) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("username", "qq");
        map.put("passWord", "123");
        map.put("userID", "1");
        map.put("email", "qq@qq.com");

        for (String s : map.values()) {
            System.out.println(s+"------------map.values()");
        }
        for (String s : map.keySet()) {
            System.out.println(s+"------------map.keySet()");
        }
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s+"------------map.entrySet()");
        }

        Set set = map.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()){
            Map.Entry<String, String> entry1=(Map.Entry<String, String>)i.next();
            System.out.println(entry1.getKey()+"=="+entry1.getValue());
        }
    }
    @Test
    public void test05(){

    }

    public static void main(String[] args) {

        List<User> user = Arrays.asList(
                new User("张三", 18, 11111.11),
                new User("张二", 18, 11111.11),
                new User("张二", 18, 11111.11),
                new User("李四", 28, 22222.22),
                new User("拔四", 28, 22222.22),
                new User("拔四", 28, 22222.22),
                new User("王五", 38, 33333.33),
                new User("李六", 48, 44444.44),
                new User("卢七", 58, 55555.55)
        );


        List<String> str = Arrays.asList("aa", "bb", "CC", "DD");

        str.stream()
                .map((s) -> s.toUpperCase())
                .forEach(System.out::println);

        user.stream()
                .map(User::getAge)
                .forEach(System.out::println);

    }
}


