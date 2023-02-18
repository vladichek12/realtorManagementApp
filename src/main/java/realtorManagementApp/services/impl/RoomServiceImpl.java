package realtorManagementApp.services.impl;

import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.impl.RoomDaoImpl;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;
import realtorManagementApp.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private static volatile RoomServiceImpl instance;

    private RoomDao roomDao;

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
    public void save(Room room) {
        roomDao.save(room);
    }

    @Override
    public void delete(Room room) {
        roomDao.delete(room);
    }

    @Override
    public void update(Room room) {
        roomDao.update(room);
    }
}
