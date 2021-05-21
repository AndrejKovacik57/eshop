package sk.stuba.fei.uim.oop.assignment3.Cart;



public interface ICartService {
    Cart createCart();
    Cart getCartById(Long id);
    void deleteCartById(Long id);
    Cart addProductToCart(Long cartId, ProductInCart request);
    String payForCart(Long cartId);
}
