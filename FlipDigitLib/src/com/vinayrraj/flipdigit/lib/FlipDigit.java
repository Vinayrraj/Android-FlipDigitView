package com.vinayrraj.flipdigit.lib;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * @author VinayrajSingh
 * 
 */
class FlipDigit implements AnimationListener {

	private ImageView flipImage_BackUpper = null;
	private ImageView flipImage_BackLower = null;
	private ImageView flipImage_FrontUpper = null;
	private ImageView flipImage_FrontLower = null;

	private Context context;
	private Animation animation1;
	private Animation animation2;
	private int id;
	private OnAnimationComplete onAnimComplete;

	private int animateTo = 0;
	private int animateFrom = 0;

	public interface OnAnimationComplete {
		public void onComplete(int id);
	}

	public FlipDigit(Context act, int id, View view, OnAnimationComplete onAnimComplete) {
		super();
		this.context = act;
		this.id = id;
		this.onAnimComplete = onAnimComplete;

		flipImage_BackUpper = (ImageView) view.findViewById(R.id.FlipMeterSpinner_image_back_upper);
		flipImage_BackLower = (ImageView) view.findViewById(R.id.FlipMeterSpinner_image_back_lower);
		flipImage_FrontUpper = (ImageView) view.findViewById(R.id.FlipMeterSpinner_image_front_upper);
		flipImage_FrontLower = (ImageView) view.findViewById(R.id.FlipMeterSpinner_image_front_lower);

		init();
	}

	public void setDigit(int digit, boolean withAnimation) {

		if (digit < 0)
			digit = 0;
		if (digit > 9)
			digit = 9;

		animateTo = digit;

		if (withAnimation)
			animateDigit(true);
		else
			setDigitImageToAll(digit);
	}

	private void animateDigit(boolean isUpper) {

		animateFrom = getLastDigit(isUpper);
		startAnimation();

	}

	private void init() {

		flipImage_BackUpper.setTag(0);
		flipImage_BackLower.setTag(0);
		flipImage_FrontUpper.setTag(0);
		flipImage_FrontLower.setTag(0);

		animation1 = AnimationUtils.loadAnimation(context, R.anim.flip_point_to_middle);
		animation1.setAnimationListener(this);
		animation2 = AnimationUtils.loadAnimation(context, R.anim.flip_point_from_middle);
		animation2.setAnimationListener(this);

	}

	private void startAnimation() {

		if (animateTo == animateFrom) {
			if (onAnimComplete != null)
				onAnimComplete.onComplete(id);
		} else {
			startDigitAnimation(true);
		}

	}

	private void startDigitAnimation(boolean isUpper) {

		if (isUpper) {
			flipImage_FrontUpper.clearAnimation();
			flipImage_FrontUpper.setAnimation(animation1);
			flipImage_FrontUpper.startAnimation(animation1);

		} else {
			flipImage_FrontLower.clearAnimation();
			flipImage_FrontLower.setAnimation(animation2);
			flipImage_FrontLower.startAnimation(animation2);

		}
	}

	private void incrementFromCode() {
		animateFrom++;
		if (animateFrom < 0)
			animateFrom = 0;

		if (animateFrom > 9)
			animateFrom = 9;

	}

	private int getLastDigit(boolean isUpper) {
		int digit = 0;
		try {
			if (isUpper)
				digit = (Integer) flipImage_FrontUpper.getTag();
			else
				digit = (Integer) flipImage_FrontLower.getTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (digit > 9)
			digit = 0;

		return digit;
	}

	private int getDigitToShow() {
		if (animateFrom + 1 > 9)
			return 0;
		else
			return animateFrom + 1;
	}

	private void setDigitImageToAll(int digit) {
		setDigitImage(digit, true, flipImage_BackUpper);
		setDigitImage(digit, false, flipImage_BackLower);
		setDigitImage(digit, true, flipImage_FrontUpper);
		setDigitImage(digit, false, flipImage_FrontLower);

	}

	private void setDigitImage(int digitToShow, boolean isUpper, ImageView image) {
		image.setTag(digitToShow);
		int resource = 0;
		switch (digitToShow) {
		case 0:
			if (isUpper)
				resource = R.drawable.digit_0_upper;
			else
				resource = R.drawable.digit_0_lower;

			break;

		case 1:
			if (isUpper)
				resource = R.drawable.digit_1_upper;
			else
				resource = R.drawable.digit_1_lower;

			break;

		case 2:
			if (isUpper)
				resource = R.drawable.digit_2_upper;
			else
				resource = R.drawable.digit_2_lower;

			break;

		case 3:
			if (isUpper)
				resource = R.drawable.digit_3_upper;
			else
				resource = R.drawable.digit_3_lower;

			break;

		case 4:
			if (isUpper)
				resource = R.drawable.digit_4_upper;
			else
				resource = R.drawable.digit_4_lower;

			break;

		case 5:
			if (isUpper)
				resource = R.drawable.digit_5_upper;
			else
				resource = R.drawable.digit_5_lower;

			break;

		case 6:
			if (isUpper)
				resource = R.drawable.digit_6_upper;
			else
				resource = R.drawable.digit_6_lower;

			break;

		case 7:
			if (isUpper)
				resource = R.drawable.digit_7_upper;
			else
				resource = R.drawable.digit_7_lower;

			break;

		case 8:
			if (isUpper)
				resource = R.drawable.digit_8_upper;
			else
				resource = R.drawable.digit_8_lower;

			break;

		case 9:
			if (isUpper)
				resource = R.drawable.digit_9_upper;
			else
				resource = R.drawable.digit_9_lower;

			break;

		}

		image.setImageResource(resource);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == animation1) {
			flipImage_FrontUpper.setVisibility(View.INVISIBLE);
			startDigitAnimation(false);

		} else if (animation == animation2) {

			flipImage_FrontUpper.setVisibility(View.VISIBLE);
			setDigitImage(getDigitToShow(), true, flipImage_FrontUpper);
			setDigitImage(getDigitToShow(), false, flipImage_BackLower);
			incrementFromCode();
			animateDigit(false);
		}

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation animation) {

		if (animation == animation1) {

			flipImage_FrontLower.setVisibility(View.INVISIBLE);
			flipImage_FrontUpper.setVisibility(View.VISIBLE);

			setDigitImage(getDigitToShow(), false, flipImage_FrontLower);
			setDigitImage(getDigitToShow(), true, flipImage_BackUpper);

		} else if (animation == animation2) {
			flipImage_FrontLower.setVisibility(View.VISIBLE);
		}

	}

}
