package com.ikhokha.techcheck.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainsService implements PatternService {

    private String key;
    private String regex;

    public ContainsService(String reportKey, String regex) throws Exception {
        if(regex.isEmpty() || reportKey.isEmpty()){
            throw new Exception("ContainsService: Report key or regex cannot be empty");
        }
        this.key = reportKey;
        this.regex = regex;
    }

    @Override
    public String getReportKey() {
        return key;
    }

    @Override
    public int count(String comment) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(comment);
        return matcher.find() ? 1 : 0;
    }
}
