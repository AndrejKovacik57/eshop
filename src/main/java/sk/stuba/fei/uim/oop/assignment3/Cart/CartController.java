package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;


    @PostMapping()
    public ResponseEntity<CartResponse>  addCart(){

        return new ResponseEntity<>(new CartResponse(this.service.createCart()),HttpStatus.CREATED);
    }
    @GetMapping ("/{id}")
    public CartResponse  findCartById(@PathVariable("id") Long id){
        return new CartResponse(this.service.getCartById(id));
    }
    @DeleteMapping("/{id}")
    void deleteCartById(@PathVariable("id") Long id){
        this.service.deleteCartById(id);
    }

    @PostMapping("/{id}/add")
    public CartResponse addProductToCart(@PathVariable("id") Long id, @RequestBody ProductInCart request){
        return new CartResponse(this.service.addProductToCart(id,request));
    }
    @GetMapping ("/{id}/pay")
    public String  payForCart(@PathVariable("id") Long id){
        return this.service.payForCart(id);
    }
}
