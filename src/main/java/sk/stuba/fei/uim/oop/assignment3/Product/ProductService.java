package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{


    private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
        Product p1 = new Product();
        p1.setUnit("procesor");
        this.repository.save(p1);
        Product p2 = new Product();
        p2.setUnit("graficka karta");
        this.repository.save(p2);
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setAmount(request.getAmount());
        newProduct.setDescription(request.getDescription());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);

    }
}
