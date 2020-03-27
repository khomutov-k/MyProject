import DAO.MySqlApartmentRepository;
import Domain.Apartment;
import Domain.ApartmentType;
import org.junit.Test;
import org.testng.Assert;

//TODO change data
public class ApartmentRepositoryTest {
    @Test
    public void testAddApartmentAndFindBy(){
        MySqlApartmentRepository rep = new MySqlApartmentRepository();
        Apartment apartment = new Apartment();
        apartment.setCapacity(2);
        apartment.setNumber(104);
        apartment.setPrice(100);
        apartment.setApartmentType(ApartmentType.SUIT);
        rep.addApartment(apartment);
        Apartment newApartment = rep.findById(1);
        Assert.assertEquals(apartment.getApartmentType(), newApartment.getApartmentType());
    }
    @Test
    public void testDeleteApartmentAndFindBy(){
        MySqlApartmentRepository rep = new MySqlApartmentRepository();
        Apartment apartment = new Apartment();
        apartment.setCapacity(2);
        apartment.setNumber(104);
        apartment.setPrice(100);
        apartment.setApartmentType(ApartmentType.SUIT);
        rep.deleteApartment(apartment);
        Apartment newApartment = rep.findById(1);
        Assert.assertEquals(apartment.getApartmentType(), newApartment.getApartmentType());
    }
}
