package zerobase.marketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.marketproject.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByMember_Userid(String userid);
}
