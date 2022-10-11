package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.march.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor

public class Cart {
    private  List<CartItem> items;
    private int totalPrice;
    public Cart(){
        this.items = new ArrayList<>();
    }
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void add(Product product) {
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();

}


//        public void add(Product p) {
//            for (CartItem item : items) {
//                if (item.getProductId().equals(p.getId())) {
//                    item.incrementQuantity();
//                    recalculate();
//                    return;
//                }

}
