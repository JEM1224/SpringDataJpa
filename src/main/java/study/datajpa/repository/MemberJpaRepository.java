package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    //순수 JPA
    @PersistenceContext
    private EntityManager em;

    //update가 없는 이유는 JPA는 엔티티의 변경을 감지하여
    // 자동으로 데이트를 처리하기 때문이다.(더티 체킹)
    //영속성 컨텍스트에 엔티티의 원본 상태를 저장
    // 트랜잭셔이 커밋되거나 flush가 호출될 때 JPA는 엔티티의 현재상태와 원본상태를 비교
    // 변경된 부분이 있으면 JPA는 자동으로 업데이트 쿼리를 생성하여 데이터 베이스에 반영

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member find(Long id){
        return em.find(Member.class,id);
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
        //null이면 Optional 객체를 반환한다.
        //null일 수 있는 상황에서 발생할 수 있는 NullPointerException을 방지
        //의도를 명시적으로 전달 , ifPresent, orElse 등 메서드 제공
    }

    public long count(){
        return em.createQuery("select count(m) from Member m",Long.class).getSingleResult();
    }

    public void delete(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        //전체를 조회하거나 특정 조건을 걸 때는  JPQL를 사용해야한다.
        return em.createQuery("select m from Member m ",Member.class).getResultList();
    }


    //회원의 이름과 나이 기준으로 검색
    public List<Member> findByUsernameAndAgeGreaterThen(String username,int age){
        return em.createQuery("select m from Member m where m.username =:username and m.age> :age")
                .setParameter("username",username)
                .setParameter("age",age)
                .getResultList();
    }



}
