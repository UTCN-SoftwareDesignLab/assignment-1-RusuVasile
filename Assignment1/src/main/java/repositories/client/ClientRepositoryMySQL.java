package repositories.client;

import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;
import repositories.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository{
    private final JDBConnectionWrapper connectionWrapper;

    public ClientRepositoryMySQL(JDBConnectionWrapper connectionWrapper){
        this.connectionWrapper=connectionWrapper;
    }
    @Override
    public List<Client> findAll() {
        Connection connection=connectionWrapper.getConnection();
        List<Client> clients=new ArrayList<>();
        String sql="Select * from client";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                clients.add(getClientFromResultSet(resultSet));
            }
        }catch (SQLException throwables){throwables.printStackTrace();}

        return clients;
    }


    @Override
    public Client findById(Long id) throws EntityNotFoundException {

        try {
            Connection connection = connectionWrapper.getConnection();
            Statement statement=connection.createStatement();
            String sql = "Select * from client where id=" + id;
            ResultSet resultSet=statement.executeQuery(sql);
            if (resultSet.next()) {
                return getClientFromResultSet(resultSet);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }

    }

    @Override
    public void removeById(Long id) {
        Connection connection=connectionWrapper.getConnection();
        String sql="DELETE from client where CNP = "+id;

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException throwables){throwables.printStackTrace();}
    }

    @Override
    public void updateAddress(String newAddress,Long id) {
        try {
            Connection connection = connectionWrapper.getConnection();
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client SET address = ? WHERE id= "+id);
            insertStatement.setString(1,newAddress);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean save(Client client) {
        Connection connection = connectionWrapper.getConnection();
        String sql = "INSERT INTO client values (null, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement(sql);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setLong(3, client.getCnp());
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
        String sql="DELETE from client where id >= 0";

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException throwables){throwables.printStackTrace();}
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
       return new ClientBuilder()
                .setId(resultSet.getLong("id"))
                .setAddress(resultSet.getString("address"))
                .setName(resultSet.getString("name"))
                .setCNP(resultSet.getLong("CNP"))
                .build();

    }

}
