package habit.security;

import habit.models.User;
import habit.models.UserDao;
import habit.models.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao, UserRoleDao userRoleDao){
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if(null == user){
            throw new UsernameNotFoundException("No user present with that email");
        } else {
            List<String> userRoles = userRoleDao.findRoleByUsersEmail(email);
            return new CustomUserDetails(user, userRoles);
        }
    }

}
