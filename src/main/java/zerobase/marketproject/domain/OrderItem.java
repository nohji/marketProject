package zerobase.marketproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Member user; // 구매자

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="item_id")
    //private Item item;

    private int itemId; // 주문 상품 번호
    private String itemName; // 주문 상품 이름
    private int itemPrice; // 주문 상품 가격
    private int itemCount; // 주문 상품 수량
    private int itemTotalPrice; // 가격*수량

    // 장바구니 전체 주문
    public static OrderItem createOrderItem(int itemId, Member user, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setUser(user);
        orderItem.setItemName(cartItem.getItem().getItemName());
        orderItem.setItemPrice(cartItem.getItem().getPrice());
        orderItem.setItemCount(cartItem.getCount());
        orderItem.setItemTotalPrice(cartItem.getItem().getPrice()*cartItem.getCount());
        return orderItem;
    }
}
