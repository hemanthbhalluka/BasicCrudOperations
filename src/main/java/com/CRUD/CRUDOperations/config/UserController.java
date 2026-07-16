package com.CRUD.CRUDOperations.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }

    private String getRateLimitKey(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        String sessionId = getSessionIdFromCookie(request);

        if (sessionId == null) {
            sessionId = generateNewSessionId();
            addSessionIdToResponse(request, sessionId);
        }

        return hashKey(clientIp + ":" + sessionId);
    }

    private String getSessionIdFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("SESSION_ID".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void addSessionIdToResponse(HttpServletRequest request, String sessionId) {
        Cookie sessionCookie = new Cookie("SESSION_ID", sessionId);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(60 * 60); // 1 hour
        request.setAttribute("SESSION_COOKIE", sessionCookie);
    }

    private String generateNewSessionId() {
        return java.util.UUID.randomUUID().toString();
    }

    private String hashKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(key.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash for rate limit key", e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String rateLimitKey = getRateLimitKey(request);
        Bucket bucket = buckets.computeIfAbsent(rateLimitKey, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            Cookie sessionCookie = (Cookie) request.getAttribute("SESSION_COOKIE");
            if (sessionCookie != null) {
                response.addCookie(sessionCookie);
            }
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS);
            response.getWriter().write("Too many requests - Rate limit exceeded");
        }
    }
}