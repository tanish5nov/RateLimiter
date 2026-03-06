package RateLimiter.TokenBucket;

import RateLimiter.RateLimiter;
import Request.Request;
import Server.Server;
import User.User;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter implements RateLimiter {

    private static TokenBucketRateLimiter tokenBucketRateLimiter = null;

    public static Map<User, Integer> bucket = new HashMap<>();
    private Thread refillerThread;

    private TokenBucketRateLimiter() {
        try {
            refillerThread = new Thread(()->{
                try {
                    while(true) {
                        for(User user: bucket.keySet()) {
                            bucket.put(user, 3);
                        }
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            });
            refillerThread.start();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static synchronized TokenBucketRateLimiter getInstance() {
        if(tokenBucketRateLimiter == null) {
            TokenBucketRateLimiter instance = new TokenBucketRateLimiter();
            tokenBucketRateLimiter = instance;
        }
        return tokenBucketRateLimiter;
    }

    @Override
    public void checkRequest(Request request) {
        try {
            User user = request.getUser();
            if(bucket.containsKey(user) && bucket.get(user) > 0) {
                bucket.put(user, bucket.get(user) - 1);
                Server.handleRequest(request);
            } else {
                System.out.println("Dropped Request : " + request.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}