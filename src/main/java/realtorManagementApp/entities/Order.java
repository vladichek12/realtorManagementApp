package realtorManagementApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "realtor_id")
    private User realtor;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public User getRealtor() {
        return realtor;
    }

    public void setRealtor(User realtor) {
        this.realtor = realtor;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public User getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(User customer) {
//        this.customer = customer;
//    }

    public Order() {
    }

    public Order(User customer, User realtor, Address address) {
        //this.customer = customer;
        // this.realtor = realtor;
        this.room = room;
    }
}
