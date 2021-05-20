package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;


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

    @Override
    public Cart getCartById(Long id ) {
        if(this.repository.findById(id).isPresent()){
            return this.repository.findById(id).get();
        }
        else{
            throw new NotFoundException();
        }
    }

}
