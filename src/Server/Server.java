package Server;

import Request.Request;

public class Server {
    public static void handleRequest(Request request) {

        try {
            String senderName = request.getUser().getName();
            String requestJson = request.getRequest();

            String Log = """
                The sender is:
                """ + senderName + """
               \n The Request is: \n
                """ + requestJson.toString();

            System.out.println(Log);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}
