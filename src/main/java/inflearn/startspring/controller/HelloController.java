package inflearn.startspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name",required = false) String name,Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//응답 Http Body에 데이터를 직접 넣어준다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;//이 데이터를 그대로 내려 보내준다.
    }

    @GetMapping("hello-api")
    @ResponseBody//응답 Http Body에 데이터를 직접 넣어준다는 의미
    public Hello helloApi(@RequestParam("name") String name){
       Hello hello = new Hello();
       hello.setName(name);
       return hello;//객체를 반환
    }

    static class Hello{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
