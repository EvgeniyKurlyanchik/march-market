package ru.geekbrains.march.market.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.march.market.entities.Product;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
