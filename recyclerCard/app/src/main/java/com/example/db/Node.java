package com.example.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app")
public class Node {

    @ColumnInfo(name="parent_id")
    private int parent_id;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "description")
    private String description;


    @ColumnInfo(name = "date")
    private String date;


    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }
    @ColumnInfo(name="name")
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setParent_id(int parent_id){
        this.parent_id = parent_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }

    public String getDate() {return date ;}
    public void setDate(String date){
        this.date = date;
    }
    public String getDescription() {return description;}
    public void setDescription(String Description) {this.description = Description;}

}
