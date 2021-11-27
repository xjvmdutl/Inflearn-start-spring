package inflearn.startspring.service;

import inflearn.startspring.domain.Member;
import inflearn.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository;
    //new 로 MemoryMeberRepository가 계속 생성된다.

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //테스트 코드 같은 경우 한글로 적어도 된다
        //given
        //무엇인가 주어 졋을때
        Member member = new Member();
        member.setName("hello");

        //when
        //언제
        Long saveId = memberService.join(member);

        //then
        //어떤값이 나와야되(검증)
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

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        /*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        }
        */

        //then
    }

    @Test
    void 회원_전체_조회() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        List<Member> result = memberService.findMembers();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void 회원_한명_조회() {
        Member member = new Member();
        member.setName("spring");
        memberService.join(member);
        Member result = memberService.findOne(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }
}