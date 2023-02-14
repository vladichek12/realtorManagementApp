package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.entities.Room;
import realtorManagementApp.session.SessionHandler;

import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private Session currentSession;

    public RoomDaoImpl() {
    }

    @Override
    public List<Room> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<Room> rooms = currentSession.createSQLQuery("select * from rooms").addEntity(Room.class).list();
        SessionHandler.closeTransactionSession();
        return rooms;
    }

    @Override
    public void save(Room room) {
        currentSession = SessionHandler.openTransaction();
        currentSession.save(room);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void delete(Room room) {
        currentSession = SessionHandler.openTransaction();
        currentSession.delete(room);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void update(Room room) {
        currentSession = SessionHandler.openTransaction();
        currentSession.update(room);
        SessionHandler.closeTransactionSession();
    }
}
