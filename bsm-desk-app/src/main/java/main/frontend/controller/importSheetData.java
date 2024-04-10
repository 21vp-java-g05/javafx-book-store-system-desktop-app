package main.frontend.controller;

import java.time.LocalDateTime;

public class importSheetData {
    private final int importId;
    private final LocalDateTime importTime;
    private final String employee;
    private final float totalCost;

    public importSheetData(int importId, LocalDateTime importTime, String employee, float totalCost) {
        this.importId = importId;
        this.importTime = importTime;
        this.employee = employee;
        this.totalCost = totalCost;
    }

    public int getImportId() {
        return importId;
    }

    public LocalDateTime getImportTime() {
        return importTime;
    }

    public String getEmployeeName() {
        return employee;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
