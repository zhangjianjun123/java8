package pkq.com;

import ch.qos.logback.core.joran.conditional.ElseAction;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.Goddess;
import pkq.bean.Men;
import pkq.bean.User;

import java.util.Optional;

@SpringBootTest
class testOptional {
    /**
     * Optional容器类的常用方法:
     *      Optional.of(T t) :创建一个Optional实例
     * 	 optional.empty() :创建一个空的Optional实例
     * 	 Optional. ofNullable(T t):若t不为null,创建Optional实例,否则创建空实例
     * 	 isPresent() :判断是否包含值
     * 	 orElse(T t)如果调用对象包含值,返回该值,否则返回
     * 	 torElseGet(Supplier js) :如果调用对象包含值,返回该值,否则返回s获取的值
     * 	 map(Function f):如果有值对其处理,并返回处理后的Optional,否则返回Optional. empty()
     * 	 flatMap(Function mapper):与map类似,要求返回值必须是Optional
     */

    public static void main(String[] args) {

        /**
         * 这个在实体类定义或者在这定义都行首先给他转换成Optional对象
         * 在具体操作实现   个人感觉使用一般
         */
        Optional<Goddess> gd =Optional.ofNullable(new Goddess("眼镜破洞裤"));
        Optional<Men> men = Optional.ofNullable(new Men(gd));
        String goddess = testOptional.getGoddess(men);
        System.out.println(goddess);
    }
    public static String getGoddess(Optional<Men> men){
       return men.orElse(new Men())
               .getGddes()
               .orElse(new Goddess("波波老师"))
               .getName();


    }
    public static void main4(String[] args) {
        Optional<User> user = Optional.of(new User("张三", 33, 444.44, User.Status.Red));
        Optional<User.Status> status = user.map((x) -> x.getStatus());
        System.out.println(status.get());
        Optional<Integer> integer = user.flatMap((x) -> Optional.ofNullable(x.getAge()));
        System.out.println(integer.get());
    }
    public static void main3(String[] args) {
        Optional<User> user = Optional.ofNullable(new User());
        System.out.println(user.get());
        //上面正常输出  下面就是报错
        Optional<User> user1 = Optional.ofNullable(null);
        //加上这个
        if (user1.isPresent()) {//这句话的意识是判断为空不进if
            System.out.println(user1.get());
        }
        Optional<User> user2 = Optional.ofNullable(new User());//这句话的意思就是当这里不是null的时候就用这个要不就用下面那个
        User user3 = user2.orElse(new User("张三", 22, 33.3, User.Status.Red));
        System.out.println(user3);
        Optional<User> user4= Optional.ofNullable(null);//这句话的意思就是当这里不是null的时候就用这个要不就用下面那个
        User user5= user4.orElse(new User("张三", 22, 33.3, User.Status.Red));
        System.out.println(user5);
        Optional<User> user6= Optional.ofNullable(null);
        User user7 = user6.orElseGet(() -> new User());
        System.out.println(user7+"-----------------");

    }
    public static void main2(String[] args) {
        Optional<User> empty = Optional.empty();
        //这个是在这里报错
        System.out.println(empty.get());
    }
    public static void main1(String[] args) {
        //Optional.of()  直接会报这里的错误
        Optional<User> user = Optional.of(null);
        System.out.println(user+"-----------------");
    }


}


