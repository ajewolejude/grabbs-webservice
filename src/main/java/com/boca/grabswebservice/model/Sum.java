package com.boca.grabswebservice.model;


import javax.persistence.Column;
import java.math.BigDecimal;


public class Sum {



    private Long id;

    @Column(name = "sumTotal")
    private BigDecimal sumTotal;

    @Column(name = "sumMonth")
    private BigDecimal sumMonth;

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }

    public BigDecimal getSumMonth() {
        return sumMonth;
    }

    public void setSumMonth(BigDecimal sumMonth) {
        this.sumMonth = sumMonth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
