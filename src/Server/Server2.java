package Server;

import Request.Request;

public class Server2 implements Server {
    private static Server serverInstance = null;

    private Server2() {
        addServer();
    }

    public static Server getInstance() {
        if(serverInstance == null) {
            serverInstance = new Server2();
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
