package realtorManagementApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "room_images")
public class RoomImage implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] image;

    private String fileName;

    @Column(name = "file_type")
    private String type;


    @OneToOne(mappedBy = "roomImage")
    private Room room;

    public RoomImage() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomImage roomImage = (RoomImage) o;
        return Objects.equals(id, roomImage.id) && Arrays.equals(image, roomImage.image) && Objects.equals(fileName, roomImage.fileName) && Objects.equals(type, roomImage.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileName, type);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
