package ru.geekbrains.march.market.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private  int price;


//    public CartItem(Long id, String title, int quantity, BigDecimal price, BigDecimal price1) {
//    }

    public CartItem(Long id, String title, int quantity, int price, int price1) {
    }


//    public void incrementQuantity() {
//        quantity++;
//        price = price.add(pricePerProduct);
//    }

}
