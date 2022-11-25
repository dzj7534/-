package test;

import util.BaseUtil;
import util.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class loginTest {
    public static void main(String[] args) {
        BaseUtil bs = new BaseUtil();
        String username = "wangwu";
        String password = "123";
        String sql = "select * from cd_user where username=? and password=?";
        System.out.println(User.class);
        List<User> list = bs.getList(User.class, sql, username, password);
        System.out.println(list.size());
    }

}
