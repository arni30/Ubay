package world.ucode.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import world.ucode.dao.UserDao;
import world.ucode.models.Lot;
import world.ucode.models.Role;
import world.ucode.models.User;

import javax.transaction.Transactional;
import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {
    private final UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User validateUser(User user) throws Exception {
        User newUser = usersDao.findByLogin(user.getLogin());
        System.out.println(newUser.getVerification());
        System.out.println(newUser.getPassword());
        System.out.println(user.getPassword());
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        if (BCrypt.checkpw(user.getPassword(), newUser.getPassword())
        if (user.getPassword().equals(newUser.getPassword())
                && newUser.getVerification().equals("verificated"))
            return newUser;
        else {
            System.out.println("ENTERED EXCEPTION");
            throw new Exception("Unknown user");
        }
    }
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

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        //        if(user == null)
////            user = new User();
//        UserDetails loadedUser;
//        User user = usersDao.findByLogin(s);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////        System.out.println(user.getAuthorities(user.getRoles()));
////        System.out.println(user.getRoles());
////        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRoles().toString()));
//        System.out.println(user.getRoles().toString());
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(), user.getPassword(), authorities);
//    }
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = usersDao.findByLogin(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

//    public HashMap<Lot, Bid> getListOfBiddersLastBids() {
//        HashMap<Lot, Bid> lotBidsList = new HashMap<>();
//
//        for (Bid b : bids) {
//            lotBidsList.put(b.getLot(), b);
//        }
//
////        ListIterator bidsIterator = bids.listIterator(bids.size());
////        while (bidsIterator.hasPrevious()) {
////            Bid b = (Bid) bidsIterator.previous();
////            lotBidsList.put(b.getLot(), b);
////        }
//        return lotBidsList;
//    }
}
