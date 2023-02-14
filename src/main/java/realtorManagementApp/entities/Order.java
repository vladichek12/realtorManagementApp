package realtorManagementApp.entities;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "realtor_id")
    private User realtor;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public User getRealtor() {
        return realtor;
    }

    public void setRealtor(User realtor) {
        this.realtor = realtor;
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

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Order() {
    }

    public Order(User customer, User realtor, Address address) {
        this.customer = customer;
        this.realtor = realtor;
        this.address = address;
    }
}
