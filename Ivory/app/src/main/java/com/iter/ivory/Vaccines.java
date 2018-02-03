package com.iter.ivory;

import java.util.Date;

/**
 * Created by David on 2/3/18.
 */
import java.util.Calendar;

public class Vaccines {
    String vaccineName;
    Date startDate;
    Date remindDate;

    static Date calculateRemindDate(Date startDate, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR,year);
        cal.add(Calendar.MONTH, month);

        return cal.getTime();
    }

    Vaccines(){

    }

    Vaccines(String vaccineName, int month, int year){
        this.vaccineName = vaccineName;
        this.startDate = new Date();
        this.remindDate = calculateRemindDate(startDate, month, year);
    }

    public String getVaccineName(){
        return vaccineName;
    }

    public void setVaccineName(String vaccineName){
        this.vaccineName = vaccineName;
    }

    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getRemindDate(){
        return remindDate;
    }
    public void setRemindData(Date remindDate){
        this.remindDate = remindDate;
    }


}
