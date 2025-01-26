package com.example.ride.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExtraFunctions {

    
    public static String getUserName()
    {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = authentication.getName();
            return userId;
    }
}

