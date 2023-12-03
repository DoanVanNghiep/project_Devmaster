package vn.com.web.service.Web.Managermaterial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.web.service.Web.Managermaterial.Domain.Cartitem;
import vn.com.web.service.Web.Managermaterial.Domain.Customer;
import vn.com.web.service.Web.Managermaterial.Domain.Product;
import vn.com.web.service.Web.Managermaterial.Repository.Crud.CartRepository;
import vn.com.web.service.Web.Managermaterial.Repository.CustomerRepository;
import vn.com.web.service.Web.Managermaterial.Repository.ProductRepository;
import vn.com.web.service.Web.Managermaterial.Service.CartService;
import vn.com.web.service.Web.Managermaterial.Service.CartServiceUser;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.List;

@Controller
@RequestMapping("shopping-cart")
public class CartController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartService cartService;


    /*
    *  CHƯA ĐĂNG NHẬP
    * */
    @GetMapping("viewCartNoUser")
    public String showCart(Model model, HttpSession session){
        model.addAttribute("cartItemNoUN",cartService.getAll());
        model.addAttribute("countNoUN",cartService.getCount());
        model.addAttribute("amountNoUN",cartService.getAmount());

        // lưu vào session
        session.setAttribute("cartItemNoUN",cartService.getAll());
        session.setAttribute("amountNoUN",cartService.getAmount());
        session.setAttribute("countNoUN",cartService.getCount());
        return "display/cart";
    }

        // thêm vào giỏ hàng

    @GetMapping("/addNoUser/{id}")
    public String addCart(@PathVariable("id") Integer id){
        Product product = productRepository.findAllById(id);
        if (product != null){
            Cartitem cartitem = new Cartitem();
            cartitem.setQuantity(1);
            cartitem.setIdProduct(product.getId());
            cartitem.setName(product.getName());
            cartitem.setImage(product.getImage());
            cartitem.setPrice(product.getPrice());
            cartService.add(cartitem);
        }
        return "redirect:/shopping-cart/viewCartNoUser";
    }
        // clear
    @GetMapping("clear")
    public String clearCart(){
        cartService.clear();
        return "redirect:/shopping-cart/viewCartNoUser";
    }

        // delete
    @GetMapping("/delNoUser/{id}")
    public String delNoUser(@PathVariable("id") Integer id){
        cartService.delete(id);
        return "redirect:/shopping-cart/viewCartNoUser";
    }
        // update quantity

    @PostMapping("/updateNoUser")
    public String UpdateNoUser(@RequestParam("id")Integer id,
                               @RequestParam("quantity") Integer quantity){
        cartService.edit(id,quantity);
        return "redirect:/shopping-cart/viewCartNoUser";
    }

    /*
    * ĐÃ ĐĂNG NHẬP
    * */

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/viewCartUser/{username}")
    public String viewCartUser(@PathVariable("username") String username,
                               HttpSession session, Model model){

        List<Cartitem> item = cartRepository.findByUsername(username);
        double totalPrice = 0;
        for (Cartitem cartitem : item){
            totalPrice = totalPrice + (cartitem.getPrice()*cartitem.getQuantity());
        }
        model.addAttribute("totalPriceUser",totalPrice);
        model.addAttribute("cartItemUser",item);
        //model.addAttribute("countItemUser",cartServiceUser.count());

        session.setAttribute("cartItemUser",item);
        session.setAttribute("totalPriceUser",totalPrice);
        session.setAttribute("countItemUser",cartServiceUser.count());

        return "display/cart";
    }

        // thêm vào giỏ hàng có trong Database

    @Autowired
    CartServiceUser cartServiceUser;
    @GetMapping("/addYesUser/{id}/{username}")
    public String addYesUser(@PathVariable("id")Integer id,
                             @PathVariable("username")String username){

        Product product = productRepository.findAllById(id);
        Customer customer = customerRepository.findByUsername(username);

        if (product != null){
            Cartitem cartitem = new Cartitem();
            cartitem.setName(product.getName());
            cartitem.setQuantity(1);
            cartitem.setImage(product.getImage());
            cartitem.setPrice(product.getPrice());
            cartitem.setIdProduct(product.getId());
            cartitem.setIdCustomer(customer.getId());
            cartitem.setUsername(customer.getUsername());
            cartService.add(cartitem);
            cartServiceUser.save(cartitem);
        }

        return "redirect:/shopping-cart/viewCartUser/{username}";

    }

        // delete
    @GetMapping("/delUser/{id}/{username}")
    public String delUser(@PathVariable("id") Integer id,
                          @PathVariable("username") String username){

        cartServiceUser.deleteById(id);

        return "redirect:/shopping-cart/viewCartUser/{username}";
    }


        // update quantity
    @GetMapping("/updateUser/{username}")
    public String updateUser(@RequestParam(name = "id") Integer id,
                             @RequestParam(name = "quantity") Integer quantity,
                             @RequestParam(name = "username")String username){

        Cartitem cartitem = cartRepository.findAllById(id);
        cartitem.setQuantity(quantity);
        cartRepository.save(cartitem);

        return "redirect:/shopping-cart/viewCartUser/{username}";

    }



}
