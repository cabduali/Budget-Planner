package com.BudgetPlanner.service;

import com.BudgetPlanner.entity.AppUser;
import com.BudgetPlanner.DTO.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser findUserByUserName(String username);

    void save(WebUser webUser);
}
