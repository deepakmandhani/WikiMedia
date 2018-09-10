package com.wiki.reader.models;

import java.util.ArrayList;

public class Query
{
    private ArrayList<Redirect> redirects;

    public ArrayList<Redirect> getRedirects() { return this.redirects; }

    public void setRedirects(ArrayList<Redirect> redirects) { this.redirects = redirects; }

    private ArrayList<Page> pages;

    public ArrayList<Page> getPages() { return this.pages; }

    public void setPages(ArrayList<Page> pages) { this.pages = pages; }
}
