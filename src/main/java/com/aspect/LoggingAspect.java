package com.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private static final String POINT_CUT = "execution(* com.service.AbstractService.save(..))";

	@Pointcut(POINT_CUT)
	public void logBefore() {
	}

	@Pointcut(POINT_CUT)
	public void logBeforeWithArgs() {
	}

	@Pointcut(POINT_CUT)
	public void logBeforeThrow() {
	}

	@Before("logBefore()")
	public void logBeforeHandler(JoinPoint joinPoint) {
		logger.debug("Attempt: " + joinPoint.getSignature().getName());
	}

	@Before("logBeforeWithArgs()")
	public void logBeforeWithArgsHandler(JoinPoint joinPoint) {
		logger.debug("Attempt: " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "logBeforeThrow()", throwing = "e")
	public void logBeforeHandlerThrowing(JoinPoint joinPoint, Exception e) {
		logger.debug("Attempt: " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs())
				+ " " + e.getMessage());
	}

}
