package Domain;

import java.util.Date;

public class Request {
    private long id;
    private int NumberOfPeople;
    private Date ArrivalDate;
    private Date DepartureDate;
    private ApartmentType WantedType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApartmentType getWantedType() {
        return WantedType;
    }

    public void setWantedType(ApartmentType wantedType) {
        WantedType = wantedType;
    }

    public int getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        NumberOfPeople = numberOfPeople;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(Date departureDate) {
        DepartureDate = departureDate;
    }
}
