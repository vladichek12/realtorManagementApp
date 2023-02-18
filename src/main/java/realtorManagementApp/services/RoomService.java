package realtorManagementApp.services;

import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.User;

import java.util.List;

public interface RoomService {
    public List<Room> findAll();

    public Room findById(Integer id);

    public List<Room> findAllUserRooms(User user);

    public void save(Room room);

    public void delete(Room room);

    public void update(Room room);
}
