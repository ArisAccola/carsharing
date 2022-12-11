import com.ffhs.carsharing_v2.dto.Reservation;
import com.ffhs.carsharing_v2.helpers.ReservationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReservationHelperTest {

    @Test
    public void listTestDetailsPositive(){
        List<Reservation> reservationList = ReservationHelper.loadReservation();

        Assertions.assertEquals(reservationList.size(), 1);
    }
}
