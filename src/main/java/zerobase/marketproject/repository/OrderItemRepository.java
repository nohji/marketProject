package zerobase.marketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.marketproject.domain.Member;
import zerobase.marketproject.domain.OrderItem;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {
}
