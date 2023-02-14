package com.roka.smarthomeg4;

import java.util.ArrayList;

import com.roka.smarthomeg4.business.SystemInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.SystemInZoneDB;
import com.roka.smarthomeg4.frag.FanFragment;
import com.roka.smarthomeg4.frag.HVACFragment;
import com.roka.smarthomeg4.frag.LightFragment;
import com.roka.smarthomeg4.frag.MediaFragment;
import com.roka.smarthomeg4.frag.MoodsFragment;
import com.roka.smarthomeg4.frag.ShadesFragment;
import com.roka.smarthomeg4.frag.ZAudioFragment;
import com.roka.smarthomeg4.udp_socket.SmartSocketConnection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class MainZoneDetailsActivity extends FragmentActivity implements
		OnTabChangeListener, OnPageChangeListener {

	private static String ligth_tab = "ligth_tab";
	private static String hvac_tab = "hvac_tab";
	private static String zAudio_tab = "zAudio_tab";
	private static String shades_tab = "shades_tab";
	// private static String tv_tab = "tv_tab";
	// private static String dvd_tab = "dvd_tab";
	// private static String sat_tab = "sat_tab";
	// private static String appleTV_tab = "appleTV_tab";
	// private static String projector_tab = "projector_tab";
	private static String moods_tab = "moods_tab";
	private static String fan_tab = "fan_tab";
	private static String media_tab = "media_tab";

	private TabHost tabHost;
	// private TextView zoneNameTextView;
	// private ImageView backImageView;

	private LightFragment lightFragment;
	private HVACFragment hvacFragment;
	private ZAudioFragment zAudioFragment;
	private ShadesFragment shadesFragment;
	// private TVFragment tvFragment;
	// private DVDFragment dvdFragment;
	// private SATFragment satFragment;
	// private AppleTVFragment appleTVFragment;
	// private ProjectorFragment projectorFragment;
	private MoodsFragment moodsFragment;
	private FanFragment fanFragment;
	private MediaFragment mediaFragment;
	private ViewPager mViewPager;
	private HorizontalScrollView mHorizontalScroll;
	private ArrayList<Fragment> fragments;
	private Zones zones;
	private RadioButton main_tab_moods, main_tab_light, main_tab_hvac,
			main_tab_zaudio, main_tab_fan, main_tab_curtain, main_tab_media;
	private ArrayList<RadioButton> radios = new ArrayList<RadioButton>();
	private boolean tv = false, dvd = false, sat = false, appel = false,
			projector = false;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zone_details_activity);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mHorizontalScroll = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
		zones = (Zones) getIntent().getSerializableExtra("zone");

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		main_tab_moods = (RadioButton) findViewById(R.id.main_tab_moods);
		main_tab_light = (RadioButton) findViewById(R.id.main_tab_light);
		main_tab_hvac = (RadioButton) findViewById(R.id.main_tab_hvac);
		main_tab_zaudio = (RadioButton) findViewById(R.id.main_tab_zaudio);
		main_tab_fan = (RadioButton) findViewById(R.id.main_tab_fan);
		// main_tab_android_tv = (RadioButton)
		// findViewById(R.id.main_tab_android_tv);
		main_tab_curtain = (RadioButton) findViewById(R.id.main_tab_curtain);
		main_tab_media = (RadioButton) findViewById(R.id.main_tab_media);

		main_tab_light.setVisibility(View.GONE);
		main_tab_hvac.setVisibility(View.GONE);
		main_tab_moods.setVisibility(View.GONE);
		main_tab_zaudio.setVisibility(View.GONE);
		main_tab_fan.setVisibility(View.GONE);
		// main_tab_android_tv.setVisibility(View.GONE);
		main_tab_curtain.setVisibility(View.GONE);
		main_tab_media.setVisibility(View.GONE);
		// main_tab_moods.setVisibility(View.GONE);
		// backImageView = (ImageView) findViewById(R.id.backImageView);
		// backImageView.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// finish();
		// }
		// });

		if (zones != null) {
			ArrayList<SystemInZone> systemInZoneArrayList = new SystemInZoneDB(
					this).getSystemInZoneWithZoneID(zones.getZoneID());
			for (int i = 0; i < systemInZoneArrayList.size(); i++) {
				SystemInZone system = systemInZoneArrayList.get(i);
				switch (system.getSystemID()) {
				case 5:
					tv = true;
					break;
				case 6:
					dvd = true;
					break;
				case 7:
					sat = true;
					break;
				case 8:
					appel = true;
					break;
				case 9:
					projector = true;
					break;
				}
			}
			fragments = new ArrayList<Fragment>();

			boolean mediaAdded = false;
			for (int i = 0; i < systemInZoneArrayList.size(); i++) {
				SystemInZone system = systemInZoneArrayList.get(i);
				switch (system.getSystemID()) {
				case 1:
					lightFragment = new LightFragment(zones);
					fragments.add(lightFragment);
					main_tab_light.setVisibility(View.VISIBLE);
					addLightTab();
					radios.add(main_tab_light);
					break;
				case 2:
					hvacFragment = new HVACFragment(zones);
					fragments.add(hvacFragment);
					addHVACTab();
					main_tab_hvac.setVisibility(View.VISIBLE);
					radios.add(main_tab_hvac);
					break;
				case 3:
					zAudioFragment = new ZAudioFragment(zones);
					fragments.add(zAudioFragment);
					addZAudioTab();
					main_tab_zaudio.setVisibility(View.VISIBLE);
					radios.add(main_tab_zaudio);
					break;
				case 4:
					shadesFragment = new ShadesFragment(zones);
					fragments.add(shadesFragment);
					addShadesTab();
					main_tab_curtain.setVisibility(View.VISIBLE);
					radios.add(main_tab_curtain);
					break;
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
					if (!mediaAdded) {
						mediaFragment = new MediaFragment(zones, tv, dvd, sat,
								appel, projector);
						fragments.add(mediaFragment);
						addMediaTab();
						main_tab_media.setVisibility(View.VISIBLE);
						mediaAdded = true;
						radios.add(main_tab_media);
					}
					break;
				case 10:
					moodsFragment = new MoodsFragment(zones);
					fragments.add(moodsFragment);
					main_tab_moods.setVisibility(View.VISIBLE);
					addMoodTab();
					radios.add(main_tab_moods);
					break;
				case 11:
					fanFragment = new FanFragment(zones);
					fragments.add(fanFragment);
					addFanTab();
					main_tab_fan.setVisibility(View.VISIBLE);
					radios.add(main_tab_fan);
					break;
				default:
					break;
				}

			}

			MyPageAdapter pageAdapter = new MyPageAdapter(
					getSupportFragmentManager(), fragments);
			mViewPager.setAdapter(pageAdapter);
			mViewPager.setOffscreenPageLimit(fragments.size());
			mViewPager.setOnPageChangeListener(this);

			tabHost.setOnTabChangedListener(this);

			RadioGroup radioGroup = (RadioGroup) this
					.findViewById(R.id.main_tab_group);
			radioGroup
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							// TODO Auto-generated method stub
							switch (checkedId) {
							case R.id.main_tab_light://
								tabHost.setCurrentTabByTag(ligth_tab);
								lightFragment.handler.sendEmptyMessage(1);

								break;
							case R.id.main_tab_hvac://
								tabHost.setCurrentTabByTag(hvac_tab);
								hvacFragment.hvacHandler.sendEmptyMessage(0);
								break;
							case R.id.main_tab_zaudio://
								tabHost.setCurrentTabByTag(zAudio_tab);
								zAudioFragment.handler.sendEmptyMessage(0);
								break;
							case R.id.main_tab_moods://
								tabHost.setCurrentTabByTag(moods_tab);
								break;
							case R.id.main_tab_fan:
								tabHost.setCurrentTabByTag(fan_tab);
								fanFragment.handler.sendEmptyMessage(0);// read
																		// current
																		// status
								break;
							// case R.id.main_tab_socket:
							// otabHost.setCurrentTabByTag("socket");
							// break;

							// case R.id.main_tab_android_tv:// 设置
							// tabHost.setCurrentTabByTag(tv_tab);

							// boolean blnInstall =
							// loadapp.checkInstall(googleTVpackName);
							// if(blnInstall == true)
							// {
							// startAPP(googleTVpackName);
							//
							// }
							// if(!blnInstall){
							// //代码安装
							// String fileName =
							// Environment.getExternalStorageDirectory()
							// +File.separator+ "SMART-BUS" +
							// File.separator+googTVAPKName;
							// Intent intent = new
							// Intent(Intent.ACTION_VIEW);
							// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							// //intent.setDataAndType(Uri.parse("file://"+fileName),
							// "application/vnd.android.package-archive");
							// intent.setDataAndType(Uri.fromFile(new
							// File(fileName)),
							// "application/vnd.android.package-archive");
							// room_control.this.startActivityForResult(intent,
							// 1);
							// }
							// break;

							// case R.id.main_tab_xbmc:// 设置
							// otabHost.setCurrentTabByTag("xbmc");
							// //
							// startAPP("org.xbmc.android.remote");//test，正在调试其他方法
							// // loadRunApk();
							// break;
							case R.id.main_tab_curtain:
								tabHost.setCurrentTabByTag(shades_tab);
								break;
							case R.id.main_tab_media:
								mediaFragment = new MediaFragment(zones);
								tabHost.setCurrentTabByTag(media_tab);
								break;
							default:
								// tabHost.setCurrentTabByTag("我的考试");
								break;
							}
						}
					});

		}

	}

	public void addLightTab() {
		TabHost.TabSpec lightTabSpec = tabHost.newTabSpec(ligth_tab);
		tabHost.setCurrentTab(0);
		lightTabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		lightTabSpec.setIndicator(createTabView(" Light ",
				R.drawable.tab_of_light));

		tabHost.addTab(lightTabSpec);
	}

	public void addMediaTab() {
		TabHost.TabSpec lightTabSpec = tabHost.newTabSpec(media_tab);
		tabHost.setCurrentTab(0);
		lightTabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		lightTabSpec
				.setIndicator(createTabView(" Media ", R.drawable.media_tab));

		tabHost.addTab(lightTabSpec);
	}

	public void addHVACTab() {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(hvac_tab);
		tabHost.setCurrentTab(0);
		tabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		tabSpec.setIndicator(createTabView(" HVAC ", R.drawable.tab_of_hvac));

		tabHost.addTab(tabSpec);
	}

	public void addZAudioTab() {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(zAudio_tab);
		tabHost.setCurrentTab(0);
		tabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		tabSpec.setIndicator(createTabView(" Z-Audio ", R.drawable.z_audio_on));

		tabHost.addTab(tabSpec);
	}

	public void addShadesTab() {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(shades_tab);
		tabHost.setCurrentTab(0);
		tabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		tabSpec.setIndicator(createTabView(" Shades ", R.drawable.curtain_on));

		tabHost.addTab(tabSpec);
	}

	// public void addTVTab() {
	// TabHost.TabSpec tabSpec = tabHost.newTabSpec(tv_tab);
	// tabHost.setCurrentTab(0);
	// tabSpec.setContent(new TabHost.TabContentFactory() {
	//
	// @Override
	// public View createTabContent(String tag) {
	// // TODO Auto-generated method stub
	// return findViewById(android.R.id.tabcontent);
	// }
	// });
	//
	// tabSpec.setIndicator(createTabView(" TV ", R.drawable.tv_on));
	//
	// tabHost.addTab(tabSpec);
	// }
	//
	// public void addDVDTab() {
	// TabHost.TabSpec tabSpec = tabHost.newTabSpec(dvd_tab);
	// tabHost.setCurrentTab(0);
	// tabSpec.setContent(new TabHost.TabContentFactory() {
	//
	// @Override
	// public View createTabContent(String tag) {
	// // TODO Auto-generated method stub
	// return findViewById(android.R.id.tabcontent);
	// }
	// });
	//
	// tabSpec.setIndicator(createTabView(" DVD ",
	// R.drawable.ic_tab_albums_selected));
	//
	// tabHost.addTab(tabSpec);
	// }
	//
	// public void addSATTab() {
	// TabHost.TabSpec tabSpec = tabHost.newTabSpec(sat_tab);
	// tabHost.setCurrentTab(0);
	// tabSpec.setContent(new TabHost.TabContentFactory() {
	//
	// @Override
	// public View createTabContent(String tag) {
	// // TODO Auto-generated method stub
	// return findViewById(android.R.id.tabcontent);
	// }
	// });
	//
	// tabSpec.setIndicator(createTabView(" SAT ",
	// R.drawable.tab_of_whole_house_normal));
	//
	// tabHost.addTab(tabSpec);
	// }
	//
	// public void addAppelTVTab() {
	// TabHost.TabSpec tabSpec = tabHost.newTabSpec(appleTV_tab);
	// tabHost.setCurrentTab(0);
	// tabSpec.setContent(new TabHost.TabContentFactory() {
	//
	// @Override
	// public View createTabContent(String tag) {
	// // TODO Auto-generated method stub
	// return findViewById(android.R.id.tabcontent);
	// }
	// });
	//
	// tabSpec.setIndicator(createTabView(" Appel TV ",
	// R.drawable.tab_of_whole_house_normal));
	//
	// tabHost.addTab(tabSpec);
	// }
	//
	// public void addProjectorTab() {
	// TabHost.TabSpec tabSpec = tabHost.newTabSpec(projector_tab);
	// tabHost.setCurrentTab(0);
	// tabSpec.setContent(new TabHost.TabContentFactory() {
	//
	// @Override
	// public View createTabContent(String tag) {
	// // TODO Auto-generated method stub
	// return findViewById(android.R.id.tabcontent);
	// }
	// });
	//
	// tabSpec.setIndicator(createTabView(" Projector ",
	// R.drawable.tab_of_whole_house_normal));
	//
	// tabHost.addTab(tabSpec);
	// }

	public void addMoodTab() {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(moods_tab);
		tabHost.setCurrentTab(0);
		tabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		tabSpec.setIndicator(createTabView(" Mood ",
				R.drawable.tab_of_whole_house_normal));

		tabHost.addTab(tabSpec);
	}

	public void addFanTab() {
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(fan_tab);
		tabHost.setCurrentTab(0);
		tabSpec.setContent(new TabHost.TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				// TODO Auto-generated method stub
				return findViewById(android.R.id.tabcontent);
			}
		});

		tabSpec.setIndicator(createTabView(" Fan ",
				R.drawable.tab_of_whole_house_normal));

		tabHost.addTab(tabSpec);
	}

	/*
	 * returns the tab view i.e. the tab icon and text
	 */
	private View createTabView(final String text, final int id) {
		View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
		imageView.setImageDrawable(getResources().getDrawable(id));
		((TextView) view.findViewById(R.id.tab_text)).setText(text);
		return view;
	}

	/*
	 * TabChangeListener for changing the tab when one of the tabs is pressed
	 */
	// TabHost.OnTabChangeListener listener = new TabHost.OnTabChangeListener()
	// {
	// public void onTabChanged(String tabId) {
	// /* Set current tab.. */
	// if (tabId.equals(ligth_tab)) {
	// pushFragments(tabId, lightFragment);
	// } else if (tabId.equals(hvac_tab)) {
	// pushFragments(tabId, hvacFragment);
	// } else if (tabId.equals(zAudio_tab)) {
	// pushFragments(tabId, zAudioFragment);
	// } else if (tabId.equals(shades_tab)) {
	// pushFragments(tabId, shadesFragment);
	// } else if (tabId.equals(tv_tab)) {
	// pushFragments(tabId, tvFragment);
	// } else if (tabId.equals(dvd_tab)) {
	// pushFragments(tabId, dvdFragment);
	// } else if (tabId.equals(sat_tab)) {
	// pushFragments(tabId, satFragment);
	// } else if (tabId.equals(appleTV_tab)) {
	// pushFragments(tabId, appleTVFragment);
	// } else if (tabId.equals(projector_tab)) {
	// pushFragments(tabId, projectorFragment);
	// } else if (tabId.equals(moods_tab)) {
	// pushFragments(tabId, moodsFragment);
	// } else if (tabId.equals(fan_tab)) {
	// pushFragments(tabId, fanFragment);
	// }
	// }
	// };

	/*
	 * adds the fragment to the FrameLayout
	 */
	public void pushFragments(String tag, Fragment fragment) {

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();

		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub

		int pos = this.tabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);

		if (tabId.equals(ligth_tab)) {
			lightFragment.handler.sendEmptyMessage(2);
			if (hvacFragment != null)
				hvacFragment.hvacHandler.sendEmptyMessage(1);
		} else if (tabId.equals(hvac_tab)) {
			hvacFragment.hvacHandler.sendEmptyMessage(0);
			if (lightFragment != null)
				lightFragment.handler.sendEmptyMessage(3);
		} else if (tabId.equals(zAudio_tab)) {
			if (hvacFragment != null)
				hvacFragment.hvacHandler.sendEmptyMessage(1);
			if (lightFragment != null)
				lightFragment.handler.sendEmptyMessage(3);
		} else if (tabId.equals(shades_tab)) {
			if (hvacFragment != null)
				hvacFragment.hvacHandler.sendEmptyMessage(1);
			if (lightFragment != null)
				lightFragment.handler.sendEmptyMessage(3);
		} else if (tabId.equals(moods_tab)) {
			if (hvacFragment != null)
				hvacFragment.hvacHandler.sendEmptyMessage(1);
			if (lightFragment != null)
				lightFragment.handler.sendEmptyMessage(3);
		} else if (tabId.equals(fan_tab)) {
			if (hvacFragment != null)
				hvacFragment.hvacHandler.sendEmptyMessage(1);
			if (lightFragment != null)
				lightFragment.handler.sendEmptyMessage(3);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		int pos = this.mViewPager.getCurrentItem();
		this.tabHost.setCurrentTab(pos);
		radios.get(pos).setChecked(true);
		View tabView = tabHost.getTabWidget().getChildAt(arg0);
		if (tabView != null) {

			final int width = mHorizontalScroll.getWidth();
			final int scrollPos = tabView.getLeft()
					- (width - tabView.getWidth()) / 2;
			mHorizontalScroll.scrollTo(scrollPos, 0);
		} else {
			mHorizontalScroll.scrollBy(arg2, 0);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		tabHost.setCurrentTab(arg0);
		radios.get(arg0).setChecked(true);

	}

	public class MyPageAdapter extends FragmentStatePagerAdapter {
		private ArrayList<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
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
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(!SmartSocketConnection.IsSocketClose()){
			SmartSocketConnection.moUDPSocket.close();
			
		}
		SmartSocketConnection.moUDPSocket = null;
	}
	
	
	
}
