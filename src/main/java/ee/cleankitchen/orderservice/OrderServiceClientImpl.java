package ee.cleankitchen.orderservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceClientImpl implements OrderServiceClient {

    List<Map<String, Object>> orderMapList = new ArrayList<Map<String, Object>>();

    @Override
    public List<Map<String, Object>> getBy(LocalDate date) {
        return orderMapList.stream().filter(om -> date.equals(om.get("date"))).collect(Collectors.toList());
    }

    @Override
    public void addNewOrder(Map<String, Object> orderMap) {
        orderMapList.add(orderMap);
    }

}
