package com.example.casper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewBookActivity extends AppCompatActivity {

    private Button buttonOK,buttonCancel;
    private EditText editTextBookTitle,editTextBookPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        buttonOK=(Button)findViewById(R.id.button_ok);
        buttonCancel=(Button)findViewById(R.id.button_cancel);
        editTextBookTitle=(EditText)findViewById(R.id.edit_text_book_title);
        editTextBookPrice=(EditText)findViewById(R.id.edit_text_book_price);
    }
}

