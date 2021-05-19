package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProductService{


    private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product createProduct(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setAmount(request.getAmount());
        newProduct.setDescription(request.getDescription());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);

    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct=this.repository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        else{
            throw new NotFoundException();
        }

    }


}
