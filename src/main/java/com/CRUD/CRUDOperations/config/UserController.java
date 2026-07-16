package com.CRUD.CRUDOperations.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }

    private String getRateLimitKey(HttpServletRequest request) {
        String userToken = request.getHeader("Authorization");
        if (userToken != null && !userToken.isEmpty()) {
            return userToken; // Use the user token if available
        }
        String userAgent = request.getHeader("User-Agent");
        String clientIp = request.getRemoteAddr();
        return clientIp + ":" + (userAgent != null ? userAgent : "unknown"); // Fallback to IP + User-Agent
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String rateLimitKey = getRateLimitKey(request);
        Bucket bucket = buckets.computeIfAbsent(rateLimitKey, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS);
            response.getWriter().write("Too many requests - Rate limit exceeded");
        }
    }
}