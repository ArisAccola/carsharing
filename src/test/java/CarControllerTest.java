import com.ffhs.carsharing_v2.controllers.CarController;
import com.ffhs.carsharing_v2.dto.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarControllerTest {

    CarController cars = new CarController();

    @Test
    public void listTestDetails_positive() {
        List<Car> listCarDetails = cars.getCars();

        Assertions.assertEquals(listCarDetails.size(), 1);
        Assertions.assertEquals(listCarDetails.get(0).getCarId(), 1);
        Assertions.assertEquals(listCarDetails.get(0).getCarManufacturer(), "Range Rover");

    }

    @Test
    public void listTestDetails_negative() {
        List<Car> listCarDetails = cars.getCars();

        Assertions.assertNotEquals(listCarDetails.size(), 2);
        Assertions.assertNotEquals(listCarDetails.get(0).getCarId(), 20);
        Assertions.assertNotEquals(listCarDetails.get(0).getCarManufacturer(), "Range");
    }

}
