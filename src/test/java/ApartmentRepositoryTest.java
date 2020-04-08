import DAO.MySql.MySqlApartmentRepository;
import Domain.Apartment;
import Domain.ApartmentType;
import org.testng.Assert;
import org.testng.annotations.Test;

//TODO change data
public class ApartmentRepositoryTest {

    @Test
    public void testAddApartment(){
        MySqlApartmentRepository rep = new MySqlApartmentRepository();
        Apartment apartment = new Apartment();
        apartment.setCapacity(2);
        apartment.setNumber(25);
        apartment.setPrice(600);
        apartment.setApartmentType(ApartmentType.PRESIDENTLUX);
        int res = rep.addApartment(apartment);
        Assert.assertEquals(res,0);
    }
    @Test
    public void testDeleteApartment(){
        MySqlApartmentRepository rep = new MySqlApartmentRepository();
        Apartment apartment = new Apartment();
        apartment.setId(1);
        int res = rep.deleteApartment(apartment);
        Assert.assertEquals(res,0);
    }
    @Test
    public void testAddApartmentAndFindBy(){
        MySqlApartmentRepository rep = new MySqlApartmentRepository();
        Apartment apartment = new Apartment();
        apartment.setCapacity(2);
        apartment.setNumber(104);
        apartment.setPrice(100);
        apartment.setApartmentType(ApartmentType.SUIT);
        rep.addApartment(apartment);
        Apartment newApartment = rep.findById(12);
        Assert.assertEquals(apartment.getApartmentType(), newApartment.getApartmentType());
    }
//    @Test
//    public void testDeleteApartmentAndFindBy(){
//        Apartment apartment = new Apartment();
//        apartment.setId(1);
//        apartment.setApartmentType(ApartmentType.SUIT);
//        rep.deleteApartment(apartment);
//        Apartment newApartment = rep.findById(1);
//        Assert.assertEquals(apartment.getApartmentType(), newApartment.getApartmentType());
//    }
}
