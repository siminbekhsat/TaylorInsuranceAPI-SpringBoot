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
@Entity(name = "homequote")
public class HomeQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer homeQuoteId;

    private  double homeQuotePremium;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate homeQuoteStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate homeQuoteEndDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "homeId", nullable = false)
    @JsonBackReference
    private Home home;

    public Integer getHomeQuoteId() {
        return homeQuoteId;
    }

    public void setHomeQuoteId(Integer homeQuoteId) {
        this.homeQuoteId = homeQuoteId;
    }

    public double getHomeQuotePremium() {
        return homeQuotePremium;
    }

    public void setHomeQuotePremium(double homeQuotePremium) {
        this.homeQuotePremium = homeQuotePremium;
    }

    public LocalDate getHomeQuoteStartDate() {
        return homeQuoteStartDate;
    }

    public void setHomeQuoteStartDate(LocalDate homeQuoteStartDate) {
        this.homeQuoteStartDate = homeQuoteStartDate;
    }

    public LocalDate getHomeQuoteEndDate() {
        return homeQuoteEndDate;
    }

    public void setHomeQuoteEndDate(LocalDate homeQuoteEndDate) {
        this.homeQuoteEndDate = homeQuoteEndDate;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
