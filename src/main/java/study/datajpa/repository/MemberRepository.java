package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

/*
*
* 인터페이스에 구현체가 없는데 어떻게 동작하냐
* memberRepository.getClass() // class com.sun.proxy.$Proxy
* 스프링이 인터페이스를 보고 스프링데이터 JPA 구현 클래스를 만들어서 주입해줌
*
*
* 쿼리 메서드
* 조회 : find..By,read..By,query..By,get...By
* COUNT : count..By
* EXISTS : exists..By
* 삭제 : delete ..By ,remove..By
* DISTINCT : findDistinct,findMemberDistinctBy
* LIMIT : findFirst3,findFirst,findTop,findTop3
*
* 주의 ! 엔티티 필드명이 변경되면 인터페이스에 정의한 메서드 이름도 꼭 변경해야한다.
* 로딩 시점에서 오류 발견 가능
*
* 쿼리 메서드 기능 3가지
* 1. 메서드 이름으로 쿼리 작성
* 2. 메소드 이름으로 JPA NamedQuery 호출 //정적쿼리 , 거의 쓸 일이 없음, 애플리케이션 로딩 시점에 오류를 알려줌
* 3. @Query 어노테이션을 사용해서 리파지토리 인터페이스에 쿼리 직접 정의
* */


//<엔티티,PK>
public interface MemberRepository extends JpaRepository<Member,Long> {


    //쿼리 메서드 -> 메서드 이름으로 쿼리를 생성함
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //네임드 쿼리
    //@Query(name="Member.findByUsername") 없어도됨
    List<Member> findByUsername(@Param("username") String username);

}
