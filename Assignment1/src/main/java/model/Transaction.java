package model;

import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
    private Long id;
    private Integer value;
    private Long id_from;
    private Long id_to;
    private Long id_user;
    private Date transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getId_from() {
        return id_from;
    }

    public void setId_from(Long id_from) {
        this.id_from = id_from;
    }

    public Long getId_to() {
        return id_to;
    }

    public void setId_to(Long id_to) {
        this.id_to = id_to;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Date getTransactionDate() {
        return  Date.valueOf(LocalDate.now());
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate =  Date.valueOf(LocalDate.now());
    }
}
