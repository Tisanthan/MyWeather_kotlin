package com.example.beeradviser;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color){
        List<String> brands = new ArrayList<String>();
        if (color.equals("amber")){
            brands.add("jack Amber");
            brands.add("Red moose");
        }else{
            brands.add("jail pale ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}


