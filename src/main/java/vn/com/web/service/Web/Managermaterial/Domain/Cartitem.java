package vn.com.web.service.Web.Managermaterial.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cartitem", schema = "manager-material")
public class Cartitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image", length = 250)
    private String image;

    @Column(name = "username", length = 250)
    private String username;

    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "id_product")
    private Integer idProduct;

}