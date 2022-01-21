package com.ikhokha.techcheck.service;

public class LengthService implements PatternService {

    public static enum Condition {
        GreaterThan,
        LessThan,
        Equal
    }

    private String key;
    private int length;
    private Condition condition;

    public LengthService(String reportKey, Condition condition, int length) throws Exception{
        if(length < 1){
            throw new Exception("LengthService: Condition length cannot be less than 1");
        }else if(reportKey.isEmpty()){
            throw new Exception("LengthService: Report key cannot be empty");
        }
        this.key = reportKey;
        this.length = length;
        this.condition = condition;
    }

    @Override
    public String getReportKey() {
        return this.key;
    }

    @Override
    public int count(String comment) {
        switch (this.condition){
            case Equal:
                return comment.length() == length ? 1 : 0;
            case LessThan:
                return comment.length() < length ? 1 : 0;
            case GreaterThan:
                return comment.length() > length ? 1 : 0;
        }
        return 0;
    }
}
