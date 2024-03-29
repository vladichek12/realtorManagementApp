package realtorManagementApp.services.impl;

import realtorManagementApp._enum.Statuses;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.RoomImageDao;
import realtorManagementApp.dao.impl.RoomDaoImpl;
import realtorManagementApp.dao.impl.RoomImageDaoImpl;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;
import realtorManagementApp.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private static volatile RoomServiceImpl instance;

    private RoomDao roomDao;
    private RoomImageDao roomImageDao;

    public static RoomServiceImpl getInstance() {
        RoomServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new RoomServiceImpl();
            }
        }
        return localInstance;
    }

    private RoomServiceImpl() {
        roomDao = RoomDaoImpl.getInstance();
        roomImageDao = RoomImageDaoImpl.getInstance();
    }

    @Override
    public List<Room> findAll() {
        return roomDao.findAll();
    }

    @Override
    public Room findById(Integer id) {
        return roomDao.findById(id);
    }

    @Override
    public List<Room> findAllUserRooms(User user) {
        return findAll().stream().
                filter(room -> user.getId().equals(room.getUser().getId())).
                collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllPostedRooms() {
        return findAll().stream().
                filter(room -> Statuses.STATUS_POSTED.getTitle().equals(room.getStatus())).
                collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllRealtorRooms(User user) {
        return findAll().stream().
                filter(room -> room.getRealtor() != null).
                filter((room -> user.getId().equals(room.getRealtor().getId()))).
                collect(Collectors.toList());
    }

    @Override
    public List<Room> findAllProcessingRooms() {
        return findAll().stream().
                filter(room -> Statuses.STATUS_PROCESSING.getTitle().equals(room.getStatus())).
                collect(Collectors.toList());
    }

    @Override
    public void save(Room room) {
        roomDao.save(room);
    }

    @Override
    public void delete(Room room) {
        roomImageDao.findByRoomId(room.getId()).
                forEach(roomImage -> roomImageDao.delete(roomImage));
        roomDao.delete(room);
}

    @Override
    public void update(Room room) {
        roomDao.update(room);
    }
}
