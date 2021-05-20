package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService implements  ICartService{
    private CartRepository repository;
    @Autowired
    public CartService(CartRepository repository){
        this.repository=repository;
    }

    @Override
    public Cart createCart() {
        return this.repository.save(new Cart());
    }



}
