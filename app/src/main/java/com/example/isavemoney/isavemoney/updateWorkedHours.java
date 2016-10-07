package com.example.isavemoney.isavemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updateWorkedHours extends AppCompatActivity {
    String hoursYouPutInText;
    int hoursYouPutIn;
    double weeklyHourlyRate;
    int yourLastWeeksEarnings;
    VariableSalaryDBAdapter variabledb = new VariableSalaryDBAdapter(this);
    TextView newHoursText;
    EditText newHours;
    Button submitNewHours;
    FixedMonthlyDBAdapter db = new FixedMonthlyDBAdapter(this);

    public void putInYourHours(){
        if(!db.weeklyHourlyPayIsEmpty() && (db.monthlyHourlyPayIsEmpty()|| db.getLastInsertFromMonthlyHourlyPay()==null)){
            newHoursText.setText("Put in your last week's hours");
            submitNewHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoursYouPutInText = newHours.getText().toString();
                    if (hoursYouPutInText.equals("") || Integer.parseInt(hoursYouPutInText)==0){
                        Toast.makeText(getApplicationContext(), "You must put in hours that are more than 0"  , Toast.LENGTH_SHORT).show();
                    } else {
                        hoursYouPutIn = Integer.parseInt(newHours.getText().toString());
                    weeklyHourlyRate = Double.parseDouble(db.getLastInsertFromWeeklyHourlyPay());
                    yourLastWeeksEarnings = (int) (hoursYouPutIn * weeklyHourlyRate);
                    variabledb.addVariableWeeklySalary(yourLastWeeksEarnings);
                    Toast.makeText(getApplicationContext(), "Your last week's salary is " + yourLastWeeksEarnings  , Toast.LENGTH_SHORT).show();
                    Intent goHome = new Intent(updateWorkedHours.this, MainActivity.class);
                    startActivity(goHome);

                }
            }});

        } else
        {
            newHoursText.setText("Put in your last month's hours");
            submitNewHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hoursYouPutInText = newHours.getText().toString();
                    if (hoursYouPutInText.equals("") || Integer.parseInt(hoursYouPutInText)==0){
                        Toast.makeText(getApplicationContext(), "You must put in hours that are more than 0"  , Toast.LENGTH_SHORT).show();
                    } else {
                    hoursYouPutIn = Integer.parseInt(newHours.getText().toString());
                    weeklyHourlyRate = Double.parseDouble(db.getLastInsertFromMonthlyHourlyPay());
                    yourLastWeeksEarnings = (int) (hoursYouPutIn * weeklyHourlyRate);
                    variabledb.addVariableMonthlySalary(yourLastWeeksEarnings);
                        Toast.makeText(getApplicationContext(), "Your last month's salary is " + yourLastWeeksEarnings  , Toast.LENGTH_SHORT).show();
                        Intent goHome = new Intent(updateWorkedHours.this, MainActivity.class);
                        startActivity(goHome);

                    }

                }
            });


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_worked_hours);
        newHoursText = (TextView) findViewById(R.id.newHoursText);
        newHours = (EditText) findViewById(R.id.newHoursYouPutIn);
        submitNewHours = (Button) findViewById(R.id.submitNewHours);
        putInYourHours();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
