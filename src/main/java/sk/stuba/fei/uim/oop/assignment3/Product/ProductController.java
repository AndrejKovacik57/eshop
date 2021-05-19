package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts(){

        return this.service.getAllProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
    }
    @PostMapping()
    public ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.createProduct(request));

    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id){
        return  new ProductResponse(this.service.getProductById(id));
    }
}
