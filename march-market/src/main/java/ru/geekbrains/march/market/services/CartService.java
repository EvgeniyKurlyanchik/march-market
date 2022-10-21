package ru.geekbrains.march.market.services;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.dtos.Cart;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    @Value("${other.cache.cart}")
    private String CACHE_CART;
    private Cart cart;

    private final CacheManager cacheManager;


    //    public void init() {
//        tempCart = new Cart();
//       tempCart.setItems(new ArrayList<>());
//    }
        @Cacheable(value = "${other.cache.cart}", key = "#cartName")
        public Cart getCurrentCart(String cartName) {
            cart = cacheManager.getCache(CACHE_CART).get(cartName, Cart.class);
            if (!Optional.ofNullable(cart).isPresent()) {
                cart = new Cart(cartName,cacheManager);
                cacheManager.getCache(CACHE_CART).put(cartName,cart);
            }
            return cart;
        }
        @CachePut(value = "${other.cache.cart}", key = "#cartName")
        public Cart addProductByIdToCart(Long id, String cartName) {
            Cart cart = getCurrentCart(cartName);
            if (!getCurrentCart(cartName).addProductCount(id)) {
                Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт"));
                cart.addProduct(product);
            }
            return cart;
        }

//    public void addProductByIdToCart(Long id, String cartName){
//        if(!getCurrentCart(cartName).addProductCount(id)){
//            Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт"));
//            Cart cart = getCurrentCart(cartName);
//            cart.addProduct(product);
//            cacheManager.getCache("Cart").put(cartName, cart);
//        }
//    }
        @CachePut(value = "${other.cache.cart}", key = "#cartName")
        public void clear(String cartName) {
            Cart cart = getCurrentCart(cartName);
            cart.clear();
        }


}
//    public void clear(String cartName){
//        Cart cart = getCurrentCart(cartName);
//        cart.clear();
//        cacheManager.getCache(CACHE_CART).put(cartName, cart);
//    }

