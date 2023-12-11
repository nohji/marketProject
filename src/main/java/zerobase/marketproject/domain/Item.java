package zerobase.marketproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import zerobase.marketproject.config.Timestamped;
import zerobase.marketproject.dto.ItemDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String itemStatus;


    public Item(ItemDto itemDto) {
        this.itemStatus = itemDto.getItemStatus();
        this.itemName = itemDto.getItemName();
        this.price = itemDto.getPrice();
        this.stock = itemDto.getStock();
    }
}
