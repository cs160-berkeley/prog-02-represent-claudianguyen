/**
 * Created by CLAUDIA NGUYEN on 3/2/2016.
 */
package com.jacobsbytes.represent;

/**
 * store information about representatives
 */
public class CongressionalList {

    private String title;
    private String name;
    private String party;
    private String email;
    private String websiteurl;
    private String tweet;

    public CongressionalList(String title, String name, String party, String email, String websiteurl, String tweet) {
        super();
        this.title = title;
        this.name = name;
        this.party = party;
        this.email = email;
        this.websiteurl = websiteurl;
        this.tweet = tweet;
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

