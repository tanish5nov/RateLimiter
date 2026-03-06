import RateLimiter.RateLimiter;
import RateLimiter.TokenBucket.TokenBucketRateLimiter;
import Request.Request;
import Server.Server;
import Server.Server1;
import Server.Server2;
import User.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            TokenBucketRateLimiter rateLimiter = TokenBucketRateLimiter.getInstance();
            Scanner scanner = new Scanner(System.in);

            User user1 = new User("Alex", 3);
            User user2 = new User("Bob", 3);
            User user3 = new User("Alice", 3);

            rateLimiter.bucket.put(user1, 3);
            rateLimiter.bucket.put(user2, 3);
            rateLimiter.bucket.put(user3, 3);

            String rq1 = "{'value':1}";
            String rq2 = "{'value':2}";
            String rq3 = "{'value':3}";

//            JsonObject jsonContentRequest1 = Json.createReader(new StringReader(rq1)).readObject();
//            JsonObject jsonContentRequest2 = Json.createReader(new StringReader(rq2)).readObject();
//            JsonObject jsonContentRequest3 = Json.createReader(new StringReader(rq3)).readObject();

            Server server1 = Server1.getInstance();
            Server server2 = Server2.getInstance();
            Request request1 = new Request(user1, rq1);
            Request request2 = new Request(user2, rq2);
            Request request3 = new Request(user3, rq3);

            Thread pusher = new Thread(() -> {
                try {
                    for(int i = 1; i<=1000; i++) {
                        for(int j = 1; j<=4; j++) {
                            rateLimiter.checkRequest(request1);
                            rateLimiter.checkRequest(request2);
                            rateLimiter.checkRequest(request3);
                        }
                        try{
                            Thread.sleep(1000);
                            System.out.println("next");
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });

            pusher.start();



//            while(true) {
//                String input = scanner.nextLine();
//                if(input.equals("close")) {
//                    break;
//                }
//                else {
//                    if(input.equals("1")) {
//                        rateLimiter.checkRequest(request1);
//                    }
//                    else if(input.equals("2")) {
//                        rateLimiter.checkRequest(request2);
//                    }
//                    else {
//                        rateLimiter.checkRequest(request3);
//                    }
//                }
//            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}