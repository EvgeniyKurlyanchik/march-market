package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.dtos.Cart;
import ru.geekbrains.march.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
   private final CartService cartService;
    @GetMapping("/add/{id}")
public Cart addToCart(@PathVariable Long id) {
        cartService.add(id);
    return getCurrentCart(id);
    }
    @GetMapping("/clear")
    public  void clearCart() {
        cartService.clear();
    }
    @GetMapping("/remove/{id}")
    public  void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping
    public Cart getCurrentCart(Long id) {
        return cartService.getCurrentCart();
    }
@GetMapping("/plus/{id}")
    public Cart plusCart(@PathVariable Long id) {
        cartService.add(id);
        return getCurrentCart(id);
    }

}
