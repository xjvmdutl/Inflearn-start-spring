package inflearn.startspring.service;

import inflearn.startspring.domain.Member;
import inflearn.startspring.repository.MemberRepository;
import inflearn.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional//Test를 실행할때 Transactional어노테이션을 달게 되면 DB에 실제 넣지 않고 Test를 실행한뒤, 롤백해준다.
@SpringBootTest//스프링 테스트를 위한 어노테이션
class MemberServiceIntegrationTest {
    //테스트 케이스같은 경우 다른데에서 가져다 쓰는것이 아니기 때문에 굳이 생성자를 통한 주입을 받을 필요는 없다
    @Autowired MemberService memberService;

    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("spring");
        //@Transactional 해당 어노테이션 때문에 테스트 케이스를 계속 실행 하여도 문제 없다
        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

    }


}