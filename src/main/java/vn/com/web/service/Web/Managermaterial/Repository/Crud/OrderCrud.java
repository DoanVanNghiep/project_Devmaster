package vn.com.web.service.Web.Managermaterial.Repository.Crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.web.service.Web.Managermaterial.Domain.Order;
import vn.com.web.service.Web.Managermaterial.Projecttion.Sql;

@Repository
public interface OrderCrud extends CrudRepository<Order, Integer> {
}
