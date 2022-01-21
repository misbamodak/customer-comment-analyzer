package com.ikhokha.techcheck.test;

import org.junit.Before;
import org.junit.Test;

import com.ikhokha.techcheck.service.OccurrenceService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class OccurrenceServiceTest {

    private OccurrenceService testClass;
    private final String comment = "Shake things up with the new iKhokha shaker. i love the shaker!!";
    private final String noMentionsComment = "iKhokha is the future of payments";

    @Before
    public void setup() throws Exception {
        testClass = new OccurrenceService("KEY", "Shaker");
    }

    @Test
    public void whenGetReportKeyIsCalledThenCorrectKeyIsReturned()
    {
        assertFalse(testClass.getReportKey().isEmpty());
        assertEquals(testClass.getReportKey(),"KEY");
    }

    @Test
    public void whenCountIsCalledThenCorrectOccurrenceIsReturned()
    {
        int count = testClass.count(comment);
        int zero = testClass.count(noMentionsComment);
        assertEquals(count,2);
        assertEquals(zero,0);
    }


    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        testClass = new OccurrenceService("", "");
        assertNull(testClass);
    }
}
