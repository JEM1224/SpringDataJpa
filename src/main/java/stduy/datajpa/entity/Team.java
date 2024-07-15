package stduy.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name"})
public class Team {

    @Id @GeneratedValue
    @Column(name="team_id")
    private Long id;
    private String name;

    // fk = 관계의 핵심
    //1:n관계일시 fk가 없는 곳에 mappedby설정을 권장한다.
    @OneToMany(mappedBy = "team") //팀과 일대다 관계
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
