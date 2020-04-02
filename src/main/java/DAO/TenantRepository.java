package DAO;

import Domain.Tenant;

import java.util.List;

public interface TenantRepository {
    int addTenant(Tenant Tenant) ; // Add Tenant to DB
    int deleteTenant(Tenant Tenant); // Delete Tenant from DB
    //TODO Edit operations

    List<Tenant> findAll(); // Select query to get all tenants from DB
    Tenant findById(long id); // Select query to get  Tenant by ID
}
