package com8.markmcilwrath.domain;

import java.util.List;

public class Software {

    private String uuid;
    private String name;
    private String version;
   // private List<License> licenses;

    public Software(String uuid, String name, String version) {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
    }

}
