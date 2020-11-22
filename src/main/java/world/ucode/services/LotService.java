package world.ucode.services;

import world.ucode.dao.LotDao;
import world.ucode.dao.UserDao;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.util.List;

public class LotService {
    private final LotDao lotDao = new LotDao();

    public LotService() {
    }

    public Lot findLot(int id) {
        return lotDao.findById(id);
    }

    public void saveLot(Lot lot) {
        lotDao.save(lot);
    }

    public void deleteLot(Lot lot) {
        lotDao.delete(lot);
    }

    public void updateLot(Lot lot) {
        lotDao.update(lot);
    }

    public List<Lot> findAllLots() {
        return lotDao.findAll();
    }
}
