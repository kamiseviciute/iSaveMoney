package com.example.isavemoney.isavemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class howMuchYouSpend extends AppCompatActivity {
    SpendingsDB spendings = new SpendingsDB(this);
    SpendingsDBToShow spendingsToShow = new SpendingsDBToShow(this);
    String[] dropDownMenu = {"Rent", "Bills Utilities", "Traveling", "Clothing", "Hobbies", "Personal Care", "Food Dining", "Entertainment", "Other"};
    Spinner spinnerSpendings;
    Button howMuchYouSpendButton;
    EditText amountSpent;
    //CHECK HOW TO PRINT OUT A TABLE WITH DATES AND SPENDINGS BEING SHOWED. A PERSON SHOULD BE ABLE TO DELETE THEM. REFER PERHAPS TO THE TUTORIAL THAT SHOWED HOW TO BUILD UP A DATABASE
    // INT THE FIRST PLACE. CHECK HOW TO SHOW DATE ON TOASTER LIKE ''YOU HAVE SPENT BLA BLA BLA on 11/06/2016. STUFF LIKE THAT. A TABLE SHOULD ALSO SHOW THIS MONTHS SPENDINGS AND LAST MONTHS SPENDINGS. NO MORE I GUESS
    public void showExpenditure(View view){
        Intent savingsIntent = new Intent(this, showSpendings.class);
        startActivity(savingsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_much_you_spend);
        amountSpent = (EditText) findViewById(R.id.amountSpent);
        howMuchYouSpendButton = (Button) findViewById(R.id.submitSpendingsButton);
        ArrayAdapter<String> adapterForSpendingsMenu = new ArrayAdapter<String>(howMuchYouSpend.this, android.R.layout.simple_spinner_item, dropDownMenu);
        adapterForSpendingsMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpendings = (Spinner) findViewById(R.id.spinnerSpendings);
        spinnerSpendings.setAdapter(adapterForSpendingsMenu);
        spinnerSpendings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int rentPrice = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addRent("Rent", rentPrice);
                                spendings.openDB();
                                spendings.addRent(rentPrice);
                                if (rentPrice > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +rentPrice+ " on rent!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }


                            }
                        });
                        break;
                    case 1:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int billsPrice = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addBills("Bills Utilities", billsPrice);
                                spendings.openDB();
                                spendings.addBills(billsPrice);
                                if (billsPrice > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +billsPrice+ " on Bills & Utilities!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        break;
                    case 2:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view){
                            int travelingCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addTraveling("Traveling", travelingCosts);
                            spendings.openDB();
                            spendings.addTraveling(travelingCosts);
                            if (travelingCosts > 0){
                                Toast.makeText(getApplicationContext(),"You have spent " +travelingCosts+ " for traveling!",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                            } }
                        });

                        break;
                    case 3:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    int clothingCosts = Integer.parseInt(amountSpent.getText().toString());
                                    spendingsToShow.openDB();
                                    spendingsToShow.addClothing("Clothing", clothingCosts);
                                    spendings.openDB();
                                    spendings.addClothing(clothingCosts);
                                    if (clothingCosts > 0){
                                        Toast.makeText(getApplicationContext(),"You have spent " +clothingCosts+ " on clothing!",Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                    }
                                }

                        });
                        break;
                    case 4:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int hobbyCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addHobbies("Hobbies", hobbyCosts);
                                spendings.openDB();
                                spendings.addHobbies(hobbyCosts);
                                if (hobbyCosts > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +hobbyCosts+ " on your hobbies!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                        break;
                    case 5:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int personalCareCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addPersonalCare("Personal Care", personalCareCosts);
                                spendings.openDB();
                                spendings.addPersonalCare(personalCareCosts);
                                if (personalCareCosts > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +personalCareCosts+ " for your personal care!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }



                            }
                        });
                        break;
                    case 6:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int foodCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addFoodDining("Food & Dining", foodCosts);
                                spendings.openDB();
                                spendings.addFoodDining(foodCosts);
                                if (foodCosts> 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +foodCosts+ " on your hobbies!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }


                            }
                        });
                        break;
                    case 7:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int entertainmentCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addEntertainment("Entertainment", entertainmentCosts);
                                spendings.openDB();
                                spendings.addEntertainment(entertainmentCosts);
                                if (entertainmentCosts > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +entertainmentCosts+ " on entertainment!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }


                            }
                        });
                        break;
                    case 8:
                        howMuchYouSpendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int otherCosts = Integer.parseInt(amountSpent.getText().toString());
                                spendingsToShow.openDB();
                                spendingsToShow.addOther("Other", otherCosts);
                                spendings.openDB();
                                spendings.addOther(otherCosts);
                                if (otherCosts > 0){
                                    Toast.makeText(getApplicationContext(),"You have spent " +otherCosts+ " on your hobbies!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "You have to spend more than 0!", Toast.LENGTH_LONG).show();
                                }


                            }
                        });
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
}