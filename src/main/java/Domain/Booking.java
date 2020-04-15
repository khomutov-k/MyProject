package Domain;

import java.time.LocalDate;

public class Booking {
    private long id;
    private int numberOfPeople;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ApartmentType wantedType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWantedType() {
        return wantedType.toString();
    }

    public void setWantedType(ApartmentType wantedType) {
        this.wantedType = wantedType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
