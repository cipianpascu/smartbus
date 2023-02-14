package com.roka.smarthomeg4;

import java.util.ArrayList;
import java.util.HashMap;

import com.roka.smarthomeg4.business.Logo;
import com.roka.smarthomeg4.database.Zaudio_DB;
import com.roka.smarthomeg4.database.dbconnection.LogoDB;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SplashActivity extends Activity implements Runnable {

	

	private static final byte CONST_MSG_TYPE_REFRESH_LIGHT = 1;
	private ImageView gifView;// gif
	private ImageView logoView;
	Handler myHandlerLight = new Handler() {
		
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case CONST_MSG_TYPE_REFRESH_LIGHT:
				ArrayList<Logo> arrLogos=new LogoDB(SplashActivity.this).getZoneImages();
				if(arrLogos!=null){
					for (Logo logo : arrLogos) {
						My_app.hasmap.put(logo.getLogoID(), logo);
					}
				}
				new Zaudio_DB(SplashActivity.this).getWritableDatabase();
				startActivity(new Intent(getApplication(), MainActivity.class));
				finish();	

			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		initAmimationOpen();

	
		myHandlerLight.postDelayed(this, 3000);

	}

	// init open animation gif
	public void initAmimationOpen() {
		gifView = (ImageView) findViewById(R.id.imageViewname);
		logoView = (ImageView) findViewById(R.id.activity_logo_gif);

		final Animation logoAnim = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.activity_logo);
		logoView.startAnimation(logoAnim);

		logoAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				final AnimationDrawable anim = (AnimationDrawable) getResources()
						.getDrawable(R.anim.activity_line);
				gifView.setBackgroundDrawable(anim);
				gifView.getViewTreeObserver().addOnPreDrawListener(
						new OnPreDrawListener() {

							@Override
							public boolean onPreDraw() {
								// TODO Auto-generated method stub
								anim.start();
								return true;//
							}
						});
			}
		});
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent motionevent) {
//		exec();
//		return true;
//	}

	public void exec() {
		ArrayList<Logo> arrLogos=new LogoDB(SplashActivity.this).getZoneImages();
		My_app.hasmap=new HashMap<Integer, Logo>();
		if(arrLogos!=null){
			for (Logo logo : arrLogos) {
				My_app.hasmap.put(logo.getLogoID(), logo);
			}
		}
		
		startActivity(new Intent(getApplication(), MainActivity.class));
		finish();
	}

	@Override
	public void run() {

		exec();
	}

}
