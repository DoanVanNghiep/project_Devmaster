package vn.com.web.service.Web.Managermaterial.Dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link vn.com.web.service.Web.Managermaterial.Domain.Cartitem}
 */
@Value
public class CartitemDto implements Serializable {
    Integer id;
    String name;
    Double price;
    Integer quantity;
    String image;
    String username;
    Integer idCustomer;
    Integer idProduct;
}