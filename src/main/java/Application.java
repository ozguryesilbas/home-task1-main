import ee.cleankitchen.orderservice.OrderServiceClient;
import ee.cleankitchen.orderservice.OrderServiceClientImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class Application {

    private int limitForMonday = 4;
    private int limitForOtherDays = 2;

    ZoneId defaultZoneId = ZoneId.systemDefault();
    LocalDate monday = LocalDate.of(2021, 12, 27); // monday
    Date date = Date.from(monday.atStartOfDay(defaultZoneId).toInstant());
    String mondayString = new SimpleDateFormat("EEEE").format(date);

    private String deliveryTimeString1 = "10:30";
    private String deliveryTimeString2 = "12:30";
    private String deliveryTimeString3 = "18:30";

    LocalTime localTime1 = LocalTime.of(10, 30);
    LocalTime localTime2 = LocalTime.of(12, 30);
    LocalTime localTime3 = LocalTime.of(18, 30);

    private List<String> fullDeliveryTimeList = Arrays.asList(new String[]{deliveryTimeString1, deliveryTimeString2, deliveryTimeString3});

    OrderServiceClient orderServiceClient = new OrderServiceClientImpl();

    public List<Map<String, Object>> getOrderMapListByDate(LocalDate date) {
        List<Map<String, Object>> orderMapList = orderServiceClient.getBy(date);
        return orderMapList;
    }

    public List<String> getAvailableDeliveryTimes(LocalDate localDate) {
        List<Map<String, Object>> orderMapList = getOrderMapListByDate(localDate);
        List<String> avaliableDeliveryTimes = new ArrayList<>();

        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        String dayWeekText = new SimpleDateFormat("EEEE").format(date);

        int limit = limitForOtherDays;
        if (mondayString.equals(dayWeekText)) { // use limitForMonday
            limit = limitForMonday;
        }

        if (orderMapList != null) {
            int deliveryTime1Count = 0;
            int deliveryTime2Count = 0;
            int deliveryTime3Count = 0;

            for (Map<String, Object> orderMap : orderMapList) {
                if (localTime1.equals(orderMap.get("time"))) {
                    deliveryTime1Count = deliveryTime1Count + 1;
                } else if (localTime2.equals(orderMap.get("time"))) {
                    deliveryTime2Count = deliveryTime2Count + 1;
                } else if (localTime3.equals(orderMap.get("time"))) {
                    deliveryTime3Count = deliveryTime3Count + 1;
                }
            }

            if (limit > deliveryTime1Count) {
                avaliableDeliveryTimes.add(deliveryTimeString1);
            }
            if (limit > deliveryTime2Count) {
                avaliableDeliveryTimes.add(deliveryTimeString2);
            }
            if (limit > deliveryTime3Count) {
                avaliableDeliveryTimes.add(deliveryTimeString3);
            }

            return avaliableDeliveryTimes;

        }

        return fullDeliveryTimeList;

    }

    public void addNewOrder(Map<String, Object> orderMap){
        orderServiceClient.addNewOrder(orderMap);
    }

}
