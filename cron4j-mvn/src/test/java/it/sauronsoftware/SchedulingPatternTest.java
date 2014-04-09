package it.sauronsoftware;

import it.sauronsoftware.cron4j.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SchedulingPatternTest {

    @BeforeClass
	public static void testSetup() {
    }

    @AfterClass
	public static void testCleanup() {
	// Teardown for data used by the unit tests
    }

    @Test(expected = InvalidPatternException.class)
	public void testExceptionIsThrown() {
	SchedulingPattern sp = new SchedulingPattern("0 5 * *");
    }

    @Test
	public void testPattern() {
	String pattern;
	pattern="0 5 * * *|8 10 * * *|22 17 * * *";
	assertTrue(pattern + "is correct", SchedulingPattern.validate(pattern));
	pattern="0 5 * * *";
	assertTrue(pattern + "is correct", SchedulingPattern.validate(pattern));
    }

    public class HelloRunnable implements Runnable {
	
	private int i = 0;

	public void run() {
	    i++;
	    System.out.println("Hello from a thread ("+i+")");
	}
    }

    @Test
	public void testRunableTask() {
	Task task = new RunnableTask(new HelloRunnable());
	Scheduler scheduler = new Scheduler();
	scheduler.schedule("* * * * *", task);
	scheduler.start();
	try {
	    Thread.sleep(3L * 60L * 1000L);
	} catch (InterruptedException e) {}
	scheduler.stop();
    }
}
