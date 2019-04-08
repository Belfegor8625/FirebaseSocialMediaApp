package com.bartoszlewandowski.firebasesocialmediaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class SocialMediaActivity extends AppCompatActivity {

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_media);
		mAuth = FirebaseAuth.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.logoutItem:
				logout();
				break;
			default:
				break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void logout() {
		mAuth.signOut();
		finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		logout();
	}
}
