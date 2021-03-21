package repositories.account;

import database.JDBConnectionWrapper;
import model.Account;
import model.builder.AccountBuilder;
import repositories.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository{
    private JDBConnectionWrapper connectionWrapper;

    public AccountRepositoryMySQL(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper=connectionWrapper;
    }
    @Override
    public List<Account> findAll() {
        Connection connection=connectionWrapper.getConnection();
        List<Account> accounts=new ArrayList<>();
        String sql="Select * from accounts";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                accounts.add(getAccountFromResultSet(resultSet));
            }
        }catch (SQLException throwables){throwables.printStackTrace();}

        return accounts;
    }


    @Override
    public Account findById(Long id) throws EntityNotFoundException, EntityNotFoundException {
        try {
            Connection connection = connectionWrapper.getConnection();
            Statement statement=connection.createStatement();
            String sql = "Select * from accounts where id=" + id;
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
    public List<Account> findByClientId(Long id) {

            Connection connection = connectionWrapper.getConnection();
            List<Account>accounts=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql = "Select * from accounts where client_id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                accounts.add(getAccountFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return accounts;
    }

    @Override
    public boolean save(Account account) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO accounts values (null, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, account.getType());
            insertStatement.setInt(2,account.getSOLD());
            insertStatement.setLong(3, account.getId_client());
            insertStatement.setLong(4,account.getCreationDate().getTime());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void addToSoldById(Long id,Integer toAdd){
        try {
            Connection connection = connectionWrapper.getConnection();
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE accounts SET sold = ? WHERE id= "+id);
            insertStatement.setInt(1,getSoldById(id)+ toAdd);

            insertStatement.executeUpdate();

        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void subFromSoldById(Long id,Integer toAdd){
        try {
            Connection connection = connectionWrapper.getConnection();
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE accounts SET sold = ? WHERE id= "+id);
            insertStatement.setInt(1,getSoldById(id)- toAdd);

            insertStatement.executeUpdate();

        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();

        }

    }
    @Override
    public Integer getSoldById(Long id) throws SQLException, EntityNotFoundException {
        try {
            Connection connection = connectionWrapper.getConnection();
            Statement statement=connection.createStatement();
            String sql = "Select * from accounts where id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);
            if (resultSet.next()) {
                return getAccountFromResultSet(resultSet).getSOLD();
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }

        }catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }
    @Override
    public void removeAll() {
        Connection connection=connectionWrapper.getConnection();
        String sql="DELETE from accounts where id >= 0";

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException throwables){throwables.printStackTrace();}


    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException{
        return new AccountBuilder()
                .setId(resultSet.getLong("id"))
                .setSold(resultSet.getInt("sold"))
                .setClientId(resultSet.getLong("client_id"))
                .setType(resultSet.getString("kind"))
                .setDate()
                .build();
    }
}
