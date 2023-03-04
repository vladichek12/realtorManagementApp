package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.entities.Room;
import realtorManagementApp.exceptions.EntityNotFoundException;
import realtorManagementApp.session.SessionHandler;

import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {
    private static volatile RoomDao instance;

    public static RoomDao getInstance() {
        RoomDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new RoomDaoImpl();
            }
        }
        return localInstance;
    }

    private RoomDaoImpl() {
    }

    private Session currentSession;

    @Override
    public List<Room> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<Room> rooms = currentSession.createSQLQuery("select * from rooms").addEntity(Room.class).list();
        SessionHandler.closeTransactionSession();
        return rooms;
    }

    @Override
    public Room findById(Integer id) {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM rooms WHERE id=:id";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(Room.class);
        query.setParameter("id", id);

        Optional<Room> room = query.stream().findFirst();
        if (room.isEmpty()) {
            throw new EntityNotFoundException(String.format("No room with such id found:%s", id));
        }
        return room.get();
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
