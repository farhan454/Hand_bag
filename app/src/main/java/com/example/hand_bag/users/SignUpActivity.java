package com.example.hand_bag.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hand_bag.DashBoardActivity;
import com.example.hand_bag.R;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Set;

public class SignUpActivity extends AppCompatActivity {
    Button linearLayout;
    LinearLayout linearLayouts;
    EditText edt_user_name,edt_psd,edt_email,edt_confirm_psd;
    boolean  ispsdvalid, isnamevalid,ismailvalid,isconfirmpsdvalid;
    String password;
    String name;
    String email;
    String confrm_psd;
    TextView txt_forget_psd;
    LinearLayout ll_google;
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 1001;
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    Button btn;
    TextView txt_signup;
    private FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    LoginButton loginButton;
    LinearLayout fb_layout;
    TextView txt_forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn=findViewById(R.id.btn);
        edt_user_name=findViewById(R.id.edt_user_name_signup);
        edt_confirm_psd=findViewById(R.id.edt_cfrn_psd_signup);
        edt_email=findViewById(R.id.edt_email_signup);
        edt_psd=findViewById(R.id.edt_psd_signup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetValidation();
            }
        });

    }
    private void  SetValidation(){
        password=edt_psd.getText().toString();
        name=edt_user_name.getText().toString();
        confrm_psd=edt_confirm_psd.getText().toString();
        email=edt_email.getText().toString();
        if (TextUtils.isEmpty(name)) {
            edt_user_name.setError("Empty field");
            isnamevalid= false;
        } else {
            isnamevalid = true;
        }

        if (TextUtils.isEmpty(password)) {
            edt_psd.setError("Empty field");
            ispsdvalid= false;
        } else {
            ispsdvalid = true;
        }
        if (!confrm_psd.equalsIgnoreCase(password)) {
            edt_confirm_psd.setError("not match");
            isconfirmpsdvalid= false;
        }
        if (TextUtils.isEmpty(confrm_psd)) {
            edt_confirm_psd.setError("Empty field");
            isconfirmpsdvalid= false;
        } else {
            isconfirmpsdvalid= true;
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email)) {
            edt_email.setError("empty fielf");
            ismailvalid = false;
        }
        if (!email.matches(emailPattern)){
            edt_email.setError("invalid email");
            ismailvalid=false;
        }
        else {
            ismailvalid = true;
        }
        if (ispsdvalid &&  isnamevalid && ismailvalid && isconfirmpsdvalid) {
            //  Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUpActivity.this, DashBoardActivity.class));
            //  save()
        }
    }
}