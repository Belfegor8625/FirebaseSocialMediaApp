package com.bartoszlewandowski.firebasesocialmediaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.edtEmail)
	EditText edtEmail;
	@BindView(R.id.edtUsername)
	EditText edtUsername;
	@BindView(R.id.edtPassword)
	EditText edtPassword;

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FirebaseApp.initializeApp(this);
		ButterKnife.bind(this);
		mAuth = FirebaseAuth.getInstance();
	}


	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if (currentUser != null) {

		}
	}


	@OnClick(R.id.btnSignUp)
	public void onClickBtnSingUp() {
		signUp();
	}

	private void signUp() {
		mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							FirebaseDatabase.getInstance().getReference()
									.child("my_users")
									.child(task.getResult().getUser().getUid())
									.child("username").setValue(edtUsername.getText().toString());
							transitionToSocialMediaActivity();
						} else {
							Toast.makeText(MainActivity.this, "Signing up failed",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	@OnClick(R.id.btnSignIn)
	public void onClickBtnSignIn() {
		signIn();
	}

	private void signIn() {
		mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							transitionToSocialMediaActivity();
						} else {

						}

					}
				});
	}

	private void transitionToSocialMediaActivity() {
		Intent intent = new Intent(MainActivity.this, SocialMediaActivity.class);
		startActivity(intent);
	}
}
