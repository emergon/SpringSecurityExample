package emergon.service;

import emergon.dao.UserDao;
import emergon.entity.MyUser;
import emergon.entity.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserServiceImpl implements MyUserService{

    @Autowired
    private UserDao udao;
    
    @Override//This method will be used from DaoAuthenticationProvider in MyWebSecurityConfigurer
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myuser = udao.findByUsername(username);
        List<GrantedAuthority> authorities = convertRolesToGrantedAuthorities(myuser.getRoleSet());
        User user = new User(myuser.getUsername(), myuser.getPasswd(), authorities);
        return user;
    }
    
    /**
     * This method will convert Objects of type Role(our entity) to Objects of type GrantedAuthority
     * Why???? Because User object of SpringFramework needs it.
     * What does User object need? A Collection of GrantedAuthority objects
     * @param roloi The roles of MyUser Entity from Database
     * @return 
     */
    private List<GrantedAuthority> convertRolesToGrantedAuthorities(Set<Role> roloi){
        List<GrantedAuthority> authorities = new ArrayList();
        for(Role rolos: roloi){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+rolos.getRname());
            authorities.add(authority);
        }
        return authorities;
    }
    
}
