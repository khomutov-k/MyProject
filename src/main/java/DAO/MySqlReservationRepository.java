package DAO;

import Domain.Apartment;
import Domain.Reservation;
import Domain.Tenant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySqlReservationRepository implements ReservationRepository {
    @Override
    public Apartment findApartmentByTenantId(long id) {
        return null;
    }

    @Override
    public Tenant findTenantByApartmentId(long id) {
        return null;
    }

    @Override
    public int addReservation(Reservation reservation) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectionFactory.createConnection()
        ){
            String sql = "INSERT INTO tenant_apartment_reservations set " +
                    "tenant_id = ?," +
                    "apartment_id = ?," +
                    "request_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,reservation.getTenantId());
            preparedStatement.setLong(2,reservation.getApartmentId());
            preparedStatement.setLong(3,reservation.getRequestId());
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

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try(Connection connection = ConnectionFactory.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tenant_apartment_reservations")){
            if(rs.next())
            {
                final long tenant_id = rs.getLong("tenant_id");
                final long apartment_id = rs.getLong("apartment_id");
                final long request_id = rs.getLong("request_id");
                Reservation reservation = new Reservation(tenant_id,apartment_id,request_id);
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
