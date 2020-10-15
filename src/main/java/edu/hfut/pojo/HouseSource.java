package edu.hfut.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseSource {
    private int hid;
    private Date hDate;
    private String location;
    private String title;
    private double price;
    private String shape;
    private double area;
    private String hPhone;
    private  String description;
}
