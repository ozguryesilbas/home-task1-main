import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApplicationTest {

    private String deliveryTimeString1 = "10:30";
    private String deliveryTimeString2 = "12:30";
    private String deliveryTimeString3 = "18:30";

    LocalTime deliveryTime1 = LocalTime.of(10, 30);
    LocalTime deliveryTime2 = LocalTime.of(12, 30);
    LocalTime deliveryTime3 = LocalTime.of(18, 30);

    LocalDate tuesday = LocalDate.of(2021, 12, 28); //tuesday
    LocalDate monday = LocalDate.of(2021, 12, 27); // monday
    LocalDate wednesday = LocalDate.of(2021, 12, 29); // wednesday

    @Test
    public void getDeliveryTimesForScenerio1() {
        Application application = new Application();

        Map<String, Object> orderMap1 = new HashMap<>();
        orderMap1.put("date", tuesday);
        orderMap1.put("time", deliveryTime1);
        orderMap1.put("orderId", "111");
        orderMap1.put("customerId", "a-111");
        application.addNewOrder(orderMap1);

        Map<String, Object> orderMap2 = new HashMap<>();
        orderMap2.put("date", tuesday);
        orderMap2.put("time", deliveryTime1);
        orderMap2.put("orderId", "112");
        orderMap2.put("customerId", "a-112");
        application.addNewOrder(orderMap2);

        List<String> expectedResult = Arrays.asList(new String[]{deliveryTimeString2, deliveryTimeString3});
        Assertions.assertEquals(expectedResult, application.getAvailableDeliveryTimes(tuesday));

    }

    @Test
    public void getDeliveryTimesForScenerio2() {
        Application application = new Application();

        Map<String, Object> orderMap1 = new HashMap<>();
        orderMap1.put("date", monday);
        orderMap1.put("time", deliveryTime2);
        orderMap1.put("orderId", "111");
        orderMap1.put("customerId", "a-111");
        application.addNewOrder(orderMap1);

        Map<String, Object> orderMap2 = new HashMap<>();
        orderMap2.put("date", monday);
        orderMap2.put("time", deliveryTime2);
        orderMap2.put("orderId", "112");
        orderMap2.put("customerId", "a-112");
        application.addNewOrder(orderMap2);

        Map<String, Object> orderMap3 = new HashMap<>();
        orderMap3.put("date", monday);
        orderMap3.put("time", deliveryTime2);
        orderMap3.put("orderId", "113");
        orderMap3.put("customerId", "a-113");
        application.addNewOrder(orderMap3);

        Map<String, Object> orderMap4 = new HashMap<>();
        orderMap4.put("date", monday);
        orderMap4.put("time", deliveryTime3);
        orderMap4.put("orderId", "113");
        orderMap4.put("customerId", "a-114");
        application.addNewOrder(orderMap4);

        Map<String, Object> orderMap5 = new HashMap<>();
        orderMap5.put("date", monday);
        orderMap5.put("time", deliveryTime3);
        orderMap5.put("orderId", "114");
        orderMap5.put("customerId", "a-115");
        application.addNewOrder(orderMap5);

        Map<String, Object> orderMap6 = new HashMap<>();
        orderMap6.put("date", monday);
        orderMap6.put("time", deliveryTime3);
        orderMap6.put("orderId", "115");
        orderMap6.put("customerId", "a-116");
        application.addNewOrder(orderMap6);

        Map<String, Object> orderMap7 = new HashMap<>();
        orderMap7.put("date", monday);
        orderMap7.put("time", deliveryTime3);
        orderMap7.put("orderId", "116");
        orderMap7.put("customerId", "a-117");
        application.addNewOrder(orderMap7);

        List<String> expectedResult = Arrays.asList(new String[]{deliveryTimeString1, deliveryTimeString2});
        Assertions.assertEquals(expectedResult, application.getAvailableDeliveryTimes(monday));

    }

    @Test
    public void getDeliveryTimesForScenerio3() {
        Application application = new Application();

        List<String> expectedResult = Arrays.asList(new String[]{deliveryTimeString1, deliveryTimeString2, deliveryTimeString3});
        Assertions.assertEquals(expectedResult, application.getAvailableDeliveryTimes(wednesday));

    }
}
