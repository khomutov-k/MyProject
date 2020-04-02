package Domain;

public class Apartment {
    private long id;
    private int capacity;
    private ApartmentType apartmentType;
    private int number;
    private int price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getApartmentType() {
        return apartmentType.toString();
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
