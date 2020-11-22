package world.ucode.services;

import world.ucode.dao.LotDao;
import world.ucode.dao.UserDao;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.util.List;

public class LotService {
    private LotDao lotDao = new LotDao();

    public LotService() {
    }

    public Lot findUser(int id) {
        return lotDao.findById(id);
    }

    public void saveUser(Lot lot) {
        lotDao.save(lot);
    }

    public void deleteUser(Lot lot) {
        lotDao.delete(lot);
    }

    public void updateUser(Lot lot) {
        lotDao.update(lot);
    }

    public List<Lot> findAllLots() {
        return lotDao.findAll();
    }
}
