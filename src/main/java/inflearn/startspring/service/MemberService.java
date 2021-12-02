package inflearn.startspring.service;


import inflearn.startspring.domain.Member;
import inflearn.startspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member){
        /*
        //모든 로직에 시간을 계산하는 로직이 들어간다.
        long start = System.currentTimeMillis();
        try {
            //같은 이름이 있는 중복회원 x
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
        */
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(
                member.getName()).ifPresent(m ->{
                    //OPtional을 썻기 때문에 사용가능한 메소드
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        /*
        //모든 로직에 시간을 계산하는 로직이 들어간다.
        long start = System.currentTimeMillis();
        try {
            //서비스는 비지니스 로직과 연관지어 네이밍 해야된다.
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
        */
        return memberRepository.findAll();
    }

    /**
     *
     * 회원 ID 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
