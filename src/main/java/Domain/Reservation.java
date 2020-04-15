package Domain;

public class Reservation {
    private long tenantId;
    private long apartmentId;
    private long requestId;

    public Reservation(){

    }
    public Reservation(long tenantId, long requestId) {
        this.tenantId = tenantId;
        this.apartmentId = apartmentId;
        this.requestId = requestId;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
}
