package repositories.transaction;

import database.JDBConnectionWrapper;
import model.Account;
import model.Transaction;
import model.builder.TransactionBuilder;
import repositories.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryMySQL implements TransactionRepository{
    private JDBConnectionWrapper connectionWrapper;

    public TransactionRepositoryMySQL(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper=connectionWrapper;
    }
    @Override
    public List<Transaction> findAll() {
        Connection connection=connectionWrapper.getConnection();
        List<Transaction> transactions=new ArrayList<>();
        String sql="Select * from transaction";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                transactions.add(getAccountFromResultSet(resultSet));
            }
        }catch (SQLException throwables){throwables.printStackTrace();}

        return transactions;
    }

    @Override
    public Transaction findById(Long id) throws EntityNotFoundException, EntityNotFoundException {
        try {
            Connection connection = connectionWrapper.getConnection();
            Statement statement=connection.createStatement();
            String sql = "Select * from transaction where id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);
            if (resultSet.next()) {
                return getAccountFromResultSet(resultSet);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public List<Transaction> findByUserId(Long id) throws EntityNotFoundException, EntityNotFoundException {
        Connection connection = connectionWrapper.getConnection();
        List<Transaction>transactions=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql = "Select * from transaction where user_id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                transactions.add(getAccountFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return transactions;
    }

    @Override
    public List<Transaction> betweenDates(java.util.Date startDate, java.util.Date endDate,Long id) throws EntityNotFoundException, EntityNotFoundException {
        Connection connection = connectionWrapper.getConnection();

        List<Transaction>transactions=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql = ("Select * from transaction where transaction_date between "+startDate.getTime())+" and "+endDate.getTime()+" and user_id="+id;
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                transactions.add(getAccountFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return transactions;
    }







    @Override
    public boolean save(Transaction transaction) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO transaction values (null, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setInt(1,transaction.getValue());
            insertStatement.setLong(2, transaction.getId_from());
            insertStatement.setLong(3, transaction.getId_to());
            insertStatement.setLong(4, transaction.getId_user());
            insertStatement.setLong(5, transaction.getTransactionDate().getTime());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        Connection connection=connectionWrapper.getConnection();
        String sql="DELETE from transaction where id >= 0";

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException throwables){throwables.printStackTrace();}


    }
    private Transaction getAccountFromResultSet(ResultSet resultSet) throws SQLException{
        return new TransactionBuilder()
                .setId(resultSet.getLong("id"))
                .setValue(resultSet.getInt("value"))
                .setFromId(resultSet.getLong("from_id"))
                .setToId(resultSet.getLong("to_id"))
                .setUserId(resultSet.getLong("user_id"))
                .setDate()
                .build();
    }
}
