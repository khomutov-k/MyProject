package DAO.Interfaces;

import DAO.MyExceptions.IdNotFoundException;
import Domain.Apartment;

import java.util.List;

public interface ApartmentRepository {

    int addApartment(Apartment apartment) ; // Add apartment to DB
    int deleteApartment(long id); // Delete apartment from DB by id
    List<Apartment> findAll() ; // Select query to get all apartments from DB
    Apartment findById(long id) throws IdNotFoundException; // Select query to get  apartment by ID
}
