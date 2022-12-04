package test;

import util.BaseUtil;
import util.MysqlUtil;
import util.account;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class loginTest {
    public static void main(String[] args) {
//        BaseUtil bs = new BaseUtil();
//        String username = "20";
//        String password = "1126";
//        String sql = "select * from account where user_id=? and password=?";
//        List<account> list = bs.getList(account.class, sql, username, password);
//        System.out.println(list.size());

        MysqlUtil util = new MysqlUtil();

        List<Object> list = new ArrayList<>();

        list.add(3);
        list.add("你好");
        list.add(3);

        System.out.println(list);
        try {
            util.insertData("UPDATE feedback set state = ? and words = ? where message_id = ?",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
