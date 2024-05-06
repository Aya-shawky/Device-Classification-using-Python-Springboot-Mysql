package com.example.springserver.model;
// User.java
//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double battery_power;
    private int blue;
    private double clock_speed;
    private int dual_sim;
    private int fc;
    private int four_g;
    private double int_memory;
    private double m_dep;
    private double mobile_wt;
    private double n_cores;
    private double pc;
    private double px_height;
    private double px_width;
    private double ram;
    private double sc_h;
    private double sc_w;
    private double talk_time;
    private int three_g;
    private int touch_screen;
    private int wifi;
    private int price_range;


    public Device() {
    }
    public Device( double battery_power, int blue, double clock_speed,
    int dual_sim, int fc, int four_g, double int_memory,
     double m_dep, double mobile_wt, double n_cores,  double pc,
     double px_height,  double px_width, double ram,  double sc_h,  double sc_w,
     double talk_time, int three_g, int touch_screen,
     int wifi) 
     {
//     this.id = id;
     this.battery_power= battery_power;
     this.blue= blue;
     this.clock_speed= clock_speed;
     this.dual_sim= dual_sim;
     this.fc= fc;
     this.four_g= four_g;
     this.int_memory= int_memory;
     this.m_dep= m_dep;
     this.mobile_wt= mobile_wt;
     this.n_cores= n_cores;
     this.pc= pc;
     this.px_height= px_height;
     this.px_width= px_width;
     this.ram= ram;
     this.sc_h= sc_h;
     this.sc_w= sc_w;
     this.talk_time= talk_time;
     this.three_g= three_g;
     this.touch_screen= touch_screen;
     this.wifi= wifi;
     this.price_range = -1;
    }



    public Long getId() {
        return id;
    }



    public double getBattery_power() {
        return battery_power;
    }



    public int getBlue() {
        return blue;
    }



    public double getClock_speed() {
        return clock_speed;
    }



    public int getDual_sim() {
        return dual_sim;
    }



    public int getFc() {
        return fc;
    }



    public int getFour_g() {
        return four_g;
    }



    public double getInt_memory() {
        return int_memory;
    }



    public double getM_dep() {
        return m_dep;
    }



    public double getMobile_wt() {
        return mobile_wt;
    }



    public double getN_cores() {
        return n_cores;
    }



    public double getPc() {
        return pc;
    }



    public double getPx_height() {
        return px_height;
    }



    public double getPx_width() {
        return px_width;
    }



    public double getRam() {
        return ram;
    }



    public double getSc_h() {
        return sc_h;
    }



    public double getSc_w() {
        return sc_w;
    }



    public double getTalk_time() {
        return talk_time;
    }


    public int getThree_g() {
        return three_g;
    }

    public int getTouch_screen() {
        return touch_screen;
    }

    public int getWifi() {
        return wifi;
    }

    public double getPrice_range() {
        return price_range;
    }

    public void setID(Long id) {
        this.id = id;
    }
    

    public void setprice_range(int price_range) {
        this.price_range = price_range;
    }
     // Getters and setters 

}
