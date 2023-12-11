package zerobase.marketproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zerobase.marketproject.config.Timestamped;


@Getter
@Setter
@Entity
public class Cart extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int count; // 카트에 담긴 상품 개수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setCount(0);
        cart.setMember(member);

        return cart;
    }
}
