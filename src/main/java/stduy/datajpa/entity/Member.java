package stduy.datajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// entity 는 기본생성자가 있어야한다.
@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    //private으로 막아놓으면 jpa가 객체를 생성하지 못한다.
    protected Member(){

    }
    public Member(String username){
        this.username = username;
    }

}
