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

@Entity(name = "autopolicy")
public class AutoPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer autoPolicyId;

    private  double autoPolicyPremium;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate autoPolicyStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate autoPolicyEndDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autoId", nullable = false)
    @JsonBackReference
    private Auto auto;

    public Integer getAutoPolicyId() {
        return autoPolicyId;
    }

    public void setAutoPolicyId(Integer autoPolicyId) {
        this.autoPolicyId = autoPolicyId;
    }

    public double getAutoPolicyPremium() {
        return autoPolicyPremium;
    }

    public void setAutoPolicyPremium(double autoPolicyPremium) {
        this.autoPolicyPremium = autoPolicyPremium;
    }

    public LocalDate getAutoPolicyStartDate() {
        return autoPolicyStartDate;
    }

    public void setAutoPolicyStartDate(LocalDate autoPolicyStartDate) { this.autoPolicyStartDate = autoPolicyStartDate;}

    public LocalDate getAutoPolicyEndDate() {
        return autoPolicyEndDate;
    }

    public void setAutoPolicyEndDate(LocalDate autoPolicyEndDate) {
        this.autoPolicyEndDate = autoPolicyEndDate;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
