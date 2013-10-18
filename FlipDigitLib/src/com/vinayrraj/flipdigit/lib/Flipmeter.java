package com.vinayrraj.flipdigit.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * @author VinayrajSingh
 * 
 */
public class Flipmeter extends LinearLayout {
	private static final int NUM_DIGITS = 6;

	private int mCurrentValue;
	private int animationCompleteCounter = 0;

	private FlipmeterSpinner[] mDigitSpinners;

	public Flipmeter(Context context) {
		super(context);

		initialize();
	}

	public Flipmeter(Context context, AttributeSet attrs) {
		super(context, attrs);

		initialize();
	}

	private void initialize() {
		mDigitSpinners = new FlipmeterSpinner[NUM_DIGITS];

		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.widget_flipmeter, this, true);

		mDigitSpinners[0] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_1);
		mDigitSpinners[1] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_10);
		mDigitSpinners[2] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_100);
		mDigitSpinners[3] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_1k);
		mDigitSpinners[4] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_10k);
		mDigitSpinners[5] = (FlipmeterSpinner) findViewById(R.id.widget_flipmeter_spinner_100k);

	}

	public void setValue(int value, boolean withAnimation) {

		mCurrentValue = value;
		int tempValue = value;

		for (int i = 5; i > 0; --i) {
			int factor = (int) Math.pow(10, i);

			int digitVal = (int) Math.floor(tempValue / factor);
			tempValue -= (digitVal * factor);
			mDigitSpinners[i].setDigit(digitVal, withAnimation);
			changeAnimationCompleteCounter(withAnimation);
		}

		mDigitSpinners[0].setDigit(tempValue, withAnimation);
		changeAnimationCompleteCounter(withAnimation);

	}

	private synchronized int changeAnimationCompleteCounter(Boolean increment) {
		if (increment == null)
			return animationCompleteCounter;
		else if (increment)
			return ++animationCompleteCounter;
		else
			return --animationCompleteCounter;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return mCurrentValue;
	}

	public interface OnValueChangeListener {
		abstract void onValueChange(Flipmeter sender, int newValue);
	}

}
