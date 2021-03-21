package model;

import java.sql.Date;
import java.time.LocalDate;

public class UtilityBill {
    private Long id;
    private String Description;
    private Integer value;
    private Long id_account;
    private Long id_user;
    private Date payDate;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Date getPayDate() {
        return  Date.valueOf(LocalDate.now());
    }

    public void setPayDate(Date payDate) {
        this.payDate = Date.valueOf(LocalDate.now());
    }
}
