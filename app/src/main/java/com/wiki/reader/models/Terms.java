package com.wiki.reader.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Terms extends RealmObject
{
    private RealmList<String> description;

    public RealmList<String> getDescription() { return this.description; }

    public void setDescription(RealmList<String> description) { this.description = description; }
}