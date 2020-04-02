package DAO;

import Domain.Apartment;
import Domain.Reservation;
import Domain.Tenant;

import java.util.List;

public interface ReservationRepository {
    Apartment findApartmentByTenantId(long id);
    Tenant findTenantByApartmentId(long id);
    void addReservation(Reservation reservation);
    List<Reservation> findAll();
}
