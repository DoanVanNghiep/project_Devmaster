package vn.com.web.service.Web.Managermaterial.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;
import vn.com.web.service.Web.Managermaterial.Repository.Crud.CartRepository;
import vn.com.web.service.Web.Managermaterial.Service.CartServiceUser;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceUserImpl implements CartServiceUser {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Cartitem save(Cartitem entity) {
        return cartRepository.save(entity);
    }

    @Override
    public List<Cartitem> saveAll(List<Cartitem> entities) {
        return ( List<Cartitem> )cartRepository.saveAll(entities);
    }

    @Override
    public Optional<Cartitem> findById(Integer integer) {
        return cartRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return cartRepository.existsById(integer);
    }

    @Override
    public List<Cartitem> findAll() {
        return (List<Cartitem>)cartRepository.findAll();
    }

    @Override
    public List<Cartitem> findAllById(List<Integer> integers) {
        return (List<Cartitem>) cartRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return cartRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        cartRepository.deleteById(integer);
    }

    @Override
    public void delete(Cartitem entity) {
        cartRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        cartRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Cartitem> entities) {
        cartRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }
}
