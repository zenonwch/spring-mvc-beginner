package my.spring.project.springmvc.domain.xml;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.spring.project.springmvc.domain.Product;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "products")
public class Products {
    private List<Product> products;

    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }
}