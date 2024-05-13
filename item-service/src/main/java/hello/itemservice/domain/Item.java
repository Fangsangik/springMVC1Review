package hello.itemservice.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Item {
    private Long id;
    private String itemName;
    private int price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, int price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
