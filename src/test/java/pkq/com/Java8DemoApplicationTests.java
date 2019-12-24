package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import pkq.bean.User;
import pkq.conf.UserTest;
import pkq.conf.UserTestIMP;

import javax.swing.text.StyledEditorKit;
import java.net.UnknownServiceException;
import java.util.*;

@SpringBootTest
class Java8DemoApplicationTests {
//  匿名内部类
        List<User> user = Arrays.asList(
                new User("张三",18,11111.11),
                new User("李四",28,22222.22),
                new User("王五",38,33333.33),
                new User("李六",48,44444.44),
                new User("卢七",58,55555.55)
        );
   //策略设计模式
    @Test
    public void getzjj(){

       List<User> users = getUsers(user, new UserTestIMP());
        System.out.println(users);
    }
        public List<User> getCss(){
            List<User>  us = new ArrayList<>();
            for (User u : user) {
                if(u.getAge()>=38){
                    us.add(u);
                }
                        }
            return us;
    }
//
    public List<User> getUsers(List<User> usa, UserTest<User> ma){
        List<User> us = new ArrayList<>();
        for (User u : usa) {
            if(ma.test(u)){
                us.add(u);
            }
        }
        return us;
    }
    //匿名内部类
    @Test
    public void test1(){
        List<User> users = getUsers(user, new UserTest<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge()>48;
            }
        });
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
//lambda表达式
    @Test
    public void test2(){
        List<User> users = getUsers(user, (m) -> m.getAge() > 48);
        users.forEach(System.out::println);
    }
//lambda表达式二

    @Test
    public void test3(){
        user.stream()
                .filter((e)-> e.getAge()>18)
                .limit(2)
                .forEach(System.out::println);
        user.stream()
                .map(User::getNaem)
                .forEach(System.out::println);
    }
}
