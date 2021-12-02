package inflearn.startspring;

import inflearn.startspring.aop.TimeTraceAop;
import inflearn.startspring.repository.*;
import inflearn.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /*
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    */
    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    */

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    /*
    @Bean
    public MemberRepository memberRepository(){
        //기존에 쓰이던 Repository
        //return new MemoryMemberRepository();
        //다른 코드를 변경하지 않고 JdbcMemberRepository를 만들고 SpringConfig만 수정했다

        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }
     */
    /*
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    */
}
