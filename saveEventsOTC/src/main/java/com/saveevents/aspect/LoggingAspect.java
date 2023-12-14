/*
 * package com.saveevents.aspect;
 * 
 * import org.aspectj.lang.JoinPoint; import org.aspectj.lang.annotation.After;
 * import org.aspectj.lang.annotation.AfterReturning; import
 * org.aspectj.lang.annotation.Aspect; import
 * org.aspectj.lang.annotation.Before; import
 * org.aspectj.lang.annotation.Pointcut; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import org.springframework.stereotype.Component;
 * 
 * @Component
 * 
 * @Aspect public class LoggingAspect { Logger logger =
 * LoggerFactory.getLogger(LoggingAspect.class);
 * 
 * @Pointcut("execution(* com.saveevents.service.*.*(..))") public void
 * servicePointcut() throws NoSuchMethodException, SecurityException {
 * 
 * }
 * 
 * @Before("execution(* com.saveevents.controller.*.*(..))") public void
 * controllerLog(JoinPoint joinPoint) {
 * logger.debug("calling "+joinPoint.getSignature().getName()+" controller"); }
 * 
 * @Before("servicePointcut()") public void callLogMethod(JoinPoint joinPoint) {
 * logger.debug("executing "+joinPoint.getSignature().getName()+" "+"service");
 * logger.debug("target object "+joinPoint.getTarget());
 * if(joinPoint.getArgs()!=null) { Object[] args = joinPoint.getArgs();
 * 
 * for(Object arguments:args) { logger.info("getting arguments: "+arguments); }
 * } }
 * 
 * @AfterReturning(pointcut = "servicePointcut()",returning = "result") public
 * void logResult(JoinPoint joinPoint,Object result) {
 * logger.info("after executing "+joinPoint.getSignature().getName()
 * +" service return value is "+result.toString()); }
 * 
 * }
 */