package com.iter.ivory;

/**
 * Created by David on 2/3/18.
 */
import java.util.*;

public class User {

    String name;
    ArrayList<Vaccines> vaccinations = new ArrayList<>();

    User(){

    }

    User(String name, ArrayList<Vaccines> vaccinations){
        this.name = name;
        this.vaccinations = vaccinations;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Vaccines> getVaccinations(){
        return vaccinations;
    }

    public void setVaccinations(ArrayList<Vaccines> vaccinations){
        this.vaccinations = vaccinations;
    }
}
