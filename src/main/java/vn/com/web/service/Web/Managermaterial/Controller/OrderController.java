package vn.com.web.service.Web.Managermaterial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.web.service.Web.Managermaterial.Domain.*;
import vn.com.web.service.Web.Managermaterial.Projecttion.IOrderUser;
import vn.com.web.service.Web.Managermaterial.Projecttion.IProduct;
import vn.com.web.service.Web.Managermaterial.Repository.*;
import vn.com.web.service.Web.Managermaterial.Repository.Crud.OrderCrud;
import vn.com.web.service.Web.Managermaterial.Service.CartServiceUser;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

    @Autowired
    TransportMethodRepository transportMethodRepository;

    @Autowired
    OrderTransportRepository orderTransportRepository;

    @Autowired
    CartServiceUser cartServiceUser;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/saveOrder/{username}")
    public String addOrder(Model model,
                           HttpSession session,
                           @PathVariable(name = "username") String username,
                           @RequestParam(required = false, name = "IDPayment") Integer IDPayment,
                           @RequestParam(required = false, name = "IDTransport") Integer IDTransport){

        Customer customer = customerRepository.findByUsername(username);

        Double totalMoney = (Double) session.getAttribute("amount");
        // đưa vào order
        Order order = new Order();
        String IDOrder = UUID.randomUUID().toString().substring(0,10);
        order.setOrdersDate(new Date().toInstant());
        order.setIdorders(IDOrder);
        order.setNotes("Có");
        order.setIdcustomer(customer);
        order.setTotalMoney(totalMoney);
        order.setNameReciver(customer.getName());
        order.setAddress(customer.getAddress());
        order.setPhone(customer.getPhone());
        order.setTrangthai(1);
        orderRepository.save(order);

        // đưa vào order - details
        Collection<Cartitem> cart = (Collection<Cartitem>) session.getAttribute("cartItemUser");
        List<OrdersDetail> ordersDetails = new ArrayList<>();
        for (Cartitem cartDto : cart){
            OrdersDetail ordersDetail  = new OrdersDetail();
            ordersDetail.setIdord(order);
            ordersDetail.setQty(cartDto.getQuantity());
            ordersDetail.setIdproduct(cartDto.getIdProduct());
            ordersDetail.setPrice(cartDto.getPrice());
            //save
            orderDetailsRepository.save(ordersDetail);
            ordersDetails.add(ordersDetail);

            // cập nhật lại số lượng sản phẩm
            int idProduct = cartDto.getIdProduct();
            Product product = productRepository.findAllById(idProduct);
            product.setQuatity(product.getQuatity() - cartDto.getQuantity());

            // số lần đã mua của sản phaamr

            product.setPurchar(product.getPurchar() + cartDto.getQuantity());
            // nếu hết hàng
            if (product.getQuatity() == 0){
                product.setIsactive((byte) 0);
            }
            productRepository.save(product);
        }


        // đưa vào order - payment
        OrdersPayment ordersPayment = new OrdersPayment();
        PaymentMethod paymentMethod = paymentMethodRepository.findAllById(IDPayment);
        ordersPayment.setIdord(order);
        ordersPayment.setIdpayment(paymentMethod);
        ordersPayment.setTotal(0);
        // save
        orderPaymentRepository.save(ordersPayment);


        // đưa vào order - transport
        OrdersTransport ordersTransport = new OrdersTransport();
        TransportMethod transportMethod = transportMethodRepository.findAllById(IDTransport);
        ordersTransport.setIdord(order);
        ordersTransport.setIdtransport(transportMethod);
        ordersTransport.setTotal(transportMethod.getPrice());
        ordersTransport.setNotes(1);
        // save
        orderTransportRepository.save(ordersTransport);


        // xóa các giá trị giỏ hàng trong session
        session.removeAttribute("cartItemUser");
        session.removeAttribute("totalPriceUser");
        session.removeAttribute("countItemUser");

        // xóa các cart trong giỏ hàng
        cartServiceUser.deleteAll();
        return "display/success";
    }
    // view đơn đã đặt hàng

    @GetMapping("orderViewUser/{idCustomer}")
    public String showOrder(Model model, @PathVariable(name = "idCustomer") Integer idCustomer){
        List<IProduct> product= productRepository.getProductTC(idCustomer);
        model.addAttribute("trangChu",product);
        return "display/orderViewUser";
    }

    @GetMapping("productOrder/{idProduct}/{idCustomer}")
    public String showProductOrder(@PathVariable(name = "idProduct") Integer idProduct,
                                   @PathVariable(name = "idCustomer")Integer idCustomer,
                                   Model model){
        List<IOrderUser> list = productRepository.getOrUser(idProduct,idCustomer);
        model.addAttribute("order_chiTiet",list);
        return "display/orderDetailsView";
    }

    // hủy đơn hàng
    @GetMapping("/huyDon/{idOrder}/{idCustomer}")
    public String huyDon(Model model, @PathVariable(name = "idOrder")Integer idOrder,
                         @PathVariable(name = "idCustomer") Integer idCustomer){
        Order order = orderRepository.findAllById(idOrder);
        order.setTrangthai(4);
        orderRepository.save(order);
        return "redirect:/order/orderViewUser/{idCustomer}";
    }
    // đặt lại đơn hàng
    @GetMapping("/datLai/{idOrder}/{idCustomer}")
    public String datLai(Model model,@PathVariable(name = "idOrder")Integer idOrder,
                         @PathVariable(name = "idCustomer") Integer idCustomer){
        Order order = orderRepository.findAllById(idOrder);
        order.setTrangthai(1);
        orderRepository.save(order);
        return "redirect:/order/orderViewUser/{idCustomer}";
    }

    // đã nhận được hàng
    @GetMapping("/nhanHang/{idOrder}/{idCustomer}")
    public String nhanHang(Model model,@PathVariable(name = "idOrder")Integer idOrder,
                           @PathVariable(name = "idCustomer") Integer idCustomer){
        Order order = orderRepository.findAllById(idOrder);
        order.setTrangthai(5);
        orderRepository.save(order);
        return "redirect:/order/orderViewUser/{idCustomer}";
    }
}
