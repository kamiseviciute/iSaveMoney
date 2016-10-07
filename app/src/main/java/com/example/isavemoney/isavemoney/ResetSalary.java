package com.example.isavemoney.isavemoney;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ResetSalary extends AppCompatActivity {
    private FixedMonthlyDBAdapter db = new FixedMonthlyDBAdapter(this);
    TextView messageAboutSalary;
    Button resetButton;
    TextView lastGivenEarnings;
    Button updateHours;
    TextView newWeekHours;
    private VariableSalaryDBAdapter variabledb = new VariableSalaryDBAdapter(this);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void goToHome(View view){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_salary);
        messageAboutSalary = (TextView) findViewById(R.id.messageAboutSalary);
        resetButton = (Button) findViewById(R.id.resetButton);
        lastGivenEarnings = (TextView) findViewById(R.id.lastGivenEarnings);
        updateHours = (Button) findViewById(R.id.updateHours);
        newWeekHours = (TextView) findViewById(R.id.newWeekHours);
        if (db.monthlySalaryIsEmpty() && db.weeklySalaryIsEmpty() && db.monthlyHourlyPayIsEmpty() && db.weeklyHourlyPayIsEmpty()) {
            messageAboutSalary.setText("You have not given your salary details");
            resetButton.setText("ADD MY EARNINGS");
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });

        } else if (!db.monthlySalaryIsEmpty() && (db.weeklySalaryIsEmpty()||db.getLastInsertFromWeeklySalary()==null) && (db.weeklyHourlyPayIsEmpty() || db.getLastInsertFromWeeklyHourlyPay()==null) && (db.monthlyHourlyPayIsEmpty()|| db.getLastInsertFromMonthlyHourlyPay()==null)){
            messageAboutSalary.setText("Your monthly salary is: " + db.getLastInsertFromMonthlySalary());
            resetButton.setText("CHANGE MY SALARY");
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });
        } else if ((db.monthlySalaryIsEmpty() || db.getLastInsertFromMonthlySalary()==null) && !db.weeklySalaryIsEmpty() && (db.weeklyHourlyPayIsEmpty() || db.getLastInsertFromWeeklyHourlyPay()==null) && (db.monthlyHourlyPayIsEmpty()|| db.getLastInsertFromMonthlyHourlyPay()==null) ) {
            messageAboutSalary.setText("Your weekly salary is: " + db.getLastInsertFromWeeklySalary());
            resetButton.setText("CHANGE MY SALARY");
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });

        } else if((db.monthlySalaryIsEmpty() || db.getLastInsertFromMonthlySalary()==null) && (db.weeklySalaryIsEmpty() || db.getLastInsertFromWeeklySalary() ==null) && !db.weeklyHourlyPayIsEmpty() && (db.monthlyHourlyPayIsEmpty()|| db.getLastInsertFromMonthlyHourlyPay()==null)) {
            messageAboutSalary.setText("Your hourly pay is " + db.getLastInsertFromWeeklyHourlyPay());
            resetButton.setText("CHANGE MY SALARY");
            lastGivenEarnings.setText("Your last week's salary is " + variabledb.getLastInsertFromWeeklyVariableSalary() );
            lastGivenEarnings.setVisibility(View.VISIBLE);
            newWeekHours.setVisibility(View.VISIBLE);
            updateHours.setVisibility(View.VISIBLE);

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });
            updateHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent updateHoursWorked = new Intent(view.getContext(), updateWorkedHours.class);
                    startActivity(updateHoursWorked);
                }
            });
        } else if ((db.monthlySalaryIsEmpty() || db.getLastInsertFromMonthlySalary()==null) && (db.weeklySalaryIsEmpty() || db.getLastInsertFromWeeklySalary() ==null)  && (db.weeklyHourlyPayIsEmpty() || db.getLastInsertFromWeeklyHourlyPay()==null) && !db.monthlyHourlyPayIsEmpty()){
            messageAboutSalary.setText("Your hourly pay is " + db.getLastInsertFromMonthlyHourlyPay());

            resetButton.setText("CHANGE MY SALARY");
            lastGivenEarnings.setText("Your last month's salary is " + variabledb.getLastInsertFromMonthlyVariableSalary() );
            lastGivenEarnings.setVisibility(View.VISIBLE);
            newWeekHours.setText("Is it a new month? Would you like to put in your new hours worked?");
            newWeekHours.setVisibility(View.VISIBLE);
            updateHours.setVisibility(View.VISIBLE);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });
            updateHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent updateHoursWorked = new Intent(view.getContext(), updateWorkedHours.class);
                    startActivity(updateHoursWorked);
                }
            });
        } else {
            messageAboutSalary.setText("Something went wrong!");
            resetButton.setText("CHANGE MY SALARY");
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent howMuchYouEarnIntent = new Intent(v.getContext(), howMuchYouEarn.class);
                    startActivity(howMuchYouEarnIntent);
                }
            });
        }

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
                "ResetSalary Page", // TODO: Define a title for the content shown.
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
                "ResetSalary Page", // TODO: Define a title for the content shown.
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
}
