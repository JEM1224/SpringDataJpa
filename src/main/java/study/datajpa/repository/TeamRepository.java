package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

//@Repository 생략 가능
public interface TeamRepository extends JpaRepository<Team,Long> {
}

/*
* JpaRepository -> package org.springframework.data.jpa.repository
*
* PagingAndSoringRepository ->package org.springframework.data.repository
*
* spring-data-commons라는 공통의 프로젝트가 있다.
*
* CrudRepository -> 기본적인 crud 메서드가 있다.
*
* Repository <- CurdRepository <- PagingAndSortingRepository<- JpaRepository
*
* 이름으로 멤버를 찾고싶음
* -> 상속해서 구현한다 -> 너무 많음
* -> 이름으로 멤버를 찾는 커스텀 기능 추가
* -> 쿼리 메서드 기능 이용
* * */