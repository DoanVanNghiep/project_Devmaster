package vn.com.web.service.Web.Managermaterial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.web.service.Web.Managermaterial.Domain.Customer;
import vn.com.web.service.Web.Managermaterial.Repository.CustomerRepository;
import vn.com.web.service.Web.Managermaterial.Repository.ProductRepository;
import vn.com.web.service.Web.Managermaterial.Service.ReadData.ParamService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    ParamService paramService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("index")
    public String index(){
        return "layout/web";
    }
    @GetMapping("login")
    public String viewLogin(){
        return "account/login";
    }

    @GetMapping("profile")
    public String viewProfile(){
        return "account/profile";
    }

    // login
    @PostMapping("check-account")
    public String checkAccount(Model model,
                               @RequestParam("username") String username,
                               HttpSession session){
        String password = paramService.getString("password","");
        try {
            Customer customer = customerRepository.findByUsername(username);
            if (!customer.getPassword().equals(password)) model.addAttribute("message","Sai mật khẩu!");
            if (customer.getId() == 19){
                model.addAttribute("customer",customerRepository.findByUsername(username));
                session.setAttribute("customer",customer);
                return "/admin/indexAdmin";
            }
            else {
                model.addAttribute("customer",customerRepository.findByUsername(username));
                session.setAttribute("customer",customer);
                return "redirect:/";
            }
        }catch (Exception e){
            model.addAttribute("message", "....");
        }
        return "redirect:/account/login";
    }

    // register

    @GetMapping("/register")
    public String viewRegister(Model model){
        model.addAttribute("Cus",new Customer());
        return "account/register";
    }

    @PostMapping("/SaveOrUpdate")
    public String saveOrUpdate(@Validated @ModelAttribute("Cus") Customer customer,
                               BindingResult result, Model model) throws IOException {
        if (result.hasErrors()){
            model.addAttribute("error","Please re-enter");
            return "account/register";
        }
        customer.setIsactive((byte)1);
        customer.setCreatedDate(new Date().toInstant());
        customerRepository.save(customer);
        model.addAttribute("Cus", new Customer());
        return "account/register";
    }
}
