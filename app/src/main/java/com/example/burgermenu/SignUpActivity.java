package com.example.burgermenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import classes.Person;

public class SignUpActivity extends AppCompatActivity {
    EditText name, username, password;
    FirebaseDatabase db;
    DatabaseReference people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        db = FirebaseDatabase.getInstance("https://burgermenu-3efc4-default-rtdb.firebaseio.com/");
        people = db.getReference().child("People");
    }

    public void btnSignUpClicked(View v) {
        String name = this.name.getText().toString();
        String username = this.username.getText().toString();
        String passwd = this.password.getText().toString();
//Валидация
        boolean flag = true;
        if (name.isEmpty()) {
            this.name.setError("Enter name");
            flag = false;
        }
        if (username.isEmpty()) {
            this.username.setError("Enter username");
            flag = false;
        }
        if (passwd.isEmpty()) {
            this.password.setError("Enter password");
        }
        if (!flag) {
            return;
        }

        Query query = people.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    Toast.makeText(getApplicationContext(),
                            "This username is busy already",
                            Toast.LENGTH_LONG).show();
                } else {
                    Person p = new Person(name.trim(), username.trim(), passwd);

                    DatabaseReference push = people.push();
                    String key = push.getKey();
                    Toast.makeText(getApplicationContext(),
                            "Success", Toast.LENGTH_LONG).show();
                    p.setKey(key);
                    push.setValue(p);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}