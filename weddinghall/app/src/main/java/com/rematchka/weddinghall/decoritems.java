package com.rematchka.weddinghall;

/**
 * Created by extra on 25/12/2016.
 */
public class decoritems {
    String decorID;
    String decorName;
    String decorPrice;

    boolean selected = false;
    decoritems(String bid,String bn,String bp)
    {
        decorID=bid;
        decorName=bn;
        decorPrice=bp;

    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getDecorID()
    {return decorID;}
    public String getDecorName()
    {return decorName;}
    public String getDecorPrice()
    {return decorPrice;}

}

