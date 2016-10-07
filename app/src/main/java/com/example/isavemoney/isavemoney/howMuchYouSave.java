package com.example.isavemoney.isavemoney;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class howMuchYouSave extends AppCompatActivity {
    EditText savingsAmount;
    Button submitAmount;
    SavingsDB db = new SavingsDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_much_you_save);

        savingsAmount = (EditText) findViewById(R.id.number);
        submitAmount = (Button) findViewById(R.id.submit);

        submitAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int savingNumber = Integer.parseInt(savingsAmount.getText().toString());
                db.openDB();
                db.addSaving(savingNumber);
                if(savingNumber > 0){
                    Toast.makeText(getApplicationContext(), "You want to save " + savingNumber + " per month", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Your savings must be more than 0", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
