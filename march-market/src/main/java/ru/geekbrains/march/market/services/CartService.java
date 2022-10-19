package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.dtos.CartItem;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.dtos.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    public  Cart tempCart;
    private List<CartItem> items;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
       tempCart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {

      Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не найден продукт с таким id=" + productId));
      tempCart.add(product);
    }
    public void clear(){
       tempCart.clear();

    }

    public void remove(Long productId){
        tempCart.remove(productId);

    }

}
