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

  @Test(expected = Exception.class)
  public void testCronParserFalse() throws Exception {
    TaskTable t = new TaskTable ();
    CronParser.parseLine(t,"chlagadeul");
  }

  @Test
  public void testCronParserTrue() throws Exception {
    TaskTable t = new TaskTable ();
    CronParser.parseLine(t,"0 5 * * * ls");
  }
}
