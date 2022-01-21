package com.ikhokha.techcheck.test;

import org.junit.Test;

import com.ikhokha.techcheck.service.LengthService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class LengthServiceTest {

    private LengthService testClass;
    private final String ikhokha = "iKhokha";
    private final String gratitude = "Thank you iKhokha !!";

    @Test
    public void whenGetReportKeyIsCalledThenCorrectKeyIsReturned() throws Exception
    {
        testClass = new LengthService("REPORT_KEY", LengthService.Condition.LessThan, 15);
        assertFalse(testClass.getReportKey().isEmpty());
        assertEquals(testClass.getReportKey(),"REPORT_KEY");
    }

    @Test
    public void whenLessThanCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthService("REPORT_KEY", LengthService.Condition.LessThan, 15);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenGreaterThanCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthService("REPORT_KEY", LengthService.Condition.GreaterThan, 15);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,0);
        assertEquals(thanks,1);
    }

    @Test
    public void whenEqualCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthService("REPORT_KEY", LengthService.Condition.Equal, 7);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }


    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        testClass = new LengthService("ZERO_LENGTH", LengthService.Condition.LessThan, 0);;
        assertNull(testClass);

        testClass = new LengthService("", LengthService.Condition.GreaterThan, 10);;
        assertNull(testClass);
    }
}
