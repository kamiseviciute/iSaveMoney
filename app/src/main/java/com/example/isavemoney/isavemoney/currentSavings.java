package com.example.isavemoney.isavemoney;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class currentSavings extends AppCompatActivity {
    private FixedMonthlyDBAdapter database = new FixedMonthlyDBAdapter(this);
    Calendar calendar = Calendar.getInstance();
    SpendingsDBToShow db = new SpendingsDBToShow(this);
    VariableSalaryDBAdapter dbs = new VariableSalaryDBAdapter(this);
    int totalSpendings;
    TextView expenditure;
    TextView salary;
    TextView savings;
    @Override
    @TargetApi(Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_savings);
        expenditure = (TextView) findViewById(R.id.expenditure);
        salary = (TextView) findViewById(R.id.salary);
        savings = (TextView) findViewById(R.id.savings);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1;
        int totalExpenditure = 0;
        if (currentMonth < 10){
        Cursor res = db.getSpendingOnMonth(currentMonth, currentYear);
        if(res.getCount()==0) {

            while (res.moveToNext()) {
                String expenditure = res.getString(2);
                totalExpenditure += Integer.parseInt(expenditure);
            }

    }}else {
            Cursor res = db.getSpendingOnMonth2(currentMonth,currentYear);
            if(res.getCount()==0){
            } while (res.moveToNext()) {
                String expenditure = res.getString(2);
                totalExpenditure+=Integer.parseInt(expenditure);
            }
        }
        if (database.monthlySalaryIsEmpty() && database.weeklySalaryIsEmpty() && database.monthlyHourlyPayIsEmpty() && database.weeklyHourlyPayIsEmpty()){
            savings.setText("You have no savings this month");
            salary.setText("You have not put in your salary!");
            expenditure.setText("You have no expenditures this month");
        }
        else if (!database.monthlySalaryIsEmpty() && (database.weeklySalaryIsEmpty()||database.getLastInsertFromWeeklySalary()==null) && (database.weeklyHourlyPayIsEmpty() || database.getLastInsertFromWeeklyHourlyPay()==null) && (database.monthlyHourlyPayIsEmpty()|| database.getLastInsertFromMonthlyHourlyPay()==null)) {
            int monthlySalary = Integer.parseInt(database.getLastInsertFromMonthlySalary());
            int monthlySavings = monthlySalary - totalExpenditure;
            savings.setText("Your monthly savings: "+Integer.toString(monthlySavings));
            salary.setText("Your salary this month: " + Integer.toString(monthlySalary));
            expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
        }
        else if ((database.monthlySalaryIsEmpty() || database.getLastInsertFromMonthlySalary()==null) && !database.weeklySalaryIsEmpty() && (database.weeklyHourlyPayIsEmpty() || database.getLastInsertFromWeeklyHourlyPay()==null) && (database.monthlyHourlyPayIsEmpty()|| database.getLastInsertFromMonthlyHourlyPay()==null)) {
            int weeklySalary = Integer.parseInt(database.getLastInsertFromWeeklySalary());
            int monthlySalary = (int)(weeklySalary * 52 / 12);
            int monthlySavings = monthlySalary - totalExpenditure;
            savings.setText(Integer.toString(monthlySavings));
            salary.setText("Your salary this month: " + Integer.toString(monthlySalary));
            expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
        }
        else if(!database.monthlySalaryIsEmpty() && !database.weeklySalaryIsEmpty() && !database.weeklyHourlyPayIsEmpty() && (database.monthlyHourlyPayIsEmpty()|| database.getLastInsertFromMonthlyHourlyPay()==null)) {
            int totalSalary = 0;
            if (currentMonth < 10) {
                Cursor cursor = dbs.getCurrentSalary(currentMonth, currentYear);
                if (cursor.getCount() == 0) {
                    savings.setText("You have no savings!");
                    salary.setText("You have not put in your salary!");
                    expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
                }
                while (cursor.moveToNext()) {
                    if (cursor.getString(1) != null) {
                        String monthSalary = cursor.getString(1);
                        totalSalary += Integer.parseInt(monthSalary);
                    }
                }
                int monthlySavings = totalSalary - totalExpenditure;
                savings.setText("Your monthly savings: " + monthlySavings);
                salary.setText("Your monthly salary: " + totalSalary);
                expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));


            } else {
                Cursor cursor = dbs.getCurrentSalaryUpper(currentMonth, currentYear);
                if (cursor.getCount() == 0) {
                    savings.setText("You have no savings!");
                    salary.setText("You have not put in your salary!");
                    expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
                }
                while (cursor.moveToNext()) {
                    if (cursor.getString(1) != null) {
                        String monthSalary = cursor.getString(1);
                        totalSalary += Integer.parseInt(monthSalary);
                    }
                }
                int monthlySavings = totalSalary - totalExpenditure;
                savings.setText("Your monthly savings: " + monthlySavings);
                salary.setText("Your monthly salary: " + totalSalary);
                expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
            }

        }
        else if (!database.monthlySalaryIsEmpty() && !database.weeklySalaryIsEmpty() && (database.weeklyHourlyPayIsEmpty() || database.getLastInsertFromWeeklyHourlyPay()==null) && !database.monthlyHourlyPayIsEmpty()){
           int totalSalary = 0;
            if (currentMonth < 10){
                Cursor cursor = dbs.getCurrentSalary(currentMonth, currentYear);
                if(cursor.getCount()==0) {
                    savings.setText("You have no savings!");
                    salary.setText("You have not put in your salary!");
                    expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
                }
                while (cursor.moveToNext()) {
                    if (cursor.getString(2) != null) {
                        String monthSalary = cursor.getString(2);
                        totalSalary += Integer.parseInt(monthSalary);
                    }
                }
                int monthlySavings = totalSalary - totalExpenditure;
                savings.setText("Your monthly savings are: " + monthlySavings);
                salary.setText("Your monthly salary is: " + totalSalary);
                expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));

            } else {
                Cursor cursor = dbs.getCurrentSalaryUpper(currentMonth,currentYear);
                if(cursor.getCount()==0) {
                    savings.setText("You have no savings!");
                    salary.setText("You have not put in your salary!");
                    expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
                }
                while (cursor.moveToNext()) {
                    if (cursor.getString(2) != null) {
                        String monthSalary = cursor.getString(2);
                        totalSalary += Integer.parseInt(monthSalary);
                    }
                }
                int monthlySavings = totalSalary - totalExpenditure;
                savings.setText("Your savings are: " + Integer.toString(monthlySavings));
                salary.setText("Your monthly salary is: " + totalSalary);
                expenditure.setText("Your total monthly expenditure: " + Integer.toString(totalExpenditure));
            }

        }
        else {

        }
        } }
