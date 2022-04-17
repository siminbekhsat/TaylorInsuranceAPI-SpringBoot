package com.example.taylorinsuranceapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * The core class for the Customer. All insured homes, autos, quotes and policies owned by a customer.
 * @author Simin
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer customerId;

    private String firstName;

    private String lastName;

    @Column(unique=true)
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate dateOfBirth;

    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<Home> homes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference               //Manages the reference to prevent infinite api reference
    private Set<Auto> autos;


    public Integer getId() {
        return customerId;
    }

    public void setId(Integer id) {
        this.customerId = id;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Set<Auto> getAutos() {return autos;}

    public void setAutos(Set<Auto> autos) {this.autos = autos;}

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}
}
