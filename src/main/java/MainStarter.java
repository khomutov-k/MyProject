import DAO.Console.ConsoleManager;
import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.BookingRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import DAO.MySql.MySqlApartmentRepository;
import DAO.MySql.MySqlBookingRepository;
import DAO.MySql.MySqlReservationRepository;
import DAO.MySql.MySqlTenantRepository;
import Domain.Apartment;
import Domain.Booking;
import Domain.Reservation;
import Domain.Tenant;
import Service.TenantRegister;

import java.text.ParseException;
import java.util.List;

public class MainStarter {
    static     ApartmentRepository apartmentRepository  = new MySqlApartmentRepository();
    static     TenantRepository tenantRepository = new MySqlTenantRepository();
    static     BookingRepository bookingRepository = new MySqlBookingRepository();
    static     ReservationRepository reservationRepository = new MySqlReservationRepository();
    static     ConsoleManager cm = new ConsoleManager();
    static     TenantRegister register = new TenantRegister(apartmentRepository,tenantRepository, bookingRepository,reservationRepository);
    public static void main(String[] args) {
        /*
        Use ConsoleManager class to read from console and std.out to write to console
            Logic: realise cyclic request for UI.
            Console menu:
             1.	Apartment management
             2.	Registration for tenant
             3.	Statistic
                a.	People who leaves today
                b.	People who left on date(input date);

         */
        String ans;
        boolean con = true;
        try {
            do {
                writeMenuOptions();
                ans = cm.nextLine();
                switch (ans) {
                    case "1":
                        apartmentManagement();
                        break;
                    case "2":
                        bookingForm();
                        break;
                    case "3":
                        registration();
                        break;
                    default:
                        con = false;
                }
            } while (con);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            cm.close();
        }
    }

    private static void registration() {
        System.out.println("Please enter tenant id:");
        Tenant tenant = tenantRepository.findById(cm.nextLong());
        register.registerTenant(tenant);
    }

    private static void bookingForm() {
        System.out.println("Here you could fill in booking form.");
        try {
            Booking booking =  cm.readBooking();
            bookingRepository.addRequest(booking);
            System.out.println("Please enter tenant information:");
            Tenant newTenant = cm.readTenant();
            Reservation reservation = new Reservation(newTenant.getId(),booking.getId());
            reservationRepository.addReservation(reservation);
        } catch (ParseException e) {
            System.out.println("Input error:Error in type of apartment");
        }
    }

    private static void apartmentManagement() {
        /*
        1.Add apartment
        2.Delete apartment by id
        3.Show list of apartments
        */
        subMenuForApartmentManagement();

        String ans1 = cm.nextLine();
        switch (ans1){
            case "1": {
                Apartment newApartment = cm.readApartment();
                //TODO add potential option to cancel change before
                apartmentRepository.addApartment(newApartment);
            }break;
            case "2": deleteApartment();break;
            case "3": {
                List<Apartment> apartmentList = apartmentRepository.findAll();
                for(Apartment apartment:apartmentList){
                    cm.writeToConsole(apartment);
                }
            }break;
        }

    }

    private static void deleteApartment() {
        System.out.println("Please enter id of apartments you wish to delete: ");
        long id = cm.nextLong();
        //TODO check existence of the apartment if not throw exception
        System.out.println("Do you want to delete this apartment:");
        Apartment deletedApartment = apartmentRepository.findById(id);
        cm.writeToConsole(deletedApartment);
        System.out.println("Are you sure?(yes/no)");
        cm.nextLine();
        String ans11 = cm.nextLine();
        if (ans11.equalsIgnoreCase("yes")) {
            apartmentRepository.deleteApartment(id);
        }
    }

    private static void subMenuForApartmentManagement() {
        System.out.println("Choose one option please:");
        System.out.println("1.Add apartment");
        System.out.println("2.Delete apartment by id");
        System.out.println("3.Show list of all apartments");
        System.out.print("->");
    }

    private static void writeMenuOptions() {
        System.out.println("1.\tApartment management");
        System.out.println("2.\tBooking form");
        System.out.println("3.\tTenant registration");
        System.out.print("->");
    }
}
