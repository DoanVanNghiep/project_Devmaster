package vn.com.web.service.Web.Managermaterial.Service;

import org.springframework.stereotype.Service;
import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;

import java.util.List;
import java.util.Optional;

@Service
public interface CartServiceUser {
    Cartitem save(Cartitem entity);

    List<Cartitem> saveAll(List<Cartitem> entities);

    Optional<Cartitem> findById(Integer integer);

    boolean existsById(Integer integer);

    List<Cartitem> findAll();

    List<Cartitem> findAllById(List<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(Cartitem entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Cartitem> entities);

    void deleteAll();
}
