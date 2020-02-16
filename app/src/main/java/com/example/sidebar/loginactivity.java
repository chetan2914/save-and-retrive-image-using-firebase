package com.example.sidebar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";
    private EditText id,password;
    private Button loginbtn;
    private TextView register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        id =findViewById(R.id.userid);
        password = findViewById(R.id.userpassword);
        loginbtn =findViewById(R.id.loginbutton);
        register=findViewById(R.id.register);
        loginbtn.setOnClickListener(this);
        register.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void signin(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginactivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    public void  updateUI(FirebaseUser currentUser){
        if(currentUser != null){
            startActivity(new Intent(this,MainActivity.class));
            Toast.makeText(this,"U Signed In successfully"+currentUser.getEmail(),Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }
    public void signout()
    {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbutton:
                if(id==null && password==null) {
                    startActivity(new Intent(this,loginactivity.class));
                    Toast.makeText(this,"enter the required fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String idd=id.getText().toString();
                    String pass=password.getText().toString();
                    signin(idd,pass);
                }
                /*startActivity(new Intent(this,mainpage.class));*/
                break;
            case R.id.register:
                //using this IP for Genymotion emulator
                Toast.makeText(loginactivity.this, "has to be built", Toast.LENGTH_LONG).show();


        }
    }
}
