package com.BudgetPlanner.service;

import com.BudgetPlanner.entity.AppUser;
import com.BudgetPlanner.DTO.CustomUserDetails;
import com.BudgetPlanner.DTO.WebUser;
import com.BudgetPlanner.entity.Client;
import com.BudgetPlanner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClientService clientService;
  public PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ClientService clientService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientService = clientService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public AppUser findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Transactional
    @Override
    public void save(WebUser webUser) {
        Client client = new Client();
        client.setFirstName(webUser.getFirstName());
        client.setLastName(webUser.getLastName());
        client.setEmail(webUser.getEmail());

        AppUser user = new AppUser();
        user.setUserName(webUser.getUsername());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setClient(client);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Since roles are no longer used, pass an empty collection
        return new CustomUserDetails(user.getUserName(), user.getPassword(),
                Collections.emptyList(), user.getClient().getId());
    }
}
