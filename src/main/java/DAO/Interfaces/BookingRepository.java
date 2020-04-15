package DAO.Interfaces;

import Domain.Booking;

import java.util.List;

public interface BookingRepository {
    int addRequest(Booking Booking) ; // Add Request to DB
    int deleteRequest(long id); // Delete Request from DB
    //TODO Edit operations

    List<Booking> findAll(); // Select query to get all Requests from DB
    Booking findById(long id); // Select query to get  Request by ID
}
