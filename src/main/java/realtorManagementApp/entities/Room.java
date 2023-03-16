package realtorManagementApp.entities;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private long square;


    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "realtor_id", referencedColumnName = "id")
    private User realtor;

    @Column
    private String description;

    @Column
    private long price;

    @Column
    private String status;

    @Column
    private String type;


    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<RoomImage> roomImages;


    public List<RoomImage> getRoomImage() {
        return roomImages;
    }

    public void setRoomImage(List<RoomImage> roomImages) {
        this.roomImages = roomImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSquare() {
        return square;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRealtor() {
        return realtor;
    }

    public void setRealtor(User realtor) {
        this.realtor = realtor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room() {
    }

    public Room(long square, int numberOfRooms, Address address,/* User user, User realtor,*/ String description,
                long price, List<RoomImage> images) {
        this.square = square;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
        this.user = user;
        this.realtor = realtor;
        this.description = description;
        this.price = price;
        this.roomImages = images;
    }

    public Room(long square, int numberOfRooms, Address address,
                String description, long price, String status, String type, List<RoomImage> images) {
        this.square = square;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
        this.description = description;
        this.price = price;
        this.status = status;
        this.type = type;
        this.roomImages = images;
    }

    public Room(Room room) {
        this.square = room.square;
        this.address = room.address;
        this.description = room.description;
        this.numberOfRooms = room.numberOfRooms;
        this.id = room.id;
        this.price = room.price;
        this.realtor = room.realtor;
        this.status = room.status;
        this.user = room.user;
    }
}
