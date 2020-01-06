package pkq.com;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;
import sun.rmi.runtime.NewThreadAction;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 中间操作
 *      筛选与切片
 *      filter -接受lambda，从流中排除某些元素。
 *      limit -截断流，就是不能超过指定的数量
 *      skip -跳过元素，返回一个扔掉前n个元素流。若流中元素不足n个，返回空流。与limit（n）互补
 *      distinct-筛选，通过流所生成的元素的hashcode()和equals去重复元素  【和集合判断对象是否存在类似】
 */

/**
 * 映射
 * map  接受lambda  将元素转换成其他形式或者提取信息。接受一个函数作为参数，该函数被应用到每个元素上，
 * 并将其映射成一个新的元素。
 *
 * flatMap 接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流。
 *
 * 两者取别在与map 是返回一个流，flatMap是将流合并成一个流
 *  就和add，和addall一样  add添加一个集合是吧整个集合放进去。
 *  addall是讲一个集合的元素添加进去
 */

/**
 * 排序
 *    sorted()  自然排序
 *    sorted()  定制排序  就是根据你想排序的规则去排序  详情test06
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

    @Test
    public void getssoom(){

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
        List<Integer> collect = user.stream()
                .map((z) -> z.getAge()).collect(Collectors.toList());


        Comparator<User> cpr = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getNaem().equals(o2.getNaem())){
                return o1.getAge().compareTo(o2.getAge());
            }else {
                return o1.getNaem().compareTo(o2.getNaem());
            }
        }
        };
        Comparable<User> abc = new Comparable<User>() {
            @Override
            public int compareTo(User o) {
                return o.getNaem().compareTo(new User().getNaem());
            }
        };
        //Collections.sort(user);
    }
    //sotted()排序
    @Test
    private void test06(){
        List<String> str = Arrays.asList("bb", "cc","aa",  "dd");
        //自然排序
        str.stream()
                .sorted()
                .forEach(System.out::println);
        //自定义排序
        user.stream()
                .sorted((x,y) -> {
                    if(x.getNaem().equals(y.getNaem())){
                        return x.getNaem().compareTo(y.getNaem());
                    }else{
                        return x.getAge().compareTo(y.getAge());
                    }
                }).forEach(System.out::println);
    }

    //map flatmap
    @Test
    public void test05(){
        List<String> str = Arrays.asList("aa", "bb", "CC", "DD");
        //.map
        str.stream()
                .map((s) -> s.toUpperCase())
                .forEach(System.out::println);
        System.out.println("----------------------------------------------");
        user.stream()
                .map(User::getGz)
                .forEach(System.out::println);
        System.out.println("----------------------------------------------");
        Stream<Stream<Character>> streamStream = str.stream()
                .map(TestStream01::getStream);
        streamStream.forEach((aa) -> aa.forEach(System.out::println));
        System.out.println("----------------------------------------------");
        Stream<String> stream = str.stream().map(s -> s.toUpperCase());
        stream.forEach(System.out::println);
        System.out.println("----------------------------------------------");
        //.flatmap
        Stream<Character> characterStream = str.stream()
                .flatMap(TestStream01::getStream);
        characterStream.forEach(System.out::println);
    }
    public static Stream<Character> getStream(String ss){
        List<Character> ca = new ArrayList<>();

        for (char c : ss.toCharArray()) {
            ca.add(c);
        }
        return ca.stream();
    }
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

}


