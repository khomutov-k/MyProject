package DAO.MySql;

import DAO.ConnectionFactory;
import DAO.Interfaces.BookingRepository;
import DAO.MyExceptions.IdNotFoundException;
import Domain.ApartmentType;
import Domain.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO javadoc
public class MySqlBookingRepository implements BookingRepository {

    public long addBooking(Booking Booking) {
        try (Connection connection = ConnectionFactory.createConnection();
             Statement statement = connection.createStatement()
        ){
            String sql = "INSERT INTO  request_booking set "+
                    "numberOfPeople = " + Booking.getNumberOfPeople()+
                    ",apartmentType = '"+Booking.getWantedType()+"',"+
                    "arrivalDate = '"+Booking.getArrivalDate()+"',"+
                    "departureDate = '"+Booking.getDepartureDate()+"';";

            statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public int deleteBooking(long id) {
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

    public List<Booking> findAll() throws IdNotFoundException {
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
            if (bookings.isEmpty()){
                throw new IdNotFoundException();
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
        booking.setWantedType(ApartmentType.valueOf(rs.getString("ApartmentType").toUpperCase()) );
        booking.setArrivalDate(rs.getObject( "arrivalDate", LocalDate.class));
        booking.setDepartureDate(rs.getObject("DepartureDate",LocalDate.class));
    }

    public Booking findById(long id) throws IdNotFoundException {
        Booking booking = new Booking();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM request_booking where idRequest =" + id)){
            if (rs.next()) {
                bookingFilling(rs, booking);
            }
            if (booking.getId() == 0){
                throw new IdNotFoundException();
            }
            return booking;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return new Booking();
    }
}
