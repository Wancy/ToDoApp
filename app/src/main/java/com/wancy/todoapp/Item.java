package com.wancy.todoapp;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class)
public class Item extends BaseModel {
    @Column
    @PrimaryKey (autoincrement=true)
    private int id;

    @Column
    private String content;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() { return this.content; }

}
