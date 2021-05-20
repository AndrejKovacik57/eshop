package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import java.util.Map;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.createProduct(request));

    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id){
        return  new ProductResponse(this.service.getProductById(id));
    }
    @PutMapping("/{id}")
    public ProductResponse updateProductById(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        return new ProductResponse(this.service.updateProductById(id,request.getName(),request.getDescription())) ;

    }
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable("id") Long id){
        this.service.deleteProductById(id);
    }
    @GetMapping(value = "/{id}/amount")
    public Map<String, Integer> getProductAmountById(@PathVariable("id") Long id){
        return Collections.singletonMap("amount",service.getProductById(id).getAmount());
    }
    @PostMapping(value = "/{id}/amount")
    public Map<String, Integer> increaseProductAmountById(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        this.service.increaseProductAmountById(id,request.getAmount());
        return Collections.singletonMap("amount",service.getProductById(id).getAmount());
    }
}
