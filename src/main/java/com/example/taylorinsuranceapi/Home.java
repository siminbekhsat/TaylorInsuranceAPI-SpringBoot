package com.example.taylorinsuranceapi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Migrate this from a POJO to a DAO
 * @Simin
 */
@Entity(name = "home")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer homeId;

    @Enumerated(EnumType.ORDINAL)
    private HeatingType heatingType;

    @Enumerated(EnumType.ORDINAL)
    private Location location;

    private int value;              //Should use BigDecimal but who cares right now
    private  int streetNumber;
    private String streetName;
    private String city;
    private String postalcode;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate dateBuilt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    @JsonBackReference              //This will stop an infinite recurrsion in api get
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
    private Set<HomeQuote> homeQuotes;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "home")
    private HomePolicy homePolicy;

    //Enum Definitions
    public enum HeatingType { OIL_HEATING, WOOD_HEATING, OTHER_HEATING }
    public enum Location { URBAN, RURAL }

    public Integer getHomeId() { return homeId; }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStreetNumber() { return streetNumber; }

    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }

    public String getStreetName() { return streetName; }

    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getPostalcode() { return postalcode; }

    public void setPostalcode(String postalcode) { this.postalcode = postalcode; }

    public LocalDate getDateBuilt() {
        return dateBuilt;
    }

    public void setDateBuilt(LocalDate dateBuilt) {
        this.dateBuilt = dateBuilt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<HomeQuote> getHomeQuotes() {
        return homeQuotes;
    }

    public void setHomeQuotes(Set<HomeQuote> homeQuotes) {
        this.homeQuotes = homeQuotes;
    }

    public HomePolicy getHomePolicy() {
        return homePolicy;
    }

    public void setHomePolicy(HomePolicy homePolicy) {
        this.homePolicy = homePolicy;
    }
}
