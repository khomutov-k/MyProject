package DAO.Interfaces;

import Domain.Apartment;
import Domain.Reservation;
import Domain.Tenant;

import java.util.List;

public interface ReservationRepository {
    Apartment findApartmentByTenantId(long id);
    Tenant findTenantByApartmentId(long id);
    int addReservation(Reservation reservation);
    List<Reservation> findAll();
}
