package util;

import java.io.Serializable;

public class account implements Serializable {

    private static final long serialVersionUID = -7002074869876877857L;
    private int id;
    private int user_id;
    private String password;
    private String sort_id;

    public account(int user_id, String password, String sort_id) {
        this.user_id = user_id;
        this.password = password;
        this.sort_id = sort_id;
    }

    public account(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }
}
