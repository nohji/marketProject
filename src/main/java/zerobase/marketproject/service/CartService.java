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

import java.util.ArrayList;
import java.util.List;
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

    // 유저 id로 해당 유저의 장바구니 찾기
    public Cart findUserCart(String userId) {

        return cartRepository.findByMember_Userid(userId);

    }

    // 카트 상품 리스트 중 해당하는 유저가 담은 상품만 반환
    // 유저의 카트 id와 카트상품의 카트 id가 같아야 함
    public List<CartItem> allUserCartView(Cart userCart) {

        // 유저의 카트 id를 꺼냄
        int userCartId = userCart.getId();

        // id에 해당하는 유저가 담은 상품들 넣어둘 곳
        List<CartItem> UserCartItems = new ArrayList<>();

        // 유저 상관 없이 카트에 있는 상품 모두 불러오기
        List<CartItem> CartItems = cartItemRepository.findAll();

        for(CartItem cartItem : CartItems) {
            if(cartItem.getCart().getId() == userCartId) {
                UserCartItems.add(cartItem);
            }
        }

        return UserCartItems;
    }

    // 장바구니 아이템 전체 삭제 -> 매개변수는 유저 id
    public void allCartItemDelete(String id) {

        // 전체 cartItem 찾기
        List<CartItem> cartItems = cartItemRepository.findAll();

        // 반복문을 이용하여 해당하는 User 의 CartItem 만 찾아서 삭제
        for(CartItem cartItem : cartItems){

            if(cartItem.getCart().getMember().getUserid().equals(id)) {

                cartItem.getCart().setCount(0);

                cartItemRepository.deleteById(cartItem.getId());
            }
        }
    }

}
