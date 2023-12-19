package zerobase.marketproject.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import zerobase.marketproject.domain.*;
import zerobase.marketproject.service.CartService;
import zerobase.marketproject.service.MemberService;
import zerobase.marketproject.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private MemberService memberService;
    private CartService cartService;
    private OrderService orderService;

    // 장바구니 상품 전체 주문
    @Transactional
    @PostMapping("/member/cart/checkout/{id}")
    public String cartCheckout(@PathVariable("id") String id) {
                               //@AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 주문하는 id가 같아야 함
       // if(principalDetails.getUser().getId() == id) {

            Member user = memberService.findMemeber(id);
        System.out.println(user);
            // 유저 카트 찾기
            Cart userCart = cartService.findUserCart(user.getUserid());

            // 유저 카트 안에 있는 상품들
            List<CartItem> userCartItems = cartService.allUserCartView(userCart);

            // 최종 결제 금액
            int totalPrice = 0;
            for (CartItem cartItem : userCartItems) {
                // 장바구니 안에 있는 상품의 재고가 없거나 재고보다 많이 주문할 경우
                if (cartItem.getItem().getStock() == 0 || cartItem.getItem().getStock() < cartItem.getCount()) {
                    return "redirect:/main";
                }
                totalPrice += cartItem.getCount() * cartItem.getItem().getPrice();
            }


            List<OrderItem> orderItemList = new ArrayList<>();

            for (CartItem cartItem : userCartItems) {

                // 재고 감소
                cartItem.getItem().setStock(cartItem.getItem().getStock() - cartItem.getCount());

                // order, orderItem 에 담기
                OrderItem orderItem = orderService.addCartOrder(cartItem.getItem().getId(), user.getUserid(), cartItem);

                orderItemList.add(orderItem);
            }

            orderService.addOrder(user, orderItemList);

            // 장바구니 상품 모두 삭제
            cartService.allCartItemDelete(id);

            return "주문완료";
//        } else {
//            return "redirect:/main";
//        }
    }
}
