package pkq.com;

import pkq.service.MyUser;
import pkq.service.MyUser2;

public class UserContro /*extends UserContro2*/ implements MyUser, MyUser2 {


    @Override
    public String getNames() {
        return MyUser2.super.getNames();
    }
}
