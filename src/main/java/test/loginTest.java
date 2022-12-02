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

        int xuehao = 2;
        String wtfl = "df";
        String zywt = "dfdfdf";
        MysqlUtil util = new MysqlUtil();
        List<Object> list = new ArrayList<>();
        list.add(xuehao);
        list.add(new Date());
        list.add(wtfl);
        list.add(zywt);

        try {
            util.insertData("INSERT INTO message(user_id,submission_time,sort_id,content)VALUES(?,?,?,?)",list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
