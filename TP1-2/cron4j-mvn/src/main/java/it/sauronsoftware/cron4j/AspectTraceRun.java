package it.sauronsoftware.cron4j;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

import java.util.concurrent.atomic.AtomicInteger;


@Aspect
    public class AspectTraceRun {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    public static class CoupleInteger {
     	private Integer id;
	private Integer cpt;
	
	public CoupleInteger (){ this.id=nextId.getAndIncrement(); this.cpt=0;}
	
	public Integer getId () { return id; }
	public Integer getCpt () { return cpt; }
        public void incr () { cpt++; }
    }

    private static final ThreadLocal<CoupleInteger> threadId =
	new ThreadLocal<CoupleInteger>() {
	@Override protected CoupleInteger initialValue() {
	    return new CoupleInteger();
	}
    };

    public static CoupleInteger get() {
	return threadId.get();
    }

    @Before("execution(* RunnableTask.execute(..))")
	public void printB () { 
        threadId.get().incr();
	System.out.println("run() is starting! (ThreadLocal: id:"+get().getId()+" cpt: "+get().getCpt()+")");
    }

    @After("execution(* RunnableTask.execute(..))")
	public void printA () { 
	System.out.println("run() is ending!");
    }
}
