package model.builder;

import model.Account;

import java.sql.Date;
import java.time.LocalDate;

public class AccountBuilder {
    private Account account;

    public AccountBuilder(){
        account=new Account();
    }

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setSold(Integer sold){
        account.setSOLD(sold);
        return this;
    }

    public AccountBuilder setClientId(Long id){
        account.setId_client(id);
        return this;
    }
    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }
    public AccountBuilder setDate(){
        account.setCreationDate(Date.valueOf(LocalDate.now()));
        return this;
    }
    public Account build(){return account;}
}
