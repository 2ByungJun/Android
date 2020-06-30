package com.example.calculator;

import java.io.Serializable;

public class Option implements Serializable {
    String opDate;    // 날짜
    String opProcess; // 수식
    String opResult;  // 결과

    /*****************************************************************
     * Constructor
     *****************************************************************/
    public Option(){
    }
    public Option(String opDate, String opProcess, String opResult) {
        this.opDate = opDate;
        this.opProcess = opProcess;
        this.opResult = opResult;
    }

    /*****************************************************************
     * Getter & Setter
     *****************************************************************/
    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getOpProcess() {
        return opProcess;
    }

    public void setOpProcess(String opProcess) {
        this.opProcess = opProcess;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    /*****************************************************************
     * ToString
     *****************************************************************/
    @Override
    public String toString() {
        return "Option{" +
                "opDate='" + opDate + '\'' +
                ", opProcess='" + opProcess + '\'' +
                ", opResult='" + opResult + '\'' +
                '}';
    }
}
