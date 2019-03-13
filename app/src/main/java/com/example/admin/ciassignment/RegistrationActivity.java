package com.example.admin.ciassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class RegistrationActivity extends AppCompatActivity {
    TextView tvName,tvPassword,tvConfirmPassword,tvLogin;
    Button btnSave;
    String userId, password,confirmPassword;
    boolean checkForEmptyFields;
    Database db=new Database(RegistrationActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tvName= (TextView) findViewById(R.id.tvName);
        tvPassword= (TextView) findViewById(R.id.tvPassword);
        tvConfirmPassword= (TextView) findViewById(R.id.tvConfirm);
        tvLogin= (TextView) findViewById(R.id.tvLogin);
        userId=""+ tvName.getText();
        password =""+ tvPassword.getText();
        confirmPassword=""+ tvConfirmPassword.getText();
        btnSave= (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForEmptyFields= checkForEmptyFields(tvName.getText().toString(),tvPassword.getText().toString(), tvConfirmPassword.getText().toString());
                if(checkForEmptyFields==false){
                    if(tvPassword.getText().toString().equals(tvConfirmPassword.getText().toString())){
                        db.insertUserData(tvName.getText().toString(),tvPassword.getText().toString());
                        Toast.makeText(RegistrationActivity.this, " Registration Successful ",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, " Password not matched with comfirm password ",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public boolean checkForEmptyFields(String userId,String password,String confirmPassword){
        if(TextUtils.isEmpty(userId)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirmPassword)){

            checkForEmptyFields=true;
            Toast.makeText(RegistrationActivity.this, " All fields are required ",Toast.LENGTH_SHORT).show();

        }
        else {
            checkForEmptyFields=false;
        }
        return checkForEmptyFields;
    }


}
