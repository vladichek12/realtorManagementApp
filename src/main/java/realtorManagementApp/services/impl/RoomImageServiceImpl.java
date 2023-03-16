package realtorManagementApp.services.impl;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.RoomImageDao;
import realtorManagementApp.dao.impl.RoomDaoImpl;
import realtorManagementApp.dao.impl.RoomImageDaoImpl;
import realtorManagementApp.entities.RoomImage;
import realtorManagementApp.services.RoomImageService;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.StringUtils.trimAllWhitespace;

public class RoomImageServiceImpl implements RoomImageService {
    private static volatile RoomImageServiceImpl instance;

    private RoomDao roomDao;
    private RoomImageDao roomImageDao;

    public static RoomImageService getInstance() {
        RoomImageServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (RoomServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new RoomImageServiceImpl();
            }
        }
        return localInstance;
    }

    private RoomImageServiceImpl() {
        roomDao = RoomDaoImpl.getInstance();
        roomImageDao = RoomImageDaoImpl.getInstance();
    }

    @Override
    public void save(MultipartFile file, Long id){
        RoomImage image = new RoomImage();
        String fileName = StringUtils.cleanPath(trimAllWhitespace
                (Objects.requireNonNull(file.getOriginalFilename())));
        try {
            image.setRoom(roomDao.findById(Math.toIntExact(id)));
            image.setImage(file.getBytes());
            image.setFileName(fileName);
            image.setType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        roomImageDao.save(image);
    }

    @Override
    public RoomImage getById(Long id) {
       return roomImageDao.findById(Math.toIntExact(id));
    }

    @Override
    public void update(MultipartFile file, Long imageId) {
        RoomImage image = new RoomImage();
        String fileName = StringUtils.cleanPath(trimAllWhitespace
                (Objects.requireNonNull(file.getOriginalFilename())));
        try {
            image.setRoom(roomDao.findById(Math.toIntExact(imageId)));
            image.setImage(file.getBytes());
            image.setFileName(fileName);
            image.setType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        roomImageDao.update(image);
    }

    @Override
    public void save(RoomImage roomImage) {
        roomImageDao.save(roomImage);
    }

    @Override
    public void delete(RoomImage roomImage) {
        roomImageDao.delete(roomImage);
    }
}
