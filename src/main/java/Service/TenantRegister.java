package Service;

import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.RequestRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import Domain.Apartment;
import Domain.Booking;
import Domain.Reservation;
import Domain.Tenant;

import java.util.List;
import java.util.Optional;

public class TenantRegister {
    private ApartmentRepository apartmentRepository;
    private TenantRepository tenantRepository;
    private RequestRepository requestRepository;
    private ReservationRepository reservationRepository;

    public TenantRegister(ApartmentRepository apartmentRepository,
                          TenantRepository tenantRepository,
                          RequestRepository requestRepository,
                          ReservationRepository reservationRepository) {
        this.apartmentRepository = apartmentRepository;
        this.tenantRepository = tenantRepository;
        this.requestRepository = requestRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean registerTenant(Tenant tenant, Booking booking){

        List<Apartment> apartments = apartmentRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAll();

        //Looking for free apartment
        Optional<Apartment> apartment = apartments.stream().filter(room -> {
            for (Reservation reservation:reservations){
                if (reservation.getApartmentId() == room.getId()) return false;
            }
            return true;
        }).findFirst();


        if (apartment.isPresent()) {
            Reservation reservation = new Reservation(tenant.getId(), apartment.get().getId(), booking.getId());
            reservationRepository.addReservation(reservation);
            tenantRepository.addTenant(tenant);
            return true;
        }
        return false;
    }
}
