import DAO.Console.ConsoleManager;
import DAO.Interfaces.ApartmentRepository;
import DAO.Interfaces.RequestRepository;
import DAO.Interfaces.ReservationRepository;
import DAO.Interfaces.TenantRepository;
import DAO.MySql.MySqlApartmentRepository;
import DAO.MySql.MySqlRequestRepository;
import DAO.MySql.MySqlReservationRepository;
import DAO.MySql.MySqlTenantRepository;
import Domain.Apartment;

import java.util.Scanner;

public class MainStarter {
    static{
        ApartmentRepository apartmentRepository  = new MySqlApartmentRepository();
        TenantRepository tenantRepository = new MySqlTenantRepository();
        RequestRepository requestRepository = new MySqlRequestRepository();
        ReservationRepository reservationRepository = new MySqlReservationRepository();
    }

    public static void main(String[] args) {
        /*Use scanner class to read from console and std out to write to console
            Logic: realise cyclic request for UI.
            Console menu:
             1.	Apartment management
             2.	Booking form
             3.	Registration for tenant
             4.	Statistic
                a.	People who leaves today
                b.	People who left on date(input date);

         */
        String ans;
        boolean con = true;
        Scanner cin = new Scanner(System.in);
        do {
            writeMenuOptions();
            ans = cin.nextLine();
            switch(ans){
                case "1": apartmentManagement(); break;
                case "2": bookingForm();break;
                case "3": registration();break;
                default: con = false;
            }

        }while (con);

    }

    private static void registration() {
    }

    private static void bookingForm() {
    }

    private static void apartmentManagement() {
        ConsoleManager cm = new ConsoleManager();
        Apartment apartment = cm.readApartment();
    }

    private static void writeMenuOptions() {
        System.out.println("1.\tApartment management");
        System.out.println("2.\tBooking form");
        System.out.println("3.\tRegistration for tenant");
        System.out.println("->");
    }
}
