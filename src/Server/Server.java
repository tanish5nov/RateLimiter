package Server;

import Request.Request;

public interface Server {
    public void executeRequest(Request request);
    public void addServer();
}
