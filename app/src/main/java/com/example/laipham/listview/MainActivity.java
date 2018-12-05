package com.example.laipham.listview;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    CustomAdapter customAdapper = null;

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
//        Contact contact4 = new Contact("Trương Quang Lâm","0978 102 102", Color.YELLOW);
//        Contact contact5 = new Contact("Võ Duy Tính","01667 333 000", Color.BLACK);
//        Contact contact6 = new Contact("Trần Văn Toàn","08 999 321", Color.BLUE);
//        Contact contact7 = new Contact("Lại Thế Quang","01222 331 331", Color.CYAN);

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
//        arrContact.add(contact4);
//        arrContact.add(contact5);
//        arrContact.add(contact6);
//        arrContact.add(contact7);

        customAdapper= new CustomAdapter(this,R.layout.row_listview,arrContact);
        lvContact.setAdapter(customAdapper);

        //TODO

        bntAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                startActivityForResult(intent,1);
            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showEditDialog(position);
            }
        });
    }
    public void showEditDialog(final int position){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_edit_contact);
//Bind dialog views
        final EditText renameEdittext=(EditText)dialog.findViewById(R.id.rename_edittext);
        final Button renameButton=(Button)dialog.findViewById(R.id.rename_button);
        Button deleteButton=(Button)dialog.findViewById(R.id.delete_button);

        //Set clicked album name to rename edittext
        renameEdittext.setText(arrContact.get(position).getName());

        //When rename button is clicked, first rename edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        renameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!renameEdittext.getText().toString().equals("")) {
                    arrContact.get(position).setName(renameEdittext.getText().toString());
                    //Notify adapter about changing of model list
                    customAdapper.notifyDataSetChanged();
                    //Close dialog
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Can be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //When delete button is clicked, it should be deleted from
        //data list and adapter should be notified that data list change
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrContact.remove(position);
                //Notify adapter about changing of model list
                customAdapper.notifyDataSetChanged();
                //Close dialog
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode==30){
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            Contact contact = new Contact(name,phone, Color.RED);
            arrContact.add(contact);
//            ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("back");
//
//            Log.d(TAG, "Ket qua: "+ data.getStringArrayListExtra("back").toString());
            customAdapper.notifyDataSetChanged();
//            Contact contact = new Contact(data.getIExtra("back",0), data.getIntExtra("back",0), data.getIntExtra("back",0))
//
//            arrContact.add(data.getExtras())
//            Integer d = data.getIntExtra("back",0);
//            result.setText(String.valueOf(d));

        }
    }
}
