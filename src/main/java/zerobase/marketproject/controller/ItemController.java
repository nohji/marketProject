package zerobase.marketproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.marketproject.domain.Item;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.dto.ItemDto;
import zerobase.marketproject.repository.ItemRepository;
import zerobase.marketproject.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/admin/items")
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    @PostMapping("/admin/item/new")
    public Item createItem(@RequestBody ItemDto itemDto) {
        Item item = new Item(itemDto);
        itemRepository.save(item);

        return item;
    }

}
