package inflearn.startspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* inflearn.startspring..*(..))")//패키지명.메소드명.파라미터, 현재는 모든 메소드 파라미터에 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            //중간에 메소드가 실행될 때, Intercepter 가 발생하면서 원하는 동작을 정의 할수 있다.
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMS = finish - start;
            System.out.println("END: " + joinPoint.toString() + " "+ timeMS + "ms");
        }

    }
}
