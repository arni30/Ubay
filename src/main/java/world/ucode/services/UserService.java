package world.ucode.services;

import world.ucode.dao.UserDao;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.util.List;

public class UserService {
    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User validateUser(User user) throws Exception {
        User newUser = usersDao.findByLogin(user.getLogin());
        if (newUser.getPassword().equals(user.getPassword()))
            return newUser;
        else
            throw new Exception("Unknown user");
    }
    public User findUser(int id) {
        return usersDao.findById(id);
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
