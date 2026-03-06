package Server;

import Request.Request;

public class Server1 implements Server {
    private static Server serverInstance = null;

    private Server1() {
        addServer();
    }

    public static Server getInstance() {
        if(serverInstance == null) {
            serverInstance = new Server1();
        }
        return serverInstance;
    }

    @Override
    public void executeRequest(Request request) {
        System.out.println("Executing the request: " + request.getRequest() + " , from user: " + request.getUser().getName());
    }

    @Override
    public void addServer() {
        LoadBalancer.addServer(this);
    }
}
