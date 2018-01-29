package com.example.android.bookfindr.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.android.bookfindr.R;

import butterknife.OnClick;

import static com.example.android.bookfindr.view.ResultsFragment.USER_INPUT;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_container, new SplashScreenFragment(), "splash");
		transaction.commit();
	}

	@OnClick(R.id.search_image_button)
	public void initializeSearch(View view) {
		Fragment resultsFragment = new ResultsFragment();
		Bundle args = new Bundle();
		args.putString(USER_INPUT, getUserInput());
		resultsFragment.setArguments(args);

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, resultsFragment, "results");
		transaction.addToBackStack("splash");
		transaction.commit();
	}

	private String getUserInput() {
		EditText userInput = findViewById(R.id.user_input_edit_text);
		return userInput.getText().toString();
	}
}
