package com.jacobsbytes.represent;

/**
 * Created by CLAUDIA NGUYEN on 3/2/2016.
 */
public class Congressionals {

    private int iconId;
    private String title;
    private String name;
    private String party;
    private String email;
    private String websiteurl;
    private String tweet;
    private String endTerm;
    private String bioguide_id;

    public Congressionals(int iconId, String title, String name, String party, String email, String websiteurl, String tweet, String endTerm, String bioguide_id) {
        super();
        this.iconId = iconId;
        this.title = title;
        this.name = name;
        this.party = party;
        this.email = email;
        this.websiteurl = websiteurl;
        this.tweet = tweet;
        this.endTerm = endTerm;
        this.bioguide_id = bioguide_id;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title; }

    public String getName() {
        return name; }

    public String getParty() {
        return party; }

    public String getEmail() {
        return email; }

    public String getWebsiteurl() {
        return websiteurl; }

    public String getTweet() {
        return tweet;}

    public String getEndTerm() {
        return endTerm;
    }
    public String getBioguide_id() {
        return bioguide_id;}
}