package com.wiki.reader.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Page extends RealmObject
{
    @PrimaryKey
    private int pageid;

    public int getPageid() { return this.pageid; }

    public void setPageid(int pageid) { this.pageid = pageid; }

    private int ns;

    public int getNs() { return this.ns; }

    public void setNs(int ns) { this.ns = ns; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private int index;

    public int getIndex() { return this.index; }

    public void setIndex(int index) { this.index = index; }

    private Thumbnail thumbnail;

    public Thumbnail getThumbnail() { return this.thumbnail; }

    public void setThumbnail(Thumbnail thumbnail) { this.thumbnail = thumbnail; }

    private Terms terms;

    public Terms getTerms() { return this.terms; }

    public void setTerms(Terms terms) { this.terms = terms; }
}