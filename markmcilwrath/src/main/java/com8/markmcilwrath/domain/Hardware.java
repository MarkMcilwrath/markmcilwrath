package com8.markmcilwrath.domain;

import java.util.List;

public class Hardware {

    private String uuid;
    private String name;
    private String version;
   // private List<Assets> assets;

    public Hardware(
            String uuid,
            String name,
            String version
          //  List assets
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
     //   this.assets = assets;
    }
}
