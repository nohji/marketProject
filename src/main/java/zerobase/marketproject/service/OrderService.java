package zerobase.marketproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.marketproject.domain.CartItem;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.domain.Order;
import zerobase.marketproject.domain.OrderItem;
import zerobase.marketproject.repository.OrderItemRepository;
import zerobase.marketproject.repository.OrderRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final MemberService memberService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    // 장바구니상품주문
    public OrderItem addCartOrder(int itemId, String userId, CartItem cartItem) {

        Member user = memberService.findMemeber(userId);

        OrderItem orderItem = OrderItem.createOrderItem(itemId, user, cartItem);

        orderItemRepository.save(orderItem);

        return orderItem;
    }

    // 주문하면 Order 만들기
    public void addOrder(Member user, List<OrderItem> orderItemList) {

        Order userOrder = Order.createOrder(user, orderItemList);

        orderRepository.save(userOrder);
    }

}
