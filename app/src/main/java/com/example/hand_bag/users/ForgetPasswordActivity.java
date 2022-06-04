package com.example.hand_bag.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hand_bag.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText edt_forget_email;
    boolean  isemailvalid;
    Button forget_rl;
    ImageView img_back;
    String email;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        edt_forget_email=findViewById(R.id.edt_email_signup);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetValidation();
            }
        });
    }
    private void  SetValidation(){
        email=edt_forget_email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email)) {
            edt_forget_email.setError("empty fielf");
            isemailvalid = false;
        }
        if (!email.matches(emailPattern)){
            edt_forget_email.setError("invalid email");
            isemailvalid=false;
        }
        else {
            isemailvalid = true;
        }
        if (isemailvalid ) {
            Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();

            //  save();
        }
    }
}