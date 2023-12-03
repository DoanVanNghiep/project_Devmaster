package vn.com.web.service.Web.Managermaterial.Service;

import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CartService {

    void add(Cartitem cartItem);

    Cartitem edit(int proID, int qTy);

    void delete(int id);

    int getCount();

    Double TotalPrice();

    Collection<Cartitem> getAll();

    void clear();

    double getAmount();
}
