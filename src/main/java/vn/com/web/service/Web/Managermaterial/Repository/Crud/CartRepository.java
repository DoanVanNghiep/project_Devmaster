package vn.com.web.service.Web.Managermaterial.Repository.Crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cartitem, Integer> {

    Cartitem findAllById(Integer id);

    @Query(value = "select c.* from Cartitem c where c.username = ?",nativeQuery = true)
    List<Cartitem> findByUsername(String username);

    @Query(value = "select * from Cartitem where id_customer = ?",nativeQuery = true)
    List<Cartitem> getById(Integer id);


}
