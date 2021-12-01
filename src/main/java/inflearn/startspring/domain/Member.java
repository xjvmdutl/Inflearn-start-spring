package inflearn.startspring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id//PK설정
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//DB가 알아서 생성해줌
    private Long id;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
