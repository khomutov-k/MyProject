package DAO;

import Domain.Apartment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MySqlApartmentRepository implements ApartmentRepository{
    //TODO javadoc
    public int addApartment(Apartment apartment) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "root");
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
        return 0;
    }

    public List<Apartment> findAll() {
        return null;
    }

    public Apartment findById() {
        return null;
    }
}
