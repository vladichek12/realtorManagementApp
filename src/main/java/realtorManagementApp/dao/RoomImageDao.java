package realtorManagementApp.dao;

import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.RoomImage;

import java.util.List;

public interface RoomImageDao {
    public List<RoomImage> findAll();

    public RoomImage findById(Integer id);

    public void save(RoomImage roomImage);

    public void delete(RoomImage roomImage);

    public void update(RoomImage roomImage);
}
