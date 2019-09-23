package com.seatcode.robotread;

public class ReportPrinter {
    private Console console;
    private ReportFormatter reportFormatter;

    public ReportPrinter(Console console, ReportFormatter reportFormatter) {
        this.console = console;
        this.reportFormatter = reportFormatter;
    }

    public void report(Record record) {
        String report = reportFormatter.execute(record);
        console.print(report);
    }
}
