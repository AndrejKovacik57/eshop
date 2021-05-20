package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CartRequest {
    private Long id;
    private List<ProductInCart> shoppingList = new ArrayList();
    private boolean payed;

}
