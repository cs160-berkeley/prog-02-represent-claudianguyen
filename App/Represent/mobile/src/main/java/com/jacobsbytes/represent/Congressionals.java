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

    public Congressionals(int iconId, String title, String name, String party, String email, String websiteurl, String tweet) {
        super();
        this.iconId = iconId;
        this.title = title;
        this.name = name;
        this.party = party;
        this.email = email;
        this.websiteurl = websiteurl;
        this.tweet = tweet;
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
}