package vn.com.web.service.Web.Managermaterial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.web.service.Web.Managermaterial.Domain.Order;
import vn.com.web.service.Web.Managermaterial.Domain.OrdersDetail;
import vn.com.web.service.Web.Managermaterial.Projecttion.IOrderDetails;
import vn.com.web.service.Web.Managermaterial.Projecttion.IOrderDetailsUser;
import vn.com.web.service.Web.Managermaterial.Projecttion.IOrderUser;
import vn.com.web.service.Web.Managermaterial.Projecttion.Sql;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrdersDetail, Integer> {
    @Query(value = Sql.ORDERDETAIL,nativeQuery = true)
    List<IOrderDetails> getOrdersDetailById(Integer idOrder);

    @Query(value = Sql.ORDERDETAILUSER,nativeQuery = true)
    List<IOrderDetailsUser> getOrdersDetailUserById(Integer idCustomer);

}
