package it.sauronsoftware.cron4j;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;


@Aspect
    public class AspectTraceRun {

    @Before("execution(* RunnableTask.execute(..))")
	public void printB () { 
	System.out.println("run() is starting!");
    }

    @After("execution(* RunnableTask.execute(..))")
	public void printA () { 
	System.out.println("run() is ending!");
    }
}
