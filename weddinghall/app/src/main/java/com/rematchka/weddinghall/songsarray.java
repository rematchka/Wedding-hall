package com.rematchka.weddinghall;

/**
 * Created by extra on 23/12/2016.
 */
public class songsarray {
    String songid;
    String songname;
    String singername;
    boolean selected = false;

    songsarray( String sn,String i,String ss)
    {
        songid=i;
        songname=ss;
        singername=sn;
    }
    String getSongid()
    {
        return songid;
    }
    String getSongname()
    {
        return songname;
    }
    String getSingername()
    {
        return singername;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
