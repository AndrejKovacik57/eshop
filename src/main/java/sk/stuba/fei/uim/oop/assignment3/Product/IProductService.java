package sk.stuba.fei.uim.oop.assignment3.Product;


import java.util.List;


public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(ProductRequest request);
    Product getProductById(Long id);
    Product updateProductById(Long id, String name, String description);
    void deleteProductById(Long id);
    void increaseProductAmountById(Long id, Integer amount);




}
