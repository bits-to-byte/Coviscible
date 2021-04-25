package com.example.coviscible.model;

import com.example.coviscible.R;

import java.sql.Timestamp;

public class Reminder {
    String name;
    String description;
    String status;
    String date;
    String id = "1";

    public String getId() {
        return id.toString();
    }

    public void setId() {
        this.id = String.valueOf(Integer.parseInt(this.id)+1);
    }


    public void setStatusColor(int statusColor) {
        this.statusColor = statusColor;
    }

    public Timestamp getDateAdded_T() {
        return dateAdded_T;
    }

    public void setDateAdded_T(Timestamp dateAdded_T) {
        this.dateAdded_T = dateAdded_T;
    }

    int statusColor;
    int statusDrawable;

    Timestamp dateAdded_T;

    public Reminder(String name, String description, String status, int statusColor, int statusDrawable, String dateAdded) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.statusColor = statusColor;
        this.statusDrawable = statusDrawable;
        this.date = dateAdded;
    }

    public Reminder() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusColor() {
        return statusColor;
    }

    public int getStatusDrawable() {
        return statusDrawable;
    }

    public String getDateAdded() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void setStatusColor() {
        if(status.equals("Active")){
            statusColor = R.color.recoveredLightText;
        }else if(status.equals("Inactive")){
            statusColor = R.color.deceasedLightText;
        }else{
        }
    }


    public void setStatusDrawable(int statusDrawable) {
        this.statusDrawable = statusDrawable;
    }

    public void setDateAdded(String dateAdded) {
        this.date = dateAdded;
    }

    private void setStatusDrawable(){
        if(status.equals("Active")){
            statusDrawable = R.drawable.active_status_circle;
        }else if(status.equals("Inactive")){
            statusDrawable = R.drawable.inactive_status_circle;
        }else{

        }
    }

    public void refreshStatus(){
        setStatusColor();
        setStatusDrawable();
    }

}
