package inflearn.startspring.controller;

import inflearn.startspring.domain.Member;
import inflearn.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService();
    //객체를 내가 new 해서 쓰면은 스프링컨테이너가 관리하는 객체를 가지고 오는 것이 아니다.
    //생성자 주입

    private final MemberService memberService;

    @Autowired//스프링 컨테이너에서 가지고 온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    //필드주입
    //@Autowired private MemberService memberService;

    //setter 주입
    /*
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
     */

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
