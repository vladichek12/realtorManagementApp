package realtorManagementApp.dao;

import realtorManagementApp.entities.Room;

import java.util.List;

public interface RoomDao {
    public List<Room> findAll();

    public Room findById(Integer id);

    public void save(Room room);

    public void delete(Room room);

    public void update(Room room);
}
