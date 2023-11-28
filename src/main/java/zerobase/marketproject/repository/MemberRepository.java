package zerobase.marketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.marketproject.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
