package DAO;

import Domain.ApartmentType;
import Domain.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO javadoc
public class MySqlRequestRepository implements RequestRepository{

    public int addRequest(Request Request) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectionFactory.createConnection()
        ){
            String sql = "INSERT INTO  request_booking set " +
                    "numberOfPeople = ?," +
                    "apartmentType = ?," +
                    "arrivalDate = ?," +
                    "departureDate = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Request.getNumberOfPeople());
            preparedStatement.setString(2,Request.getWantedType());
            preparedStatement.setString(3,"'"+Request.getArrivalDate()+"'");
            preparedStatement.setString(4,"'"+Request.getDepartureDate()+"'");
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

    public int deleteRequest(Request Request) {
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.createConnection();
            stmt = connection.createStatement();
            long id = Request.getId();
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

    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try(Connection connection = ConnectionFactory.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM request_booking")){
            if(rs.next())
            {
                Request request = new Request();
                request.setId(rs.getInt("idRequest") );
                request.setNumberOfPeople(rs.getInt("numberOfPeople") );
                request.setWantedType(ApartmentType.valueOf(rs.getString("ApartmentType")) );
                request.setArrivalDate(rs.getDate( "arrivalDate"));
                request.setDepartureDate(rs.getDate("DepartureDate"));
                requests.add(request);
            }
            return requests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Request findById(long id)  {
        Request request = new Request();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM request_booking where idRequest =" + id)){
            if (rs.next()) {
                request.setId(rs.getInt("idRequest") );
                request.setNumberOfPeople(rs.getInt("numberOfPeople") );
                request.setWantedType(ApartmentType.valueOf(rs.getString("ApartmentType")) );
                request.setArrivalDate(rs.getDate( "arrivalDate"));
                request.setDepartureDate(rs.getDate("DepartureDate"));
            }
            return request;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return new Request();
    }
}
