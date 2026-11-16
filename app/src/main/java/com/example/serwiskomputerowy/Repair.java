package com.example.serwiskomputerowy;

public class Repair {

    public String device;
    public String reporter;
    public String description;
    public String status;

    public Repair(String device, String reporter,
                  String description, String status) {

        this.device = device;
        this.reporter = reporter;
        this.description = description;
        this.status = status;
    }

    public String getListTitle() {
        return device + "  -  " + status;
    }
}