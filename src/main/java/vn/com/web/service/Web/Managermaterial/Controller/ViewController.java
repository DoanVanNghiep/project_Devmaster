package vn.com.web.service.Web.Managermaterial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.web.service.Web.Managermaterial.Repository.ProductRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ViewController {

    @Autowired
    ProductRepository productRepository;
    @GetMapping("")
    public String showView(Model model, HttpSession session){
        model.addAttribute("newProduct",productRepository.newAllProduct());
        model.addAttribute("productSell",productRepository.productSell());

        session.setAttribute("productSell",productRepository.productSell());

        // thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        model.addAttribute("currentTime", formattedTime);

        // Tách giờ, phút và giây
        String[] timeParts = formattedTime.split(":");
        model.addAttribute("hour", timeParts[0]);
        model.addAttribute("minute", timeParts[1]);
        model.addAttribute("second", timeParts[2]);

        return "layout/web";
    }


}
