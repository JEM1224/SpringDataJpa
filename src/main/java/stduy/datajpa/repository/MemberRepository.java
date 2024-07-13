package stduy.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stduy.datajpa.entity.Member;

//<엔티티,PK>
public interface MemberRepository extends JpaRepository<Member,Long> {

}
