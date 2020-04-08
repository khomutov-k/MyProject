package DAO.MySql;

import DAO.ConnectionFactory;
import DAO.Interfaces.ApartmentRepository;
import Domain.Apartment;
import Domain.ApartmentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO javadoc
public class MySqlApartmentRepository implements ApartmentRepository {

    public int addApartment(Apartment apartment) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectionFactory.createConnection()
        ){
            String sql = "INSERT INTO  apartment set " +
                    "capacity = ?," +
                    "apartmentType = ?," +
                    "apartmentNumber = ?," +
                    "price = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,apartment.getCapacity());
            preparedStatement.setString(2,apartment.getApartmentType());
            preparedStatement.setInt(3,apartment.getNumber());
            preparedStatement.setInt(4,apartment.getPrice());
            preparedStatement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public int deleteApartment(Apartment apartment) {
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.createConnection();
            stmt = connection.createStatement();
            long id = apartment.getId();
            String sql = "DELETE FROM apartment where idApartment =" + id;
            stmt.executeUpdate(sql);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (stmt !=null) stmt.close();
                if (connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<>();
        try(Connection connection = ConnectionFactory.createConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM apartment")){
                if(rs.next())
                {
                    Apartment apartment = new Apartment();
                    apartment.setId(rs.getInt("idApartment") );
                    apartment.setCapacity(rs.getInt("capacity") );
                    apartment.setApartmentType(ApartmentType.valueOf(rs.getString("apartmentType")) );
                    apartment.setNumber(rs.getInt("apartmentNumber"));
                    apartment.setPrice(rs.getInt("price") );
                    apartments.add(apartment);
                }
                return apartments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Apartment findById(long id)  {
        Apartment apartment = new Apartment();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM apartment where idApartment =" + id)){
                  if (rs.next()) {
                      apartment.setId(rs.getInt("idApartment"));
                      apartment.setCapacity(rs.getInt("capacity"));
                      String aType = rs.getString("apartmentType");
                      apartment.setApartmentType(ApartmentType.valueOf(aType.toUpperCase()));

                      apartment.setNumber(rs.getInt("apartmentNumber"));
                      apartment.setPrice(rs.getInt("price"));
                  }
                    return apartment;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return new Apartment();
    }
}
