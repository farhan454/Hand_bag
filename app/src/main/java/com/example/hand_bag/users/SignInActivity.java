package com.example.hand_bag.users;

import android.app.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hand_bag.DashBoardActivity;
import com.example.hand_bag.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class SignInActivity extends Activity implements  View.OnClickListener{
    Button linearLayout;
    LinearLayout linearLayouts;
    EditText edt_user_name,edt_psd;
    boolean  ispsdvalid, isnamevalid;
    String password;
    String name;
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
        setContentView(R.layout.activity_sign_in);
        btn=findViewById(R.id.btn);
        edt_user_name=findViewById(R.id.edt_user_name_signin);
        edt_psd=findViewById(R.id.edt_psd_signin);
        fb_layout=findViewById(R.id.fb_layput);
        loginButton = findViewById(R.id.fb);
        txt_signup=findViewById(R.id.txt_signup);
        txt_forget_psd=findViewById(R.id.txt_forget_psd);
        txt_forget_psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,ForgetPasswordActivity.class));
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
        fb_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
         edt_user_name.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
          edt_psd.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        txt_forget_psd=findViewById(R.id.txt_forget_psd);
        btn.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
// Initialize Facebook Login button
        ll_google=findViewById(R.id.ll_gogle);
        ll_google.setOnClickListener(this);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // for the requestIdToken, this is in the values.xml file that
                // is generated from your google-services.json
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();
         mCallbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("@@login", "looged in register");

                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.e("@@login", "looged in cancel");
                Log.d(TAG, "facebook:onCancel");
            }



            @Override
            public void onError(FacebookException error) {
                Log.e("@@login", String.valueOf(error));
            }
        });
// ...







    }
/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                SetValidation();
                break;
            case R.id.txt_forget_psd:
          //      startActivity(new Intent(Sign_in_Activity.this, ForgetPasswordActivity.class));
                break;
            case R.id.ll_gogle:
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }
    private void  SetValidation(){
        password=edt_psd.getText().toString();
        name=edt_user_name.getText().toString();
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
        if (ispsdvalid &&  isnamevalid ) {
          //  Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this, DashBoardActivity.class));
            //  save();
        }
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        Log.e("@@login", "looged in token" + token);


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("@@login", "looged in ");
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Log.e("@@login", "looged in success");
                            Intent intent=new Intent(SignInActivity.this,DashBoardActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignInActivity.this,"success",Toast.LENGTH_SHORT);
                            FirebaseUser user = mAuth.getCurrentUser();
                          //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("@@login", task.getException().toString());
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        //    updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                Log.e("@@loginG", "login success g");
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Log.e("@@loginG", e.toString());
            }
        }else {
            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Log.e("@@loginG", "login complete");
                            Intent intent=new Intent(SignInActivity.this,DashBoardActivity.class);
                            startActivity(intent);
                            //    FirebaseUser user = mAuth.getCurrentUser();
                            //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Log.e("@@loginG", task.getException().toString());

                            //   updateUI(null);
                        }
                    }
                });
    }
}