package com.jacobsbytes.represent;

/**
 * Created by CLAUDIA NGUYEN on 3/3/2016.
 */

public class Members {

    private int iconId;
    private String name;
    private String title;
    private String party;

    public Members (int iconId, String title, String name, String party) {
        super();
        this.iconId = iconId;
        this.title = title;
        this.name = name;
        this.party = party;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

}
