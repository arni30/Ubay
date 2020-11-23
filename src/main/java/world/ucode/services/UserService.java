package world.ucode.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import world.ucode.dao.UserDao;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.util.List;
@Service
public class UserService {
    private final UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User validateUser(User user) throws Exception {
        User newUser = usersDao.findByLogin(user.getLogin());
        System.out.println(newUser.getVerification());
        System.out.println(newUser.getPassword());
        System.out.println(user.getPassword());
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (BCrypt.checkpw(user.getPassword(), newUser.getPassword())
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
}
