package Request;

import User.User;

public class Request {
    private User sender;
    private String request;

    public Request(User sender, String request) {
        this.sender = sender;
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public User getUser() {
        return this.sender;
    }
}
