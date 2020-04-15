package DAO.Console;

import Domain.Apartment;
import Domain.ApartmentType;
import Domain.Booking;
import Domain.Tenant;

import java.io.Closeable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConsoleManager implements Closeable {
    Scanner scanner = new Scanner(System.in);
    public Apartment readApartment(){
        Apartment apartment = new Apartment();
        System.out.print("Введите количество человек, на которое рассчитана данные апартаменты:");
        apartment.setCapacity(scanner.nextInt()); scanner.nextLine();
        System.out.print("Введите тип апартаментов(Single, Delux, Suit, PresidentLux):");
        final String type = scanner.nextLine();
        apartment.setApartmentType(ApartmentType.valueOf(type.toUpperCase()));
        System.out.print("Введите номер апартаментов:");
        apartment.setNumber(scanner.nextInt());
        System.out.print("Введите стоимость апартаментов:");
        apartment.setPrice(scanner.nextInt());
        return apartment;
    }
    public void writeToConsole(Apartment apartment){
        System.out.print("Id: ");
        System.out.println(apartment.getId());
        System.out.print("Количество человек, на которое рассчитана данные апартаменты:");
        System.out.println(apartment.getCapacity());
        System.out.print("Тип апартаментов(Single, Delux, Suit, PresidentLux):");
        System.out.println(apartment.getApartmentType());
        System.out.print("Номер апартаментов:");
        System.out.println(apartment.getNumber());
        System.out.print("Стоимость апартаментов:");
        System.out.println(apartment.getPrice());
    }
    public Tenant readTenant(){
        Tenant tenant = new Tenant();
        System.out.print("Введите ваше Имя");
        tenant.setFirstName(scanner.nextLine());
        System.out.print("Введите вашу Фамилию");
        tenant.setSecondName(scanner.nextLine());
        System.out.print("Введите ваш эл. почту:");
        tenant.setEmail(scanner.nextLine());
        System.out.print("Введите номер телефона");
        tenant.setPhone(scanner.nextLine());
        return tenant;
    }
    //TODO check date 
    public Booking readBooking() throws ParseException {
        Booking Booking = new Booking();
        System.out.print("Введите количество человек:");
        Booking.setNumberOfPeople(scanner.nextInt()); scanner.nextLine();
        System.out.print("Введите тип апартаментов(Single, Delux, Suit, PresidentLux):");
        Booking.setWantedType(ApartmentType.valueOf(scanner.nextLine().toUpperCase()));
        System.out.print("Введите дату приезда(yyyy-MM-dd):");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Booking.setArrivalDate(dateFormat.parse(scanner.nextLine()));
        System.out.print("Введите дату отъезда(yyyy-MM-dd):");
        Booking.setDepartureDate(dateFormat.parse(scanner.nextLine()));
        return Booking;
    }
    public String nextLine(){
        return this.scanner.nextLine();
    }
    public Long nextLong(){
        return this.scanner.nextLong();
    }
    @Override
    public void close() {
        scanner.close();
    }
}
