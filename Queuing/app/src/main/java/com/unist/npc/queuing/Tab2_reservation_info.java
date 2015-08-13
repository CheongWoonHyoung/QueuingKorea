package com.unist.npc.queuing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab2_reservation_info extends Fragment {
    Boolean isQueue = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = null;
        if(isQueue){
            v = inflater.inflate(R.layout.tab2_reservation_info,container,false);

        }else{
            v = inflater.inflate(R.layout.tab2_reservation_info_blank,container,false);

        }
        return v;
    }
}
