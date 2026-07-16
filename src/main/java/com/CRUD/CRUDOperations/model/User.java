package com.CRUD.CRUDOperations.util;

public class DataMaskingUtil {

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "Invalid Email";
        }
        String[] parts = email.split("@");
        String localPart = parts[0];
        String domain = parts[1];
        return localPart.charAt(0) + "*****@" + domain;
    }

    public static String maskName(String name) {
        if (name == null || name.isEmpty()) {
            return "Invalid Name";
        }
        return name.charAt(0) + "*****";
    }
}

package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import com.CRUD.CRUDOperations.util.DataMaskingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Long getUserId(User user) {
        logger.info("Accessing user ID for user: {}", user.getId());
        return user.getId();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getUserName(User user) {
        String maskedName = DataMaskingUtil.maskName(user.getName());
        logger.info("Accessing user name: {}", maskedName);
        return maskedName;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getUserEmail(User user) {
        String maskedEmail = DataMaskingUtil.maskEmail(user.getEmail());
        logger.info("Accessing user email: {}", maskedEmail);
        return maskedEmail;
    }
}