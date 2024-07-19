package study.datajpa.repository;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

@SpringBootTest
@Transactional
//@Rollback(false) 테스트 메서드가 끝날 때마다 트랜잭션을 롤백하는데 , 롤백을 막고 변경사항을 유지하고 싶을 때 사용
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member); //member을 영속성 컨텍스트에 저장

        Member findMember = memberJpaRepository.find(savedMember.getId());
        //jpa는 이미 영속성 컨텍스트에 savedMember 객체가 존재하기 때문에
        //새롭게 데이터 베이스 조회를 하지 않고 영속성 컨텍스트에 있는 동일한 객체를 반환한다.
        //jpa의 영속성 컨텍스트는 엔티티 객체의 1차 캐시 역할을 하기 때문에
        //엔티티 객체가 영속성 컨텍스트에 저장되면 동일한 트랜잭션 내에서 동일한 식별자를 갖는 객체는
        // 모두 동일한 인스턴스를 가리키게 된다.

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member); // 둘은 동일한 객체를 가리키고 있따.
    }

    @Test
    public void findByUsernameAndAgeGreaterThen(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("AAA",20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);
        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThen("AAA",15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    //네임드쿼리 테스트
    @Test
    public void testNamedQuery(){
        Member m1 = new Member("AAA",10);
        Member m2 = new Member("AAA",20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }


}