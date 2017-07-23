package com.rematchka.weddinghall;

/**
 * Created by extra on 24/12/2016.
 */
public class photographer {

    String fname;
    String mname;
    String lname;
    String wages;
    String photog_id;
    boolean selected = false;
    String pckg_id;
    String no_of_pics;
    String pckg_price;

    photographer(String fn,String mn,String ln,String w,String i,String pckgid,String noofpics,String price)
    {
        fname=fn;
        mname=mn;
        lname=ln;
        wages=w;
        photog_id=i;
        pckg_id=pckgid;
        no_of_pics=noofpics;
        pckg_price=price;

    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getWages()
    {
        return wages;
    }
    public String getFname()
    {
        return fname;

    }
    public String getMname()
    {
        return mname;
    }
    public String getLname()
    {
        return lname;
    }
    public String getPhotog_id()
    {
        return photog_id;
    }
    public String getPckg_id()
    {
        return pckg_id;
    }
    public String getNo_of_pics()
    {
        return no_of_pics;
    }
    public String getPckg_price()
    {
        return pckg_price;
    }
}
