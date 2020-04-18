package DAO.Interfaces;

import DAO.MyExceptions.IdNotFoundException;
import Domain.Tenant;

import java.util.Date;
import java.util.List;

public interface TenantRepository {
    long addTenant(Tenant Tenant) ; // Add Tenant to DB
    int deleteTenant(long id); // Delete Tenant from DB
    List<Tenant> findAll() throws IdNotFoundException; // Select query to get all tenants from DB
    Tenant findById(long id); // Select query to get  Tenant by ID
    List<Tenant> findByDate(Date date);
}
