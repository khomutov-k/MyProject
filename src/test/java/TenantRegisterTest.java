import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.RequestRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import Domain.Apartment;
import Domain.Booking;
import Domain.Tenant;
import Service.TenantRegister;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TenantRegisterTest {
    @Test
    public void registerTenantTest(){

        //Initialization
        ApartmentRepository apartmentRepository = Mockito.mock(ApartmentRepository.class);
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartment = Mockito.mock(Apartment.class);
        apartments.add(apartment);
        Mockito.when(apartment.getId()).thenReturn(2L);
        Mockito.when(apartmentRepository.findAll()).thenReturn(apartments);
        
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
//        List<Reservation> reservations = new ArrayList<>();
//        Reservation reservation = Mockito.mock(Reservation.class);
//        reservations.add(reservation);
        Mockito.when(reservationRepository.findAll()).thenReturn(Collections.emptyList());
        
        TenantRepository tenantRepository = Mockito.mock(TenantRepository.class);
        RequestRepository requestRepository = Mockito.mock(RequestRepository.class);

        TenantRegister tenantRegister = new TenantRegister(apartmentRepository,tenantRepository,requestRepository,reservationRepository);

        //Parameters for service method
        Tenant newTenant = Mockito.mock(Tenant.class);
        Mockito.when(newTenant.getId()).thenReturn(3L);

        Booking newBooking = Mockito.mock(Booking.class);
        Mockito.when(newBooking.getId()).thenReturn(1L);

        //Execution is here
        tenantRegister.registerTenant(newTenant,newBooking);

        //Assertion

        Mockito.verify(tenantRepository).addTenant(newTenant);

    }
}
