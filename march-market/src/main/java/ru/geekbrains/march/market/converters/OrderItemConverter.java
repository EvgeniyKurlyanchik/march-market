package ru.geekbrains.march.market.converters;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.OrderItemDto;
import ru.geekbrains.march.market.entities.OrderItem;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
