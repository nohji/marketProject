package zerobase.marketproject.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.marketproject.domain.Cart;
import zerobase.marketproject.domain.CartItem;
import zerobase.marketproject.domain.Item;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.repository.CartItemRepository;
import zerobase.marketproject.repository.CartRepository;
import zerobase.marketproject.repository.ItemRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    public Boolean addCart(Member member, Item newItem, int count){

        //멤버 id로 카트 유무 파악
        Cart cart = cartRepository.findByMember_Userid(member.getUserid());

        // 장바구니가 존재하지 않는다면
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // item 값이 존재하는지 체크
        Item item = itemRepository.findItemById(newItem.getId());
        if (item == null) {
            return false;
        }

        CartItem cartItem = cartItemRepository.findByCart_IdAndItem_Id(cart.getId(), item.getId());

        // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, count);
            cartItemRepository.save(cartItem);
        }

        // 상품이 장바구니에 이미 존재한다면 수량만 증가
        else {
            CartItem update = cartItem;
            update.setCart(cartItem.getCart());
            update.setItem(cartItem.getItem());
            update.addCount(count);
            update.setCount(update.getCount());
            cartItemRepository.save(update);
        }

        // 카트 상품 총 개수 증가
        cart.setCount(cart.getCount()+count);

        return true;
    }
}
