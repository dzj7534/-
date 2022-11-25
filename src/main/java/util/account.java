package util;

public class account {

    private Integer id;
    private Integer user_id;
    private String password;
    private String sort_id;

    public account(Integer id, Integer user_id, String password, String sort_id) {
        this.id = id;
        this.user_id = user_id;
        this.password = password;
        this.sort_id = sort_id;
    }

    public account() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }

    @Override
    public String toString() {
        return "account{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", password='" + password + '\'' +
                ", sort_id='" + sort_id + '\'' +
                '}';
    }



}
