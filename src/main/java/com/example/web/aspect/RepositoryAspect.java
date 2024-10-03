//package com.example.web.aspect;
//import java.util.Date;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import com.example.web.entities.Audit;
//
//@Aspect
//@Component
//public class RepositoryAspect {
//
//    @Around("execution(* org.springframework.data.repository.CrudRepository+.save(..))")
//    public Object aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
//        // Logic to execute before the save method
//        System.out.println("Before save method: " + joinPoint.getSignature());
//        
//        // Get the arguments of the save method
//        Object[] args = joinPoint.getArgs();
//
//        // Assuming the save method has only one argument (the entity)
//        if (args.length > 0) {
//            Audit entity = (Audit)args[0];
//           entity.setCreatedOn(new Date());
//            // You can add your logic to process the entity here
//        }
//
//        // Proceed with the original method call
//        Object result = joinPoint.proceed();
//
//        // Logic to execute after the save method, if needed
//        System.out.println("After save method: " + joinPoint.getSignature());
//
//        return result;
//    }
//}
