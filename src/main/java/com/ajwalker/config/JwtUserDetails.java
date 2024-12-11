package com.ajwalker.config;

import com.ajwalker.entity.User;
import com.ajwalker.entity.UserRole;
import com.ajwalker.service.UserRoleService;
import com.ajwalker.service.UserService;
import com.ajwalker.utility.Enum.EAdminRole;
import com.ajwalker.utility.Enum.user.EUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public  UserDetails getUserById(Long userId){
        Optional<User> userOptional = userService.findUserById(userId);
        if(userOptional.isEmpty()){
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleService.findAllByUserId(userId);
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString())));
        authorities.add(new SimpleGrantedAuthority(EAdminRole.ADMIN.toString()));
        authorities.add(new SimpleGrantedAuthority(EUserRole.MANAGER.toString()));
        authorities.add(new SimpleGrantedAuthority(EUserRole.PERSONAL.toString()));

        User user = userOptional.get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();
    }
}
