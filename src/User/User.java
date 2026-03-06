package User;

public class User {
    private String name;
    private Integer limit;

    public User(String name, Integer limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLimit() {
        return this.limit;
    }
}
