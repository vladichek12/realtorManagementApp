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

/*    @Column
    private String phoneNumber;*/

    @Column
    private String password;
    @Column
    private String name;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "realtor", fetch = FetchType.EAGER)
    private Set<Room> realtorRooms;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public User(String email, String password, String userRole, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.userRole = user.getUserRole();
        this.password = user.getPassword();
        this.name = user.getName();
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
