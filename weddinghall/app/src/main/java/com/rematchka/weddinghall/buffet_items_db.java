package com.rematchka.weddinghall;

/**
 * Created by extra on 25/12/2016.
 */
public class buffet_items_db {
    String buffetid;
    String buffetname;
    String buffetprice;
    String buffetcategory;
    boolean selected = false;
    buffet_items_db(String bid,String bn,String bp,String bc)
    {
        buffetid=bid;
        buffetname=bn;
        buffetprice=bp;
        buffetcategory=bc;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getBuffetid()
    {return buffetid;}
    public String getBuffetname()
    {return buffetname;}
    public String getBuffetprice()
    {return buffetprice;}
    public String getBuffetcategory()
    {return buffetcategory;}
}
