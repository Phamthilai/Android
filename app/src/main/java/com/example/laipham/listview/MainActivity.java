package com.example.laipham.listview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.laipham.listview.model.Contact;
import com.example.laipham.listview.adapter.CustomAdapter;
import static android.content.ContentValues.TAG;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    Button bntAdd;
    Button bntRemove;
    ArrayList<Contact> arrContact = new ArrayList<>();

    private String[] number = {"0944684750","01236644421","01699145649","052365787"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntAdd = (Button)findViewById(R.id.bnt_add);
//        lv_contact = (ListView) findViewById(R.id.lv_contact);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,number );
//        lv_contact.setAdapter(arrayAdapter);
//        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,number[position]+"" ,Toast.LENGTH_SHORT).show();
//            }
//        });
        lvContact = (ListView) findViewById(R.id.lv_Contact);

        Contact contact1 = new Contact("Trương Đình Chiến","0988 933 xxx", Color.RED);
        Contact contact2 = new Contact("Võ Văn Tá","01667 585 545", Color.GREEN);
        Contact contact3 = new Contact("Lê Tấn Dũng","0918 033 033", Color.GRAY);
        Contact contact4 = new Contact("Trương Quang Lâm","0978 102 102", Color.YELLOW);
        Contact contact5 = new Contact("Võ Duy Tính","01667 333 000", Color.BLACK);
        Contact contact6 = new Contact("Trần Văn Toàn","08 999 321", Color.BLUE);
        Contact contact7 = new Contact("Lại Thế Quang","01222 331 331", Color.CYAN);

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);
        arrContact.add(contact5);
        arrContact.add(contact6);
        arrContact.add(contact7);

        final CustomAdapter customAdaper = new CustomAdapter(this,R.layout.row_listview,arrContact);
        lvContact.setAdapter(customAdaper);

        //TODO
        customAdaper.notifyDataSetChanged();

        bntAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: "+ data);
//        if(resultCode==30){
//
////            arrContact.add(data.getExtras())
////            Integer d = data.getIntExtra("back",0);
////            result.setText(String.valueOf(d));
//        }
    }
}
