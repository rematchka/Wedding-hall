package com.rematchka.weddinghall;

/**
 * Created by extra on 25/12/2016.
 */
public class accsitems {
    String acsID;
    String acsName;
    String acsPrice;

    boolean selected = false;
    accsitems(String bid,String bn,String bp)
    {
       acsID=bid;
        acsName=bn;
        acsPrice=bp;

    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getAcsID()
    {return acsID;}
    public String getAcsName()
    {return acsName;}
    public String getAcsPrice()
    {return acsPrice;}

}
