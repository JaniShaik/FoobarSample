package com.foobar.gireesam.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.foobar.gireesam.R;
import com.foobar.gireesam.utils.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    // Tag
    private String TAG = "LoginActivity";
    // Context
    private Context mContext = this;
    // Facebook Auth
    private RelativeLayout rlFb;
    private CallbackManager mCallbackManager;
    // Google Auth
    private RelativeLayout rlGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private int GOOGLE_SIGN_IN = 1;
    // Firebase Auth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private AccessTokenTracker mAccessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);
        // Initialization
        initialization();
        // Facebook
        FacebookSdk.sdkInitialize(mContext);
        mCallbackManager = CallbackManager.Factory.create();

        // Google SignIn Configuration
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso);

    }

    /**
     * Initialize views
     */
    private void initialization() {
        rlFb = findViewById(R.id.rlFb);
        rlFb.setOnClickListener(this);

        rlGoogle = findViewById(R.id.rlGoogle);
        rlGoogle.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    updateUIFromFB(user);
                } else {
                    updateUIFromFB(null);
                }
            }
        };

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    mAuth.signOut();
                }
            }
        };
    }



    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlFb:
                //goToNext("Bar");
                facebookSignIn();
                break;
            case R.id.rlGoogle:
                //goToNext("Restaurant");
                googleSignIn();
                //mAuth.signOut();
                break;
        }
    }

    private void goToNext(String type) {
        // Intent intent = new Intent(mContext, HomeActivity.class);
        Intent intent = new Intent(mContext, RecentCheckInActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    /**
     * Google SignIn
     */
    private void googleSignIn() {
        Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(googleSignInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseGoogleAuth(account);
            Toast.makeText(mContext, "Signed in successfully..!", Toast.LENGTH_SHORT).show();
            goToNext("Restaurant");
        } catch (ApiException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            firebaseGoogleAuth(null);
            Toast.makeText(mContext, "Sign in failed..!", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseGoogleAuth(GoogleSignInAccount account) {
        if (account != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUIFromGoogleLogin(user);
                    } else {
                        Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
                        updateUIFromGoogleLogin(null);
                    }
                }
            });
        }
    }

    private void updateUIFromGoogleLogin(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(mContext);
        if (account != null) {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Toast.makeText(mContext, personName + personEmail, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Facebook SignIn
     */
    private void facebookSignIn() {

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
        //
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "User ID: " +
                        loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken());
                Toast.makeText(mContext, "User ID: " +
                        loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
                handleFirebaseToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "Error " + error);
            }
        });
    }

    private void handleFirebaseToken(AccessToken token) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(mContext, "FB sign in is successfull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUIFromFB(user);
                } else {
                    Toast.makeText(mContext, "FB sign in failed", Toast.LENGTH_SHORT).show();
                    updateUIFromFB(null);
                }
            }
        });
    }

    private void updateUIFromFB(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(mContext, user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * For sign out
     */
    private void signOut() {
        mGoogleSignInClient.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }
}
