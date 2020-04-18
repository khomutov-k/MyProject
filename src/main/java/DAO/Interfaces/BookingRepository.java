package DAO.Interfaces;

import DAO.MyExceptions.IdNotFoundException;
import Domain.Booking;

import java.util.List;

public interface BookingRepository {
    long addBooking(Booking booking) ; // Add Request to DB
    int deleteBooking(long id); // Delete Request from DB
//    long getBookingId(Booking booking);

    List<Booking> findAll() throws IdNotFoundException; // Select query to get all Requests from DB
    Booking findById(long id) throws IdNotFoundException; // Select query to get  Request by ID
}
