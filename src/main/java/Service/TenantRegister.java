package Service;

import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.BookingRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import DAO.MyExceptions.IdNotFoundException;
import Domain.Apartment;
import Domain.Booking;
import Domain.Reservation;
import Domain.Tenant;

import java.util.Collections;
import java.util.Comparator;
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
        List<Apartment> apartments = Collections.emptyList();
        List<Reservation> tenantReservations;

        Booking booking;
        Reservation tenantReservation = new Reservation();

        int guestBooked = 0;
        try{
            apartments = apartmentRepository.findAll();
            tenantReservations = reservationRepository.findById(tenant.getId());
            tenantReservation = tenantReservations.get(0);
            booking = bookingRepository.findById(tenantReservation.getRequestId());
            guestBooked = booking.getNumberOfPeople();
        }catch (IdNotFoundException e) {
            System.out.println("Entered id is invalid. Please try again.");
        }
        int guestCounter = 0;
        List<Reservation> reservations = reservationRepository.findAll();
        do {
            //Looking for free apartment
            Optional<Apartment> apartment = apartments.stream().filter(room -> {
                for (Reservation reservation : reservations) {
                    if (reservation.getApartmentId() == room.getId()) return false;
                }
                return true;
            }).max(Comparator.comparingInt(Apartment::getCapacity));

            if (apartment.isPresent()) {
                tenantReservation.setApartmentId(apartment.get().getId());

                if (reservationRepository.updateReservation(tenantReservation) == -1){
                    System.out.println("Update error");
                }
                guestCounter += apartment.get().getCapacity();
            }
        }while(guestCounter < guestBooked);
        return false;
    }
}
