package com.becomejavasenior.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DaoLogger extends AspectLogger {
    private static final String JOIN_POINT = "execution(* com.becomejavasenior.jdbc.*.*.*(..))";
    private static final String LOGGER_NAME = "dao_logger";

    public DaoLogger() {
        super(LOGGER_NAME);
    }

    public DaoLogger(String loggerName) {
        super(loggerName);
    }

    @AfterReturning(JOIN_POINT)
    public void logSuccessDaoOperation(JoinPoint jp) {
        if (logger.isDebugEnabled()) {
            logger.info("DAO: SUCCESS: \t" + parseJointPoint(jp));
        }
    }

    @AfterThrowing(value = JOIN_POINT, throwing = "e")
    public void logAfterThrowing(JoinPoint jp, Exception e) {
        logger.error("DAO: ERROR: \t" + parseJointPoint(jp) + " EXCEPTION: " + e.getClass() + " : " + e.getMessage(), e);
    }


}