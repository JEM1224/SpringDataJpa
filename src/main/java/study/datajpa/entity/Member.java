package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

// entity 는 기본생성자가 있어야한다.
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"}) //출력을 위한 , 연관관계 걸려있는 출력은 피하는게 좋다.
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id") //테이블 매핑 값
    private Long id;
    private String username;
    private int age;

    //멤버와 팀은 다대일 관계
    //지연로딩 - 멤버를 조회할 때 멤버만 사용하고
    // lazyloaing이 설정 되어있는 team 엔티티는 프록시 객체로 가져와 사용하는 시점에 DB에 쿼리가 나간다.
    // getTeam() 으로 team을 조회하면 프록시 객체가 조회된다.
    // getTeam().getName()으로 팀의 필드에 접근하면 쿼리가 이 때 나간다.
    @ManyToOne(fetch = FetchType.LAZY) //성능 최적화를 위해 lazy로 설정한다.
    @JoinColumn(name="team_id")
    private Team team;

//    //private으로 막아놓으면 jpa가 객체를 생성하지 못한다.
//    protected Member(){
// -> NoArgsConstructor 사용
//    }
    public Member(String username){
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username=username;
        this.age =age;
        if(team!=null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username=username;
        this.age =age;
    }

    public void changeTeam(Team team){
        this.team = team; //나의팀을 변경하고
        team.getMembers().add(this); // 해당 팀에도 나를 넣어준다.
    }
}
