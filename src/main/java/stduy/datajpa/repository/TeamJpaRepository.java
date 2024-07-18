package stduy.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import stduy.datajpa.entity.Member;
import stduy.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    @PersistenceContext //JPA EntityManger Injection
    private EntityManager em;


    public Team save(Team team){
        em.persist(team);
        return team;
    }

    public void delete(Team team){
        em.remove(team);
    }

    public Optional<Team> findById(Long id){
        Team team = em.find(Team.class,id);
        return Optional.ofNullable(team);
        //null이면 Optional 객체를 반환한다.
        //null일 수 있는 상황에서 발생할 수 있는 NullPointerException을 방지
        //의도를 명시적으로 전달 , ifPresent, orElse 등 메서드 제공
    }

    public long count(){
        return em.createQuery("select count(m) from Team m",Long.class).getSingleResult();
    }


    public List<Team> findAll(){
        //전체를 조회하거나 특정 조건을 걸 때는  JPQL를 사용해야한다.
        return em.createQuery("select m from Team m ",Team.class).getResultList();
    }


}
