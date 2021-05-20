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
        if(this.repository.findById(id).isPresent()){
            return this.repository.findById(id).get();
        }
        else{
            throw new NotFoundException();
        }

    }

    @Override
    public void updateProductById(Long id, String name, String description) {

        if(this.repository.findById(id).isPresent()){
            if(name!=null){
                this.repository.findById(id).get().setDescription(description);

            }
            if(description!=null){
                this.repository.findById(id).get().setName(name);


            }
            this.repository.save(this.repository.findById(id).get());
        }
        else{
            throw new NotFoundException();
        }

    }

    @Override
    public void deleteProductById(Long id) {
        if(this.repository.findById(id).isPresent()){
            this.repository.delete(this.repository.findById(id).get());
        }
        else{
            throw new NotFoundException();
        }
    }




}
