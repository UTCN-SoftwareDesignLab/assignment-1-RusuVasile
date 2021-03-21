package repositories.bill;

import database.JDBConnectionWrapper;
import model.Account;
import model.UtilityBill;
import model.builder.UtilityBillBuilder;
import repositories.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryMySQL implements BillRepository{

    private JDBConnectionWrapper connectionWrapper;

    public BillRepositoryMySQL(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper=connectionWrapper;
    }

    @Override
    public List<UtilityBill> findAll() {
        Connection connection = connectionWrapper.getConnection();
        List<UtilityBill>utilityBills=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql = "Select * from bill";
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                utilityBills.add(getBillFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return utilityBills;
    }

    @Override
    public UtilityBill findById(Long id) throws EntityNotFoundException, EntityNotFoundException {

        try {
            Connection connection = connectionWrapper.getConnection();
            Statement statement=connection.createStatement();
            String sql = "Select * from bill where id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()) {
                return getBillFromResultSet(resultSet);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public List<UtilityBill> findByUserId(Long id) throws EntityNotFoundException, EntityNotFoundException {
        Connection connection = connectionWrapper.getConnection();
        List<UtilityBill>utilityBills=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql = "Select * from bill where users_id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
                utilityBills.add(getBillFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return utilityBills;
    }

    @Override
    public List<UtilityBill> betweenDates(java.util.Date startDate, java.util.Date endDate,Long id) throws EntityNotFoundException, EntityNotFoundException {
        Connection connection = connectionWrapper.getConnection();
        List<UtilityBill>utilityBills=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();

            String sql = "Select * from bill where pay_date between "+startDate.getTime()+" and "+endDate.getTime()+" and users_id="+id;
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()) {
               utilityBills.add(getBillFromResultSet(resultSet));
            }

        }catch (SQLException throwables){throwables.printStackTrace();}
        return utilityBills;
    }

    @Override
    public boolean save(UtilityBill utilityBill) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO bill values (null, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setInt(1,utilityBill.getValue());
            insertStatement.setLong(2, utilityBill.getId_account());
            insertStatement.setLong(3, utilityBill.getId_user());
            insertStatement.setLong(4, utilityBill.getPayDate().getTime());
            insertStatement.setString(5,utilityBill.getDescription() );
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
        String sql="DELETE from bill where id >= 0";

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException throwables){throwables.printStackTrace();}

    }
    private UtilityBill getBillFromResultSet(ResultSet resultSet) throws SQLException {
        return new UtilityBillBuilder()
                .setId(resultSet.getLong("id"))
                .setValue(resultSet.getInt("value"))
                .setAccountId(resultSet.getLong("account_id"))
                .setUserId(resultSet.getLong("users_id"))
                .setDate()
                .setDescription(resultSet.getString("description"))
                .build();
    }
}
