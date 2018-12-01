package com.example.laipham.listview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.laipham.listview.model.Contact;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AddContactActivity extends Activity {
    EditText edtName, edtPhone;
    Button bntBack;
    ArrayList<Contact> arrAddContact = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        edtName = (EditText) findViewById(R.id.name);
        edtPhone = (EditText) findViewById(R.id.phone);
        bntBack = (Button) findViewById(R.id.bnt_add_data);
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = edtName.getText().toString();
                String txtPhone = edtPhone.getText().toString();
                Contact contactAdd = new Contact(txtName, txtPhone, Color.GREEN);
                arrAddContact.add(contactAdd);
                Intent in = new Intent();
                in.putExtra("back", arrAddContact);
                setResult(30,in);
                finish();
            }

        });
    }
}
