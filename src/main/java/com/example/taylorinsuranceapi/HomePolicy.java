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

@Entity(name = "homepolicy")
public class HomePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer homePolicyId;

    private  double homePolicyPremium;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate homePolicyStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate homePolicyEndDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "homeId", nullable = false)
    @JsonBackReference
    private Home home;

    public Integer getHomePolicyId() {
        return homePolicyId;
    }

    public void setHomePolicyId(Integer homePolicyId) {
        this.homePolicyId = homePolicyId;
    }

    public double getHomePolicyPremium() {
        return homePolicyPremium;
    }

    public void setHomePolicyPremium(double homePolicyPremium) {
        this.homePolicyPremium = homePolicyPremium;
    }

    public LocalDate getHomePolicyStartDate() {
        return homePolicyStartDate;
    }

    public void setHomePolicyStartDate(LocalDate homePolicyStartDate) { this.homePolicyStartDate = homePolicyStartDate;}

    public LocalDate getHomePolicyEndDate() {
        return homePolicyEndDate;
    }

    public void setHomePolicyEndDate(LocalDate homePolicyEndDate) {
        this.homePolicyEndDate = homePolicyEndDate;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

}
