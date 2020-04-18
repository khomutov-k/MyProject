package DAO.Interfaces;

import DAO.MyExceptions.IdNotFoundException;
import Domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findById(long id) throws IdNotFoundException;
    int updateReservation(Reservation reservation);
    int addReservation(Reservation reservation);
    List<Reservation> findAll();
}
