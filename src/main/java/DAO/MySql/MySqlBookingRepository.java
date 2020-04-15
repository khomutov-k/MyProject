package DAO.MySql;

import DAO.ConnectionFactory;
import DAO.Interfaces.BookingRepository;
import Domain.ApartmentType;
import Domain.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO javadoc
public class MySqlBookingRepository implements BookingRepository {

    public int addRequest(Booking Booking) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectionFactory.createConnection()
        ){
            String sql = "INSERT INTO  request_booking set " +
                    "numberOfPeople = ?," +
                    "apartmentType = ?," +
                    "arrivalDate = ?," +
                    "departureDate = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Booking.getNumberOfPeople());
            preparedStatement.setString(2, Booking.getWantedType());
            preparedStatement.setObject(3, Booking.getArrivalDate());
            preparedStatement.setObject(4,Booking.getDepartureDate());
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

    public int deleteRequest(long id) {
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.createConnection();
            stmt = connection.createStatement();
            String sql = "DELETE FROM request_booking where idRequest =" + id;
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

    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        try(Connection connection = ConnectionFactory.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM request_booking")){
            while(rs.next())
            {
                Booking booking = new Booking();
                bookingFilling(rs, booking);
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void bookingFilling(ResultSet rs, Booking booking) throws SQLException {
        booking.setId(rs.getInt("idRequest") );
        booking.setNumberOfPeople(rs.getInt("numberOfPeople") );
        booking.setWantedType(ApartmentType.valueOf(rs.getString("ApartmentType")) );
        booking.setArrivalDate(rs.getObject( "arrivalDate", LocalDate.class));
        booking.setDepartureDate(rs.getObject("DepartureDate",LocalDate.class));
    }

    public Booking findById(long id)  {
        Booking booking = new Booking();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM request_booking where idRequest =" + id)){
            if (rs.next()) {
                bookingFilling(rs, booking);
            }
            return booking;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return new Booking();
    }
}
