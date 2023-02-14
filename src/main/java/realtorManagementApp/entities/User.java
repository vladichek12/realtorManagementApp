package realtorManagementApp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;
    @Column
    private String name;

    @Column(name = "user_role")
    private Roles userRole;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

    @OneToMany(mappedBy="realtor")
    private Set<Order> realtorOrders;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public User(String email, String password, Roles userRole, String name) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
