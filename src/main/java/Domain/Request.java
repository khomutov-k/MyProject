package Domain;

import java.util.Date;

public class Request {
    private long id;
    private int numberOfPeople;
    private Date arrivalDate;
    private Date departureDate;
    private ApartmentType wantedType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApartmentType getWantedType() {
        return wantedType;
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
