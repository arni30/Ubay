package world.ucode.services;

import world.ucode.dao.LotDao;
import world.ucode.models.Lot;

import java.util.List;

public class LotService {
    private final LotDao lotDao = new LotDao();

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

    public List<Lot> findAllLotsByUser(String login) {
        return lotDao.findAllByUser(login);
    }
}
