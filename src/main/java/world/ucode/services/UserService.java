package world.ucode.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import world.ucode.dao.UserDao;
import world.ucode.models.Bid;
import world.ucode.models.Lot;
import world.ucode.models.Role;
import world.ucode.models.User;
import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {
    private final UserDao usersDao = new UserDao();

    public User validateToken(String token) {
        User newUser = usersDao.findByToken(token);
            return newUser;
    }

    public User findUserByLogin(String login) {
        return usersDao.findByLogin(login);
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public User findUser(String login) {
        return usersDao.findByLogin(login);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Lot findLotById(int id) {
        return usersDao.findLotById(id);
    }

    public Set<Bid> findBidsByBidder(String login) {
        return usersDao.findBidsByBidder(login);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = usersDao.findByLogin(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);

    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (Role userRole : userRoles)
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
