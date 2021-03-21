package model.builder;

import model.UtilityBill;

import java.sql.Date;
import java.time.LocalDate;

public class UtilityBillBuilder {
    private UtilityBill utilityBill;

    public UtilityBillBuilder(){utilityBill=new UtilityBill();}

    public UtilityBillBuilder setId(Long id){
        utilityBill.setId(id);
        return this;
    }

    public UtilityBillBuilder setDescription(String description){
        utilityBill.setDescription(description);
        return this;
    }
    public UtilityBillBuilder setValue(Integer value){
        utilityBill.setValue(value);
        return this;
    }
    public UtilityBillBuilder setAccountId(Long id){
        utilityBill.setId_account(id);
        return this;
    }
    public UtilityBillBuilder setUserId(Long id){
        utilityBill.setId_user(id);
        return this;
    }
    public UtilityBillBuilder setDate(){
        utilityBill.setPayDate(Date.valueOf(LocalDate.now()));
        return this;
    }

    public UtilityBill build(){return utilityBill;}
}
