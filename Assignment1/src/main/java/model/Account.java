package model;

import java.sql.Date;
import java.time.LocalDate;

public class Account {
    private Long id;
    private Integer SOLD;
    private Long id_client;
    private String type;
    private Date creationDate;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSOLD() {
        return SOLD;
    }

    public void setSOLD(Integer SOLD) {
        this.SOLD = SOLD;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public Date getCreationDate() {
        return  Date.valueOf(LocalDate.now());
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = Date.valueOf(LocalDate.now());
    }
}
