package vn.com.web.service.Web.Managermaterial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.web.service.Web.Managermaterial.Domain.Order;
import vn.com.web.service.Web.Managermaterial.Domain.OrdersDetail;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select o from Order o where o.idorders= :idOder")
    Order getOrder(@Param("idOder") Integer idOder);
    Order findAllById(Integer idOrder);
}
