package DAO;

import Domain.Apartment;

import java.util.List;

public interface ApartmentRepository {

    int addApartment(Apartment apartment); // Add apartment to DB
    int deleteApartment(Apartment apartment); // Delete apartment to DB
    //TODO Edit operations

    List<Apartment> findAll(); // Select query to get all apartments from DB
    Apartment findById(long id); // Select query to get  apartment by ID
}
