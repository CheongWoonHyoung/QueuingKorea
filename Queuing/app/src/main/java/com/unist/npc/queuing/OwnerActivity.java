package com.unist.npc.queuing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class OwnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        ArrayList<CusListItem> items = new ArrayList<>();
        CusListAdapter adapter = new CusListAdapter(getApplicationContext(),R.layout.activity_owner_item,items);
        ListView cus_list = (ListView) findViewById(R.id.cus_list);
        items.add(new CusListItem("1","정인중","3","18:30","App","NOTHING"));
        items.add(new CusListItem("2","이지형","2","18:35","Offline","NOTHING"));
        cus_list.setAdapter(adapter);


    }

}
