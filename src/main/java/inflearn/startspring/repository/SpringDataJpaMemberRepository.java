package inflearn.startspring.repository;

import inflearn.startspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//SpringJPAData가 interface구현체를 만들고 spring 빈에 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {


    //select m from Member m where name = ?
    //인터페이스 이름만으로 쿼리를 만들어 준다.
    @Override
    Optional<Member> findByName(String name);


}
