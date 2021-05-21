package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.Product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.Product.Product;


@Service
public class CartService implements  ICartService{
    private CartRepository repository;
    @Autowired
    public CartService(CartRepository repository){
        this.repository=repository;
    }

    @Autowired
    private IProductService productService;

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

    @Override
    public void deleteCartById(Long id) {
        if(this.repository.findById(id).isPresent()){
             this.repository.delete(this.repository.findById(id).get());
        }
        else{
            throw new NotFoundException();
        }
    }

    @Override
    public Cart addProductToCart(Long cartId, ProductInCart request) {
        //ak product neexistuje hodi 404
        Product product=this.productService.getProductById(request.getProductId());
        Cart cart;

        //ak kosik neexistuje hodi exception
        if(this.repository.findById(cartId).isPresent()){
             cart= this.repository.findById(cartId).get();
        }
        else{
            throw new NotFoundException();
        }
        //ak je kosik zaplateny alebo ak amount na sklade je mensie nez potrebne
        if(cart.isPayed() || product.getAmount()< request.getAmount()){
            throw  new BadRequestException();
        }

        //ak sa nachadza produkt v kosiku
        for (ProductInCart productInCart : cart.getShoppingList()) {
            if (productInCart.getProductId().equals(request.getProductId())) {
                productInCart.setAmount(productInCart.getAmount() + request.getAmount());
                product.setAmount(product.getAmount()-request.getAmount());
                return cart;
            }
        }
        //ak sa nenachadza produkt v kosiku
        product.setAmount(product.getAmount()-request.getAmount());
        cart.getShoppingList().add(request);
        return cart;

    }

    @Override
    public String payForCart(Long cartId) {
        Double sum=0.0;

        if(this.repository.findById(cartId).isPresent()){
            Cart cart=this.repository.findById(cartId).get();
            if(!cart.isPayed()){
                cart.setPayed(true);
                for (ProductInCart productInCart : cart.getShoppingList()) {
                    Product product=this.productService.getProductById(productInCart.getProductId());
                    sum+=product.getPrice()*productInCart.getAmount();
                }
            }
            else {
                throw new BadRequestException();
            }

        }

        else{
            throw new NotFoundException();
        }
        return sum+"";
    }


}
