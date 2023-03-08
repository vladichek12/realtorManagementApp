package realtorManagementApp.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.RoomImageDao;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.RoomImage;
import realtorManagementApp.exceptions.EntityNotFoundException;
import realtorManagementApp.session.SessionHandler;

import java.util.List;
import java.util.Optional;

public class RoomImageDaoImpl implements RoomImageDao {
    private static volatile RoomImageDao instance;

    public static RoomImageDao getInstance() {
        RoomImageDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomDao.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new RoomImageDaoImpl();
            }
        }
        return localInstance;
    }

    private RoomImageDaoImpl() {
    }

    private Session currentSession;
    @Override
    public List<RoomImage> findAll() {
        currentSession = SessionHandler.openTransaction();
        List<RoomImage> images = currentSession.createSQLQuery("select * from room_images").addEntity(RoomImage.class).list();
        SessionHandler.closeTransactionSession();
        return images;
    }

    @Override
    public RoomImage findById(Integer id) {
        currentSession = SessionHandler.openTransaction();
        String sqlQuery = "SELECT * FROM room_images WHERE id=:id";

        Query query = currentSession.createSQLQuery(sqlQuery).addEntity(RoomImage.class);
        query.setParameter("id", id);

        Optional<RoomImage> image = query.stream().findFirst();
        if (image.isEmpty()) {
            throw new EntityNotFoundException(String.format("No image with such id found:%s", id));
        }
        SessionHandler.closeTransactionSession();
        return image.get();
    }

    @Override
    public void save(RoomImage roomImage) {
        currentSession = SessionHandler.openTransaction();
        currentSession.save(roomImage);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void delete(RoomImage roomImage) {
        currentSession = SessionHandler.openTransaction();
        currentSession.delete(roomImage);
        SessionHandler.closeTransactionSession();
    }

    @Override
    public void update(RoomImage roomImage) {
        currentSession = SessionHandler.openTransaction();
        currentSession.update(roomImage);
        SessionHandler.closeTransactionSession();
    }
}
