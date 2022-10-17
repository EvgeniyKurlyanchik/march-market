package ru.geekbrains.march.market.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private  int price;

    public CartItem(Long productId, String productTitle, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public void changeQuantity(int delta) {
        quantity+= delta;
        price = pricePerProduct*quantity;
    }

}
