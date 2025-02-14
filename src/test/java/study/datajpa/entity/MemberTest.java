package study.datajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1",10,teamA);
        Member member2 = new Member("member2",20,teamA);
        Member member3 = new Member("member3",30,teamB);
        Member member4 = new Member("member4",40,teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush(); //강제호출
        //em.persist 할 때 바로 디비에 쿼리를 날리는게 아니라
        //영속성 컨텍스트에 모은 후 트랜잭션이 커밋될 때 flush가 호출된다.
        em.clear(); //영속성 컨텍스트에 있는 쿼리를 다 날려보낸다.

        //확인
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = "+member);
            System.out.println("-> member.team = "+member.getTeam());

        }


    }
}