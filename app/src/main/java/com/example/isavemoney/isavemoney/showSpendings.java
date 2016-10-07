package com.example.isavemoney.isavemoney;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class showSpendings extends AppCompatActivity {
    SpendingsDBToShow db = new SpendingsDBToShow(this);
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spendings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.textView);
        Cursor res = db.getAllData();
        if(res.getCount()==0){
            textView.setText("No expenditures yet!");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Date :"+ res.getString(0)+"\n");
            buffer.append("Type :"+ res.getString(1)+"\n");
            buffer.append("Cost :"+ res.getString(2)+"\n\n");
        }
        String str = buffer.toString();
            textView.setText(str);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
