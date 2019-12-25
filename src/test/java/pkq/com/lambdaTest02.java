package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.bean.User;
import pkq.interfaceCs.NumEric;
import pkq.interfaceCs.NumEric02;
import pkq.interfaceCs.StrCl;

import java.util.*;
import java.util.function.Consumer;


@SpringBootTest
class lambdaTest02 {

List<User> user = Arrays.asList(
        new User("张三",18,11111.11),
        new User("张二",18,11111.11),
        new User("李四",28,22222.22),
        new User("拔四",28,22222.22),
        new User("王五",38,33333.33),
        new User("李六",48,44444.44),
        new User("卢七",58,55555.55)
);

/**
 *  用这个方法Collections.sort();
 * 先根据年龄判断 年龄相同得根据姓名判断  排序
 */
    @Test
    public void getTest1(){
        Collections.sort(user,(a,n) ->{
            if(a.getAge() == n.getAge()){
                return a.getNaem().compareTo(n.getNaem());
            }else{
                return Integer.compare(a.getAge(),n.getAge());
            }
        });
        user.stream()
                .filter((x) -> x.getAge()>18 && x.getAge()<38)
                .forEach(System.out::println);
    }
    //处理字符串
    @Test
    public void test02(){
        String ghjk = getStrValue("ghjk", (x) -> x.toUpperCase());
        System.out.println(ghjk);
    }

    public String getStrValue(String str, StrCl cs){
        return cs.getStr(str);
    }

    //现在是传入两个参数  进行运算

    @Test
    public  void  gettest4(){
        long op = getOp(100L, 200L, (a, b) -> a * b);
        System.out.println(op);
    }

    public long getOp(Long l1, Long l2, NumEric02<Long,Long> lo){

        return lo.getValue(l1,l2);
    }
}


