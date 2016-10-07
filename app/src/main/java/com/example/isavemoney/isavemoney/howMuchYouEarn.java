package com.example.isavemoney.isavemoney;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class howMuchYouEarn extends AppCompatActivity implements OnItemSelectedListener {
    String myHourlyPayText;
    String myHoursText;
    String salaryText;
    String monthlySalaryText;
    TextView howManyHoursText;
    EditText howManyHoursInput;
    Button weeklyHourlyPayButton;
    Button monthlyHourlyPayButton;
    private FixedMonthlyDBAdapter db = new FixedMonthlyDBAdapter(this);
    private VariableSalaryDBAdapter variabledb = new VariableSalaryDBAdapter(this);
    Button submitWeeklySalaryButton;
    Button submitButton;
    TextView dayGetPaid;
    EditText hourlyPay;
    TextView howMuchYouArePaidPerHour;
    TextView textViewEarnings;
    EditText monthlySalary;
    Spinner typeOfSalary;
    Spinner howOftenYouGetYourSalary;
    static final String[] typesOfSalary = {"What type of salary do you get?", "Fixed", "Variable"};
    static final String[] howOftenDoYouGetYourSalary = {"How often do you get your salary?", "Weekly", "Monthly"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public void goHomepage(View view){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
    }
    public void goBackToHowMuchYouEarn() {
        Intent goBack = new Intent(this, ResetSalary.class);
        startActivity(goBack);
    }

    public void weeklyHourlyPay() {

        howMuchYouArePaidPerHour.setText("How much are you paid per hour?");
        howMuchYouArePaidPerHour.setVisibility(View.VISIBLE);
        hourlyPay.setVisibility(View.VISIBLE);
        howOftenYouGetYourSalary.getSelectedView();
        howOftenYouGetYourSalary.setEnabled(false);
        weeklyHourlyPayButton.setVisibility(View.VISIBLE);
        howManyHoursText.setText("How many hours have you worked last week?");
        howManyHoursText.setVisibility(View.VISIBLE);
        howManyHoursInput.setVisibility(View.VISIBLE);

    }

    public void monthlyHourlyPay() {
        howMuchYouArePaidPerHour.setText("How much are you paid per hour?");
        howMuchYouArePaidPerHour.setVisibility(View.VISIBLE);
        hourlyPay.setVisibility(View.VISIBLE);
        howOftenYouGetYourSalary.getSelectedView();
        howOftenYouGetYourSalary.setEnabled(false);
        monthlyHourlyPayButton.setVisibility(View.VISIBLE);
        howManyHoursText.setText("How many hours have you worked last month?");
        howManyHoursText.setVisibility(View.VISIBLE);
        howManyHoursInput.setVisibility(View.VISIBLE);
    }

    public void submitMonthlySalary() {
        monthlySalary = (EditText) findViewById(R.id.monthlySalary);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        monthlySalaryText = monthlySalary.getText().toString();
                        if (monthlySalaryText.equals("") || Integer.parseInt(monthlySalaryText) == 0) {
                            Toast.makeText(getApplicationContext(), "Your salary must be more than 0", Toast.LENGTH_SHORT).show();
                        } else {
                            int salaryNumber = Integer.parseInt(monthlySalaryText);
                            db.openDB();
                            db.addFixedMonthlySalary(salaryNumber);
                            Toast.makeText(getApplicationContext(), "Your salary is " + salaryNumber + " per month", Toast.LENGTH_SHORT).show();
                            goBackToHowMuchYouEarn();
                            db.close();
                        }
                    }
                }
        );


    }

    public void submitWeeklySalary() {
        submitWeeklySalaryButton = (Button) findViewById(R.id.submitWeeklySalaryButton);
        submitWeeklySalaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salaryText = monthlySalary.getText().toString();
                if (salaryText.equals("") || Integer.parseInt(salaryText) == 0) {
                    Toast.makeText(getApplicationContext(), "Your salary must be more than 0", Toast.LENGTH_SHORT).show();
                } else {
                    int salaryNumber = Integer.parseInt(salaryText);
                    db.openDB();
                    db.addFixedWeeklySalary(salaryNumber);
                    Toast.makeText(getApplicationContext(), "Your salary is " + salaryNumber + " per week", Toast.LENGTH_SHORT).show();
                    goBackToHowMuchYouEarn();
                    db.close();
                }
            }});

        }


    public void submitWeeklyHourlyPay() {
        howManyHoursText = (TextView) findViewById(R.id.howManyHoursText);
        howManyHoursInput = (EditText) findViewById(R.id.howManyHoursInput);
        hourlyPay = (EditText) findViewById(R.id.hourlyPay);
        weeklyHourlyPayButton = (Button) findViewById(R.id.submitHourlyWeeklyPay);

        weeklyHourlyPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHourlyPayText=hourlyPay.getText().toString();
                myHoursText=howManyHoursInput.getText().toString();
                if(myHourlyPayText.equals("") || myHoursText.equals("") || Integer.parseInt(myHourlyPayText) == 0 || Integer.parseInt(myHoursText)==0){
                    Toast.makeText(getApplicationContext(), "Your salary must be more than 0", Toast.LENGTH_SHORT).show();
                } else {
                double myHourlyPay = Double.parseDouble(hourlyPay.getText().toString());
                int myHours = Integer.parseInt(howManyHoursInput.getText().toString());
                int weeklySalary = (int) (myHourlyPay * myHours);
                db.openDB();
                db.addWeeklyHourlyPay(myHourlyPay);
                variabledb.addVariableWeeklySalary(weeklySalary);
                    Toast.makeText(getApplicationContext(), "Your salary is " + weeklySalary + " per week", Toast.LENGTH_SHORT).show();
                    goBackToHowMuchYouEarn();
                }


            }
        });
    }

    public void submitMonthlyHourlyPay() {
        hourlyPay = (EditText) findViewById(R.id.hourlyPay);
        monthlyHourlyPayButton = (Button) findViewById(R.id.submitMonthlyWeeklyPay);

        monthlyHourlyPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHourlyPayText=hourlyPay.getText().toString();
                myHoursText=howManyHoursInput.getText().toString();
                if(myHourlyPayText.equals("") || myHoursText.equals("") || Integer.parseInt(myHourlyPayText) == 0 || Integer.parseInt(myHoursText)==0){
                    Toast.makeText(getApplicationContext(), "Your salary must be more than 0", Toast.LENGTH_SHORT).show();
                } else {
                double myHourlyPay = Double.parseDouble(hourlyPay.getText().toString());
                int myHours = Integer.parseInt(howManyHoursInput.getText().toString());
                int monthlySalary = (int) (myHourlyPay * myHours);
                db.openDB();
                db.addMonthlyHourlyPay(myHourlyPay);
                variabledb.addVariableMonthlySalary(monthlySalary);
                    Toast.makeText(getApplicationContext(), "Your salary is " + monthlySalary + " per month", Toast.LENGTH_SHORT).show();
                    goBackToHowMuchYouEarn();
                }
                }

        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_much_you_earn);
        submitMonthlyHourlyPay();
        submitWeeklyHourlyPay();
        submitMonthlySalary();
        submitWeeklySalary();
        typeOfSalary = (Spinner) findViewById(R.id.typeOfSalary);

        ArrayAdapter<String> adapterForTypesOfSalary = new ArrayAdapter<String>(howMuchYouEarn.this, android.R.layout.simple_spinner_item, typesOfSalary);

        adapterForTypesOfSalary.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfSalary.setAdapter(adapterForTypesOfSalary);
        typeOfSalary.setOnItemSelectedListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "howMuchYouEarn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.isavemoney.isavemoney/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "howMuchYouEarn Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.isavemoney.isavemoney/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        howOftenYouGetYourSalary = (Spinner) findViewById(R.id.howOftenYouGetYourSalary);

        switch (i) {
            case 0:
                break;
            case 1:
                howOftenYouGetYourSalary.setVisibility(View.VISIBLE);
                typeOfSalary.getSelectedView();
                typeOfSalary.setEnabled(false);
                ArrayAdapter<String> howOftenAdapter = new ArrayAdapter<String>(howMuchYouEarn.this, android.R.layout.simple_spinner_item, howOftenDoYouGetYourSalary);
                howOftenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                howOftenYouGetYourSalary.setAdapter(howOftenAdapter);

                howOftenYouGetYourSalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        textViewEarnings = (TextView) findViewById(R.id.textViewEarnings);


                        switch (pos) {
                            case 0:
                                break;
                            case 1:
                                textViewEarnings.setText("How much do you earn a week?");
                                textViewEarnings.setVisibility(View.VISIBLE);
                                monthlySalary.setVisibility(View.VISIBLE);
                                howOftenYouGetYourSalary.getSelectedView();
                                howOftenYouGetYourSalary.setEnabled(false);
                                submitWeeklySalaryButton.setVisibility(View.VISIBLE);


                                break;
                            case 2:
                                textViewEarnings.setText("How much do you earn a month?");
                                textViewEarnings.setVisibility(View.VISIBLE);
                                monthlySalary.setVisibility(View.VISIBLE);
                                howOftenYouGetYourSalary.getSelectedView();
                                howOftenYouGetYourSalary.setEnabled(false);
                                submitButton.setVisibility(View.VISIBLE);


                                break;
                        }
                    }


                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                break;

            case 2:
                howMuchYouArePaidPerHour = (TextView) findViewById(R.id.howMuchYouArePaidPerHour);
                howOftenYouGetYourSalary.setVisibility(View.VISIBLE);
                typeOfSalary.getSelectedView();
                typeOfSalary.setEnabled(false);
                ArrayAdapter<String> howOftenAdapter1 = new ArrayAdapter<String>(howMuchYouEarn.this, android.R.layout.simple_spinner_item, howOftenDoYouGetYourSalary);
                howOftenAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                howOftenYouGetYourSalary.setAdapter(howOftenAdapter1);
                howOftenYouGetYourSalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        dayGetPaid = (TextView) findViewById(R.id.dayGetPaid);
                        hourlyPay = (EditText) findViewById(R.id.hourlyPay);
                        weeklyHourlyPayButton = (Button) findViewById(R.id.submitHourlyWeeklyPay);
                        monthlyHourlyPayButton = (Button) findViewById(R.id.submitMonthlyWeeklyPay);

                        switch (pos) {
                            case 0:
                                break;
                            case 1:

                                weeklyHourlyPay();


                                break;
                            case 2:
                                monthlyHourlyPay();
                                break;
                        }


                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
