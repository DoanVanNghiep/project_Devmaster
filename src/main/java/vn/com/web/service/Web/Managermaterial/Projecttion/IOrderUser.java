package vn.com.web.service.Web.Managermaterial.Projecttion;

import java.time.Instant;

public interface IOrderUser {
    String idOrder();
    int trangthai();
    Instant ordersDate();
    String name();
    int quantity();
    double price();
}
