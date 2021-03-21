package model.builder;

import model.Transaction;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionBuilder {
    private final Transaction transaction;

    public TransactionBuilder(){
        transaction=new Transaction();
    }
    public TransactionBuilder setId(Long id ){
        transaction.setId(id);
        return this;
    }
    public TransactionBuilder setFromId(Long idFrom ){
        transaction.setId_from(idFrom);
        return this;
    }
    public TransactionBuilder setToId(Long idTo ){
        transaction.setId_to(idTo);
        return this;
    }
    public TransactionBuilder setUserId(Long idUser ){
        transaction.setId_user(idUser);
        return this;
    }
    public TransactionBuilder setDate(){
        transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
        return this;
    }
    public TransactionBuilder setValue(Integer value){
        transaction.setValue(value);
        return this;
    }
    public Transaction build(){return transaction;}

}
