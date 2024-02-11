package com.example.networkautomation.security;

import com.example.networkautomation.entity.RegularUserEntity;
import com.example.networkautomation.entity.SuperUserEntity;
import com.example.networkautomation.repository.RegularUserRepository;
import com.example.networkautomation.repository.SuperUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** User details service class */
@Service
public class AutomationUserDetails implements UserDetailsService {

    private SuperUserRepository superUserRepository;

    private RegularUserRepository regularUserRepository;

    /** Injecting dependency */
    public AutomationUserDetails(
            SuperUserRepository superUserRepository, RegularUserRepository regularUserRepository) {
        this.superUserRepository = superUserRepository;
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName = null;
        String password = null;
        List<GrantedAuthority> authorities = null;

        SuperUserEntity superUserEntity = superUserRepository.findByUserName(username);

        if (superUserEntity == null) {
            RegularUserEntity regularUserEntity =
                    regularUserRepository.findByUserName(username).get();

            if (regularUserEntity == null) {
                throw new UsernameNotFoundException("User name not found: " + username);
            } else {
                userName = regularUserEntity.getUserName();
                password = regularUserEntity.getPassword();
                authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(regularUserEntity.getRole()));
            }
        } else {
            userName = superUserEntity.getUserName();
            password = superUserEntity.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(superUserEntity.getIsSuperUser()));
        }
        return new User(userName, password, authorities);
    }
}
