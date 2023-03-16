package realtorManagementApp.services;

import org.springframework.web.multipart.MultipartFile;
import realtorManagementApp.entities.RoomImage;

import java.io.IOException;
import java.util.List;

public interface RoomImageService {

    void save(MultipartFile file, Long id) throws IOException;

    RoomImage getById(Long id);

    void update(MultipartFile file, Long imageId);

    void save(RoomImage roomImage);

    void delete(RoomImage roomImage);
}
