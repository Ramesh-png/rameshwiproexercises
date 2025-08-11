package com.example.kafka_common.model;

public class Subject {
    private String subjectCode;
    private String subjectValue;

    public Subject() {
    }

    public Subject(String subjectCode, String subjectValue) {
        this.subjectCode = subjectCode;
        this.subjectValue = subjectValue;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectValue() {
        return subjectValue;
    }

    public void setSubjectValue(String subjectValue) {
        this.subjectValue = subjectValue;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectValue='" + subjectValue + '\'' +
                '}';
    }
}
