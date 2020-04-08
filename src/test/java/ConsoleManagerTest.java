import DAO.Console.ConsoleManager;
import Domain.Apartment;
import Domain.ApartmentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsoleManagerTest {
    ConsoleManager cm;
    @BeforeClass
    public void init(){
        cm = new ConsoleManager();
    }

    @Test(enabled = false)
    public void apartmentReadTest(){
        Apartment readApartment = cm.readApartment();
        Apartment apartment = new Apartment();
        apartment.setCapacity(2);
        apartment.setNumber(104);
        apartment.setPrice(200);
        apartment.setApartmentType(ApartmentType.SUIT);
        Assert.assertEquals(readApartment.getCapacity(),apartment.getCapacity());
        Assert.assertEquals(readApartment.getNumber(),apartment.getNumber());
        Assert.assertEquals(readApartment.getPrice(),apartment.getPrice());
    }
}
