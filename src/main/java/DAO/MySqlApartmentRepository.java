package DAO;

import Domain.Apartment;
import Domain.ApartmentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MySqlApartmentRepository implements ApartmentRepository{
    //TODO javadoc
    final String url = "jdbc:mysql://localhost:3306/hoteldb";
    final String user = "root";
    final String password = "root";

    public int addApartment(Apartment apartment) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO  apartment set " +
                    "capacity = ?," +
                    "apartmentType = ?," +
                    "apartmentNumber = ?," +
                    "price = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,apartment.getCapacity());
            preparedStatement.setString(2,apartment.getApartmentType());
            preparedStatement.setInt(3,apartment.getNumber());
            preparedStatement.setInt(4,apartment.getPrice());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int deleteApartment(Apartment apartment) {
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            long id = apartment.getId();
            String sql = "DELETE * FROM apartment where idApartment =" + id;
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<Apartment>();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM apartment");
            if(rs.next())
            {
                Apartment apartment = new Apartment();
                apartment.setId(rs.getInt("idApartment") );
                apartment.setCapacity(rs.getInt("capacity") );
                //TODO exception handling
                apartment.setApartmentType(ApartmentType.valueOf(rs.getString("apartmentType")) );
                apartment.setNumber(rs.getInt("apartmentNumber"));
                apartment.setPrice(rs.getInt("price") );
                apartments.add(apartment);
            }
            return apartments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //TODO idNotFoundException?
    public Apartment findById(long id) {
        try{
            Apartment apartment = new Apartment();
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM apartment where idApartment =" + id);
            if(rs.next())
            {
                apartment.setId(rs.getInt("idApartment") );
                apartment.setCapacity(rs.getInt("capacity") );
                try{
                    String aType = rs.getString("apartmentType");
                    apartment.setApartmentType(ApartmentType.valueOf(aType.toUpperCase()));
                }catch(NullPointerException ex){
                    System.out.println("Exception: Enum not found.");
                    return null;
                }
                apartment.setNumber(rs.getInt("apartmentNumber"));
                apartment.setPrice(rs.getInt("price") );
                return apartment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
