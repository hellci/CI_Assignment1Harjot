package com.example.admin.ciassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView tvUserID, tvPassword;
    Button btnLogin, btnRegister;
    String userId, password;
    boolean checkIsUserExists;
    boolean checkForEmptyFields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUserID= (TextView) findViewById(R.id.tvUserID);
        tvPassword= (TextView) findViewById(R.id.tvPassword);
        userId=""+ tvUserID.getText().toString();
        password =""+ tvPassword.getText().toString();

        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForEmptyFields=checkForEmptyFields(tvUserID.getText().toString(),tvPassword.getText().toString());
                checkIsUserExists = new Database(LoginActivity.this).isUserExit(tvUserID.getText().toString(),tvPassword.getText().toString());
                if(checkForEmptyFields==false){

                    if(checkIsUserExists==true){
                        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, " Invalid UserID and Password ",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, " Please Fill all fields ",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean checkForEmptyFields(String userId,String password){
        if(TextUtils.isEmpty(userId)||TextUtils.isEmpty(password)){

            checkForEmptyFields=true;
        }
        else {
            checkForEmptyFields=false;
        }
        return checkForEmptyFields;
    }

}
