package pkq.conf;

import pkq.bean.User;

public class UserTestIMP implements UserTest<User>{

    public boolean test(User u) {
        return u.getAge()>20;
    }
}
