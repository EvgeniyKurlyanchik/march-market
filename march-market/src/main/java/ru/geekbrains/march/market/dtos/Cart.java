package ru.geekbrains.march.market.dtos;
import lombok.Data;

import org.springframework.cache.CacheManager;
import ru.geekbrains.march.market.entities.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
@Data
public class Cart {

    private List<OrderItemDto> items;
    private  int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String cartName, CacheManager manager){
        this.items = new ArrayList<>();
        this.totalPrice = 0;
    }

    public boolean addProductCount(Long id){
        for(OrderItemDto o: items){
            if(o.getProductId().equals(id)){
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product){
        if(addProductCount(product.getId())){
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    private void recalculate(){
        totalPrice = 0;
        for(OrderItemDto o: items){
            totalPrice += o.getPrice();
        }
    }

    public void removeProduct(Long id){
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    public void decreaseProduct(Long id){
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()){
            OrderItemDto o = iter.next();
            if(o.getProductId().equals(id)){
                o.changeQuantity(-1);
                if(o.getQuantity() <= 0){
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }
}

//@Data
//public class Cart {
//    private  List<CartItem> items;
//    private int totalPrice;
//    public Cart(String cartName, CacheManager manager){
//
//        this.items = new ArrayList<>();
//        this.totalPrice = 0;
//    }
//
//
//
//    public List<CartItem> getItems() {
//        return Collections.unmodifiableList(items);
//    }
//
//        public void add (Product product) {
//            for (CartItem item:items) {
//                if (product.getId().equals(item.getProductId())) {
//                    item.changeQuantity(1);
//                    recalculate();
//                    return;
//                }
//            }
//            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
//            recalculate();
//        }
//public void remove (Long productId){
//  if (items.removeIf(item-> item.getProductId().equals(productId))){
//      recalculate();
//  }
//}
//    public void clear() {
//        items.clear();
//        totalPrice = 0;
//    }
//    private void recalculate() {
//        totalPrice = 0;
//        for (CartItem item:items) {
//            totalPrice += item.getPrice();
//        }
//    }
//    public boolean addProductCount(Long id){
//        for(CartItem o: items){
//            if(o.getProductId().equals(id)){
//                o.changeQuantity(1);
//                recalculate();
//                return true;
//            }
//        }
//        return false;
//    }
//
//}