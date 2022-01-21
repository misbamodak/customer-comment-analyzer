package com.ikhokha.techcheck.test;

import org.junit.Test;

import com.ikhokha.techcheck.service.ContainsService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class ContainsServiceTest {

    private ContainsService testClass;
    private final String question = "How do i view my sales report from a specific card reader?";
    private final String gratitude = "Thank you iKhokha !!";
    private final String spam = "Checkout the new Square Reader for magstripe https://squareup.com/us/en/hardware/reader";

    @Test
    public void whenGetReportKeyIsCalledThenCorrectKeyIsReturned() throws Exception
    {
        testClass = new ContainsService("THE_REPORT_KEY", "[?]");
        assertFalse(testClass.getReportKey().isEmpty());
        assertEquals(testClass.getReportKey(),"THE_REPORT_KEY");
    }

    @Test
    public void whenQuestionsCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new ContainsService("REPORT_KEY", "[?]");
        int count = testClass.count(question);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenSpamCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        final String URL_REGEX = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
        testClass = new ContainsService("REPORT_KEY", URL_REGEX);
        int count = testClass.count(spam);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        testClass = new ContainsService("", "");
        assertNull(testClass);
    }
}
