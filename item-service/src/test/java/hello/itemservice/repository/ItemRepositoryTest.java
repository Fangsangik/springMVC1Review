package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    public ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    public void save(){
        Item item = new Item("사과", 1000, 10);
        Item save = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(save);
    }

    @Test
    public void findAll(){
        Item itemA = new Item("A", 1000, 10);
        Item itemB = new Item("B", 10000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> rst = itemRepository.findAll();

        assertThat(rst.size()).isEqualTo(2);
        assertThat(rst).contains(itemA);
    }

    @Test
    public void updateItem(){
        Item item = new Item("A", 1000, 20);

        Item save = itemRepository.save(item);
        Long id = save.getId();

        Item update = new Item("B", 2000, 30);
        itemRepository.update(id, update);

        Item findById = itemRepository.findById(id);

        assertThat(findById.getItemName()).isEqualTo(update.getItemName());
        assertThat(findById.getPrice()).isEqualTo(update.getPrice());
        assertThat(findById.getQuantity()).isEqualTo(update.getQuantity());

    }

}