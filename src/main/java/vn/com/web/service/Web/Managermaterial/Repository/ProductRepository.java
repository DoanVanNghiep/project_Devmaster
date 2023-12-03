package vn.com.web.service.Web.Managermaterial.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import vn.com.web.service.Web.Managermaterial.Domain.Category;
import vn.com.web.service.Web.Managermaterial.Domain.Product;
import vn.com.web.service.Web.Managermaterial.Projecttion.IOrderUser;
import vn.com.web.service.Web.Managermaterial.Projecttion.IProduct;
import vn.com.web.service.Web.Managermaterial.Projecttion.Sql;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findAllById(Integer id);
    Product findAllByName(String name);
    @Query(value = "select * from product where IDCATEGORY = 16",nativeQuery = true)
    List<Product> findByLapTop();

    @Query(value = "select * from product where IDCATEGORY = 17",nativeQuery = true)
    List<Product> findBySmartPhone();

    @Query(value = "select * from product where IDCATEGORY = 18",nativeQuery = true)
    List<Product> findByAccessories();

    @Query(value = "select * from product where IDCATEGORY = 19",nativeQuery = true)
    List<Product> findByCameras();

    @Query(value = "select * from product where IDCATEGORY > 19",nativeQuery = true)
    List<Product> findByRemaining();


    @Query(value = "SELECT * FROM product WHERE CREATED_DATE <= CURRENT_DATE ORDER BY CREATED_DATE DESC LIMIT 5",nativeQuery = true)
    List<Product> newAllProduct();

    @Query(value = "SELECT * FROM product ORDER BY times_purchased DESC LIMIT 5",nativeQuery = true)
    List<Product> productSell();

    @Query(value = "SELECT * FROM product ORDER BY times_purchased DESC LIMIT 3",nativeQuery = true)
    List<Product> productSellForStore();

    @Query(value = Sql.PRODUCT_TRANGCHU,nativeQuery = true)
    List<IProduct> getProductTC(Integer idCustomer);
    @Query(value = Sql.XACNHAN_ADMIN,nativeQuery = true)
    List<IProduct> xacNhanProduct();

    @Query(value = Sql.DONHANG2,nativeQuery = true)
    List<IOrderUser> getOrUser(Integer idProduct, Integer idCustomer);
}
