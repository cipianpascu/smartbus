package com.roka.smarthomeg4.frag;

import java.util.ArrayList;
import java.util.List;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.Zones;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MediaFragment extends Fragment implements OnClickListener,
		OnPageChangeListener {

	private Zones zones;
	private FragmentManager fm;
	private ImageView tvImageView, appelImageView, dvdImageView,
			projectorImageView, satImageView;
	private Context context;

	private ViewPager viewPager;

	private TVFragment tvFragment;
	private DVDFragment dvdFragment;
	private SATFragment satFragment;
	private AppleTVFragment appleTVFragment;
	private ProjectorFragment projectorFragment;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private ArrayList<ImageView> images=new ArrayList<ImageView>();
	private TextView zoneNameTextView;
	private boolean tv = true, dvd = true, sat = true, appel = true,
			projector = true;
	private FragmentManager fragmentManager;

	public MediaFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	public MediaFragment() {
		// TODO Auto-generated constructor stub
	}

	public MediaFragment(Zones zones, boolean tv, boolean dvd, boolean sat,
			boolean appel, boolean projector) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
		this.tv = tv;
		this.dvd = dvd;
		this.sat = sat;
		this.appel = appel;
		this.projector = projector;

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = activity;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.media_fragments, container, false);
		 zoneNameTextView = (TextView) view.findViewById(R.id.zoneNametext);
		
		tvImageView = (ImageView) view.findViewById(R.id.tvImageView);
		appelImageView = (ImageView) view.findViewById(R.id.appelImageView);
		dvdImageView = (ImageView) view.findViewById(R.id.dvdImageView);
		projectorImageView = (ImageView) view
				.findViewById(R.id.projectorImageView);
		satImageView = (ImageView) view.findViewById(R.id.satImageView);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);

		tvImageView.setOnClickListener(this);
		appelImageView.setOnClickListener(this);
		dvdImageView.setOnClickListener(this);
		projectorImageView.setOnClickListener(this);
		satImageView.setOnClickListener(this);

		tvImageView.setBackground(context.getResources().getDrawable(
				R.drawable.media_a));
		appelImageView.setBackground(context.getResources().getDrawable(
				R.drawable.media_b));
		dvdImageView.setBackground(context.getResources().getDrawable(
				R.drawable.media_c));
		projectorImageView.setBackground(context.getResources().getDrawable(
				R.drawable.media_d));
		satImageView.setBackground(context.getResources().getDrawable(
				R.drawable.media_e));
		ImageView backImageView = (ImageView) view.findViewById(R.id.backImageView);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		outState.putSerializable("zone", zones);
		outState.putBoolean("tv", tv);
		outState.putBoolean("dvd", dvd);
		outState.putBoolean("sat", sat);
		outState.putBoolean("projector", projector);
		outState.putBoolean("appel", appel);
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
			tv=savedInstanceState.getBoolean("tv");
			dvd=savedInstanceState.getBoolean("dvd");
			sat=savedInstanceState.getBoolean("sat");
			appel=savedInstanceState.getBoolean("appel");
			projector=savedInstanceState.getBoolean("projector");
		}
		
		zoneNameTextView.setText(zones.getZoneName());

		if (tv) {
			tvFragment = new TVFragment(zones);
		}
		if (appel) {
			appleTVFragment = new AppleTVFragment(zones);
		}
		if (dvd) {
			dvdFragment = new DVDFragment(zones);
		}
		if (projector) {
			projectorFragment = new ProjectorFragment(zones);
		}
		if (sat) {
			satFragment = new SATFragment(zones);
		}
		
		
		
		

		if (!tv) {
			tvImageView.setVisibility(View.GONE);
		} else {
			fragments.add(tvFragment);
			images.add(tvImageView);

		}
		
		

		if (!appel) {
			appelImageView.setVisibility(View.GONE);
		} else {
			fragments.add(appleTVFragment);
			images.add(appelImageView);
			}

		if (!dvd) {
			dvdImageView.setVisibility(View.GONE);
		} else {

			fragments.add(dvdFragment);
			images.add(dvdImageView);

		}

		if (!projector) {
			projectorImageView.setVisibility(View.GONE);
		} else {
			fragments.add(projectorFragment);
			images.add(projectorImageView);
		}

		if (!sat) {
			satImageView.setVisibility(View.GONE);
		} else {

			fragments.add(satFragment);
			images.add(satImageView);
		}

		fragmentManager = ((FragmentActivity) getActivity())
				.getSupportFragmentManager();

		MyPageAdapter myPageAdapter = new MyPageAdapter(
				getChildFragmentManager(), fragments);
		viewPager.setAdapter(myPageAdapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(0);

	}

	public class MyPageAdapter extends FragmentStatePagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.tvImageView:

			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_a));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_b));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_c));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}

			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

			int index=fragments.indexOf(tvFragment);
			viewPager.setCurrentItem(index);
			// FragmentTransaction fragmentTransaction=fm.beginTransaction();
			// fragmentTransaction.replace(R.id.mediaLinearLayout, new
			// TVFragment(zones));
			// fragmentTransaction.commit();
			// fm.executePendingTransactions();
			break;

		case R.id.appelImageView:
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_g));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_c));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}

			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

			 index=fragments.indexOf(appleTVFragment);
			viewPager.setCurrentItem(index);
			// fragmentTransaction=fm.beginTransaction();
			// fragmentTransaction.replace(R.id.mediaLinearLayout, new
			// AppleTVFragment(zones));
			// fragmentTransaction.commit();
			// fm.executePendingTransactions();
			break;

		case R.id.dvdImageView:
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_i));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			index=fragments.indexOf(dvdFragment);
			viewPager.setCurrentItem(index);
			// fragmentTransaction=fm.beginTransaction();
			// fragmentTransaction.replace(R.id.mediaLinearLayout, new
			// DVDFragment(zones));
			// fragmentTransaction.commit();
			// fm.executePendingTransactions();
			break;

		case R.id.projectorImageView:

			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_k));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_j));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			index=fragments.indexOf(projectorFragment);
			viewPager.setCurrentItem(index);
			
			// fragmentTransaction=fm.beginTransaction();
			// fragmentTransaction.replace(R.id.mediaLinearLayout, new
			// ProjectorFragment(zones));
			// fragmentTransaction.commit();
			// fm.executePendingTransactions();
			break;

		case R.id.satImageView:
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_k));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_m));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_l));
			}
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			index=fragments.indexOf(satFragment);
			viewPager.setCurrentItem(index);
			// fragmentTransaction=fm.beginTransaction();
			// fragmentTransaction.replace(R.id.mediaLinearLayout, new
			// SATFragment(zones));
			// fragmentTransaction.commit();
			// fm.executePendingTransactions();
			break;
		default:
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(arg0);
		
		Fragment f=fragments.get(arg0);
		if(f instanceof TVFragment){
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_a));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_b));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_c));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}

			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

		}else if(f instanceof AppleTVFragment)
		{
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_g));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_c));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}

			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

		}else if(f instanceof DVDFragment){
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_i));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_d));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
		}else if(f instanceof ProjectorFragment ){
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_k));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_j));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_e));
			}
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
		}else if(f instanceof SATFragment ){
			if (tv) {
				tvImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_f));
			}
			if (appel) {
				appelImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_h));
			}
			if (dvd) {
				dvdImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_k));
			}
			if (projector) {
				projectorImageView.setBackground(context.getResources()
						.getDrawable(R.drawable.media_m));
			}
			if (sat) {
				satImageView.setBackground(context.getResources().getDrawable(
						R.drawable.media_l));
			}
			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_media_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
		}
//		
//		switch (arg0) {
//		case 0:
//
//			tvImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_a));
//			appelImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_b));
//			dvdImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_c));
//			projectorImageView.setBackground(context.getResources()
//					.getDrawable(R.drawable.media_d));
//			satImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_e));
//
//			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//
//			break;
//
//		case 1:
//			tvImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_f));
//			appelImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_g));
//			dvdImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_c));
//			projectorImageView.setBackground(context.getResources()
//					.getDrawable(R.drawable.media_d));
//			satImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_e));
//
//			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			break;
//
//		case 2:
//			tvImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_f));
//			appelImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_h));
//			dvdImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_i));
//			projectorImageView.setBackground(context.getResources()
//					.getDrawable(R.drawable.media_d));
//			satImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_e));
//
//			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			break;
//
//		case 3:
//			tvImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_f));
//			appelImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_h));
//			dvdImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_k));
//			projectorImageView.setBackground(context.getResources()
//					.getDrawable(R.drawable.media_j));
//			satImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_e));
//
//			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			break;
//
//		case 4:
//			tvImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_f));
//			appelImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_h));
//			dvdImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_k));
//			projectorImageView.setBackground(context.getResources()
//					.getDrawable(R.drawable.media_m));
//			satImageView.setBackground(context.getResources().getDrawable(
//					R.drawable.media_l));
//
//			satImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			tvImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			appelImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			dvdImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			projectorImageView.setLayoutParams(new LinearLayout.LayoutParams(
//					(int) getResources().getDimension(
//							R.dimen.un_selected_image_media_width),
//					(int) getResources().getDimension(
//							R.dimen.image_z_audio_high)));
//			break;
//
//		default:
//			break;
//		}
	}

}
