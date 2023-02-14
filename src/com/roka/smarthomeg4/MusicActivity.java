package com.roka.smarthomeg4;

import com.roka.smarthomeg4.frag.ZAudioFragment;
import com.roka.smarthomeg4.frag.ZaudioRecordFrag;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MusicActivity extends FragmentActivity {

	private ImageView backImageView;
	private FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_music);
		fragmentManager=getSupportFragmentManager();
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.content, new ZAudioFragment(), "zaudio");
		fragmentTransaction.commit();
		backImageView = (ImageView) findViewById(R.id.backImageView);

		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		RadioGroup radioGroup = (RadioGroup) this
				.findViewById(R.id.main_tab_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.main_tab_music:
					FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.content, new ZAudioFragment(), "zaudio");
					fragmentTransaction.commit();
					break;

				case R.id.main_tab_record:
					 fragmentTransaction=fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.content, new ZaudioRecordFrag(), "zaudio");
					fragmentTransaction.commit();
					break;
				default:
					break;
				}
			}
		});

	}
}
