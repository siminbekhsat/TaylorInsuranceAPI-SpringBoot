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
@Entity(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer autoId;
    private String make;
    private String model;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate dateMade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    @JsonBackReference
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auto")
    private Set<AutoQuote> autoQuotes;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auto")
    private AutoPolicy autoPolicy;

    public Integer getAutoId() { return autoId;}

    public void setAutoId(Integer autoId) { this.autoId = autoId;}

    public String getMake() { return make;}

    public void setMake(String make) { this.make = make;}

    public String getModel() { return model;}

    public void setModel(String model) { this.model = model;}

    public LocalDate getDateMade() { return dateMade;}

    public void setDateMade(LocalDate dateMade) { this.dateMade = dateMade;}

    public Customer getCustomer() { return customer;}

    public void setCustomer(Customer customer) { this.customer = customer;}

    public Set<AutoQuote> getAutoQuotes() { return autoQuotes;}

    public void setAutoQuotes(Set<AutoQuote> autoQuotes) { this.autoQuotes = autoQuotes;}

    public AutoPolicy getAutoPolicy() { return autoPolicy;}

    public void setAutoPolicy(AutoPolicy autoPolicy) { this.autoPolicy = autoPolicy;}
}
