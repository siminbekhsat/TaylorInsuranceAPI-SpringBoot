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
@Entity(name = "autoquote")
public class AutoQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Integer autoQuoteId;

    private  double autoQuotePremium;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate autoQuoteStartDate;

    @JsonFormat(pattern="yyyy-MM-dd")  //yyyy-MM-dd
    private LocalDate autoQuoteEndDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autoId", nullable = false)
    @JsonBackReference
    private Auto auto;

    public Integer getAutoQuoteId() {
        return autoQuoteId;
    }

    public void setAutoQuoteId(Integer autoQuoteId) {
        this.autoQuoteId = autoQuoteId;
    }

    public double getAutoQuotePremium() {
        return autoQuotePremium;
    }

    public void setAutoQuotePremium(double autoQuotePremium) {
        this.autoQuotePremium = autoQuotePremium;
    }

    public LocalDate getAutoQuoteStartDate() {
        return autoQuoteStartDate;
    }

    public void setAutoQuoteStartDate(LocalDate autoQuoteStartDate) {
        this.autoQuoteStartDate = autoQuoteStartDate;
    }

    public LocalDate getAutoQuoteEndDate() {
        return autoQuoteEndDate;
    }

    public void setAutoQuoteEndDate(LocalDate autoQuoteEndDate) {
        this.autoQuoteEndDate = autoQuoteEndDate;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
