package com.vinayrraj.flipdigitexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.vinayrraj.flipdigit.lib.Flipmeter;

public class SampleActivity extends Activity {

	private final int value = 000000;
	private Flipmeter flipMeter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);

		flipMeter = (Flipmeter) findViewById(R.id.Flipmeter);

		final EditText text = (EditText) findViewById(R.id.edittext);
		final View button = findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				try {
					flipMeter.setValue(Integer.parseInt(text.getText().toString().trim()), true);
					text.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		flipMeter.setValue(value, true);
	}

}
