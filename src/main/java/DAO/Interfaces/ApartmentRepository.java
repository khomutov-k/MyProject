package DAO.Interfaces;

import Domain.Apartment;

import java.util.List;

public interface ApartmentRepository {

    int addApartment(Apartment apartment) ; // Add apartment to DB
    int deleteApartment(long id); // Delete apartment from DB by id
    //TODO Edit operations
    List<Apartment> findAll(); // Select query to get all apartments from DB
    Apartment findById(long id); // Select query to get  apartment by ID
}
