package inflearn.startspring;

import inflearn.startspring.repository.MemberRepository;
import inflearn.startspring.repository.MemoryMemberRepository;
import inflearn.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
