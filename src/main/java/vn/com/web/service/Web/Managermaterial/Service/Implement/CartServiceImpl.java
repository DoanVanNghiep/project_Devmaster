package vn.com.web.service.Web.Managermaterial.Service.Implement;

import org.springframework.stereotype.Service;
import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;
import vn.com.web.service.Web.Managermaterial.Service.CartService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    Map<Integer, Cartitem> maps = new HashMap<>();
    @Override
    public void add(Cartitem cartItem) {
        Cartitem cartDto = maps.get(cartItem.getIdProduct());
        if (cartDto == null){
            maps.put(cartItem.getIdProduct(), cartItem);
        }else
            cartDto.setQuantity(cartDto.getQuantity());
    }

    @Override
    public Cartitem edit(int proID, int qTy) {
        Cartitem cartDto = maps.get(proID);
        cartDto.setQuantity(qTy);
        return cartDto;
    }

    @Override
    public void delete(int id) {
        maps.remove(id);
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public Double TotalPrice() {
        return maps.values().stream()
                .mapToDouble(item -> item.getQuantity()* item.getPrice())
                .sum();
    }
    @Override
    public Collection<Cartitem> getAll() {
        return maps.values();
    }
    @Override
    public void clear() {
        maps.clear();
    }
    @Override
    public double getAmount(){
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
