package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

/*
*
* 인터페이스에 구현체가 없는데 어떻게 동작하냐
* memberRepository.getClass() // class com.sun.proxy.$Proxy
* 스프링이 인터페이스를 보고 스프링데이터 JPA 구현 클래스를 만들어서 주입해줌
*
*
*
* */

//<엔티티,PK>
public interface MemberRepository extends JpaRepository<Member,Long> {

}
