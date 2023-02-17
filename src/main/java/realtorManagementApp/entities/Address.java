package realtorManagementApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "house")
    private Integer houseNumber;

    //    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
//    private Order order;
    @OneToOne(mappedBy = "address")
    private Room room;

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Address() {
    }

    public Address(String city, String street, Integer houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
