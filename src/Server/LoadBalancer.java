package Server;

import Request.Request;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private static int serverToHandleThisRequest = 0;

    private static List<Server> servers = new ArrayList<>();

    public static synchronized void handleRequest(Request request) {
        servers.get(serverToHandleThisRequest).executeRequest(request);
        serverToHandleThisRequest = serverToHandleThisRequest + 1;
        if(serverToHandleThisRequest == servers.size()) serverToHandleThisRequest = 0;
    }

    public static void addServer(Server server) {
        servers.add(server);
    }
}
