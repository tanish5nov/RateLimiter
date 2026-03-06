package RateLimiter;

import Request.Request;

public interface RateLimiter {
    public void checkRequest(Request request);
}
