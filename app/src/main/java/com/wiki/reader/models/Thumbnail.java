package com.wiki.reader.models;

import io.realm.RealmObject;

public class Thumbnail extends RealmObject
{
    private String source;

    public String getSource() { return this.source; }

    public void setSource(String source) { this.source = source; }

    private int width;

    public int getWidth() { return this.width; }

    public void setWidth(int width) { this.width = width; }

    private int height;

    public int getHeight() { return this.height; }

    public void setHeight(int height) { this.height = height; }
}