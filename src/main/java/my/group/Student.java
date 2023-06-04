package my.group;

import my.group.validator.CheckIpn;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "Your firstName cannot be empty")
    private String firstName;

    @NotBlank(message = "Your lastName cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "Your email cannot be empty")
    @Email(message = "Your email is not correct")
    private String email;

    @Column(name = "ipn", unique = true)
    @CheckIpn(message = "your number ipn is not correct")
    @NotBlank(message = "Your ipn cannot be empty")
    private String ipn;

    public Student() {

    }

    public Student(String firstName, String lastName, String email, String ipn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ipn = ipn;
    }

    public String getIpn() {
        return ipn;
    }

    public Student setIpn(String ipn) {
        this.ipn = ipn;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ipn='" + ipn + '\'' +
                '}';
    }
}