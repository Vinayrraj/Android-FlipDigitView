package com.vinayrraj.flipdigit.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author VinayrajSingh
 * 
 */
public class FlipmeterSpinner extends RelativeLayout {

	private Context context;
	private View flipMeterSpinner = null;

	private int mCurrentDigit;

	private FlipDigit flipDigit = null;

	/*
	 * Simple constructor used when creating a view from code.
	 */
	public FlipmeterSpinner(Context context) {
		super(context);
		this.context = context;
		initialize();
	}

	/*
	 * This is called when a view is being constructed from an XML file, supplying attributes that were specified in the
	 * XML file.
	 */
	public FlipmeterSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initialize();
	}

	/*
	 * Perform inflation from XML and apply a class-specific base style. This constructor of View allows subclasses to
	 * use their own base style when they are inflating.
	 */
	public FlipmeterSpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initialize();
	}

	private void inflateLayout() {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		flipMeterSpinner = layoutInflater.inflate(R.layout.view_flipmeter_spinner, this);

	}

	public View getFlipmeterSpinner() {
		return flipMeterSpinner;
	}

	public void setDigit(int animateTo, boolean withAnimation) {
		flipDigit.setDigit(animateTo, withAnimation);
	}

	/*
	 * Initialize all of our class members and variables
	 */
	private void initialize() {
		inflateLayout();

		flipDigit = new FlipDigit(context, getId(), flipMeterSpinner, null);

	}

	public int getCurrentDigit() {
		return mCurrentDigit;
	}

}
