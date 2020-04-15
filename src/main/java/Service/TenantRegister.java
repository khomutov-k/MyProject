package Service;

import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.BookingRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import Domain.Apartment;
import Domain.Reservation;
import Domain.Tenant;

import java.util.List;
import java.util.Optional;

public class TenantRegister {
    private ApartmentRepository apartmentRepository;
    private TenantRepository tenantRepository;
    private BookingRepository bookingRepository;
    private ReservationRepository reservationRepository;

    public TenantRegister(ApartmentRepository apartmentRepository,
                          TenantRepository tenantRepository,
                          BookingRepository bookingRepository,
                          ReservationRepository reservationRepository) {
        this.apartmentRepository = apartmentRepository;
        this.tenantRepository = tenantRepository;
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
    }

    public boolean registerTenant(Tenant tenant){

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
            Reservation reservation = reservationRepository.findById(tenant.getId());
            reservation.setApartmentId(apartment.get().getId());
            reservationRepository.updateReservation(reservation);
            return true;
        }
        return false;
    }
}
