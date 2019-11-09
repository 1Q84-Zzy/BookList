package com.example.casper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewBookActivity extends AppCompatActivity {

    private Button buttonOK,buttonCancel;
    private EditText editTextBookTitle;
    private int insertPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        buttonOK=(Button)findViewById(R.id.button_ok);
        buttonCancel=(Button)findViewById(R.id.button_cancel);
        editTextBookTitle=(EditText)findViewById(R.id.edit_text_book_title);


        editTextBookTitle.setText(getIntent().getStringExtra("title"));
        insertPosition=getIntent().getIntExtra("insert_position",0);

        buttonOK.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                      Intent intent = new Intent();
                       intent.putExtra("title",editTextBookTitle.getText().toString());
                      intent.putExtra("insert_position",insertPosition);
                       setResult(RESULT_OK,intent);
                      NewBookActivity.this.finish();
        }
});
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewBookActivity.this.finish();
            }
        });
    }
}