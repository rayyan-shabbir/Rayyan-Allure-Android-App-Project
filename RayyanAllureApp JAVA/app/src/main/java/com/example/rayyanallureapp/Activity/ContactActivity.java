package com.example.rayyanallureapp.Activity;

//import static com.example.myapplication.Activity.HomeActivity.ADD_CONTACT_REQUEST_CODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rayyanallureapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ContactModal> arrContacts = new ArrayList<>();
    RecyclerContactAdapter adapter;

    FloatingActionButton btnOpenDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        recyclerView = findViewById(R.id.recyclerContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ContactActivity.this);
                dialog.setContentView(R.layout.activity_add_contact);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the name and number
                        String name = edtName.getText().toString();
                        String number = edtNumber.getText().toString();

                        if (!name.isEmpty() && !number.isEmpty()) {
                            // Create an Intent to send data back to ContactActivity
                            arrContacts.add(new ContactModal(name, number));
                            adapter.notifyItemInserted(arrContacts.size() - 1);
                            recyclerView.scrollToPosition(arrContacts.size() - 1);

                            dialog.dismiss();
                        } else {
                            Toast.makeText(ContactActivity.this, "Name and number fields cannot be empty!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        arrContacts.add(new ContactModal(R.drawable.a, "Muhammad", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.b, "Shabbir", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.c, "Ali", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.d, "Shahmeer", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.e, "Hassan", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.f, "Rayyan", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.g, "RDX", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.h, "Babbar", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.i, "Abbas", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.a, "Muhammad", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.b, "Shabbir", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.c, "Ali", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.d, "Shahmeer", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.e, "Hassan", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.f, "Rayyan", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.g, "RDX", "12345678"));
        arrContacts.add(new ContactModal(R.drawable.h, "Babbar", "12345678"));

        adapter = new RecyclerContactAdapter(this, arrContacts);

        recyclerView.setAdapter(adapter);

    }
}