package zerobase.marketproject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerobase.marketproject.repository.MemberRepository;

@SpringBootTest
@Transactional
public class JpaMemberRepositoryTest {
    @Autowired
    MemberRepository jpaMemberRepository;

    @Test
    void insertMemberTest() {
        //given

        //when
        //then

    }
}
