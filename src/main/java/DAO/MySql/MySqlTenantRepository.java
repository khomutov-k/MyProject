package DAO.MySql;

import DAO.ConnectionFactory;
import DAO.Interfaces.TenantRepository;
import DAO.MyExceptions.IdNotFoundException;
import Domain.Tenant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//TODO javadoc
public class MySqlTenantRepository implements TenantRepository {

    public long addTenant(Tenant Tenant) {
        try (Connection connection = ConnectionFactory.createConnection();
             Statement statement = connection.createStatement()
        ){
            String sql = "INSERT INTO  Tenant set " +
                    "firstName = '" +Tenant.getFirstName() + "',"+
                    "secondName = '" +Tenant.getSecondName() +"',"+
                    "email = '" + Tenant.getEmail() + "'," +
                     "phone = "+Tenant.getPhone()+";";
            statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int deleteTenant(long id) {
        Statement stmt = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.createConnection();
            stmt = connection.createStatement();
            String sql = "DELETE FROM Tenant where idTenant =" + id;
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

    public List<Tenant> findAll() throws IdNotFoundException {
        List<Tenant> tenants = new ArrayList<>();
        try(Connection connection = ConnectionFactory.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tenant")){
            if(rs.next())
            {
                Tenant Tenant = new Tenant();
                Tenant.setId(rs.getInt("idTenant") );
                Tenant.setFirstName(rs.getString("firstName") );
                Tenant.setSecondName(rs.getString("secondName"));
                Tenant.setEmail(rs.getString("TenantEmail"));
                Tenant.setPhone(rs.getString("phone") );
                tenants.add(Tenant);
            }
            if (tenants.isEmpty()){
                throw new IdNotFoundException();
            }
            return tenants;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Tenant findById(long id)  {
        Tenant Tenant = new Tenant();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Tenant where idTenant =" + id)){
            if (rs.next()) {
                Tenant.setId(rs.getInt("idTenant"));
                Tenant.setFirstName(rs.getString("firstName"));
                Tenant.setSecondName(rs.getString("secondName"));
                Tenant.setEmail(rs.getString("email"));
                Tenant.setPhone(rs.getString("phone"));
            }
            return Tenant;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return new Tenant();
    }

    public List<Tenant> findByDate(Date date) {
        List<Tenant> tenants = new ArrayList<>();
        Tenant tenant = new Tenant();
        try( Connection connection = ConnectionFactory.createConnection();
             Statement stmt = connection.createStatement();
             //TODO check if works correctly: java date and sql date
             ResultSet rs = stmt.executeQuery(
                     "SELECT * " +
                             "FROM Tenant INNER JOIN tenant_apartment_reservations tar " +
                             "on tenant.idTenant = tar.tenant_id  " +
                             "Inner Join request_booking  on tar.request_id = request_booking.idRequest "+
                             "where  request_booking.departureDate =" + date)){
            if (rs.next()) {
                tenant.setId(rs.getInt("idTenant"));
                tenant.setFirstName(rs.getString("firstName"));
                tenant.setSecondName(rs.getString("secondName"));
                tenant.setEmail(rs.getString("email"));
                tenant.setPhone(rs.getString("phone"));
                tenants.add(tenant);
            }
            return tenants;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new IdNotFoundException();
        }
        return Collections.emptyList();
    }
}
