package zerobase.marketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.marketproject.domain.Order;
import zerobase.marketproject.domain.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
