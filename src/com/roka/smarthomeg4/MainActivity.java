package com.roka.smarthomeg4;

import java.util.ArrayList;
import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayGridView;
import com.roka.smarthomeg4.adapter.CentralControlAdapter;
import com.roka.smarthomeg4.adapter.CommunalServiceAdapter;
import com.roka.smarthomeg4.adapter.ZoneGridAdapter;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.ZonesDB;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

//	private PageIndicator mIndicator;
//	private ViewPager viewPager;
//	private ZoneViewFlowAdapter zoneViewFlowAdapter;
	private TwoWayGridView mImageGrid;
	private ImageView g4ImageView;
	private TwoWayGridView mImageGrid1;

	private TwoWayGridView mImageGrid2;

	private ArrayList<Zones> zonArrayList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		g4ImageView=(ImageView)findViewById(R.id.g4ImageView);
		g4ImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
				startActivity(intent);
			}
		});
		mImageGrid = (TwoWayGridView) findViewById(R.id.gridview);
		mImageGrid1 = (TwoWayGridView) findViewById(R.id.gridview1);
		mImageGrid2 = (TwoWayGridView) findViewById(R.id.gridview2);
//		viewPager = (ViewPager) findViewById(R.id.pager);
//		mIndicator = (PageIndicator) findViewById(R.id.pagerIndicator);
		
		 zonArrayList=new ZonesDB(this).getAllZones();
		
//		ArrayList<ZoneGridFragment> zoneGridFragmentsList=new ArrayList<ZoneGridFragment>();
//		zoneGridFragmentsList.add(new ZoneGridFragment(zonArrayList));
//		zoneGridFragmentsList.add(new ZoneGridFragment(zonArrayList));
//		zoneGridFragmentsList.add(new ZoneGridFragment(zonArrayList));
//		zoneGridFragmentsList.add(new ZoneGridFragment(zonArrayList));
//		zoneViewFlowAdapter=new ZoneViewFlowAdapter(getSupportFragmentManager(), zoneGridFragmentsList);
//		viewPager.setAdapter(zoneViewFlowAdapter);
//        mIndicator.setViewPager(viewPager);

		mImageGrid.setAdapter(new ZoneGridAdapter(this, zonArrayList));
		mImageGrid.setOnItemClickListener(new TwoWayGridView.OnItemClickListener() {

		
			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,MainZoneDetailsActivity.class);
				intent.putExtra("zone", zonArrayList.get(position));
				startActivity(intent);
				
			}
		});
		
		mImageGrid1.setAdapter(new CentralControlAdapter(this));
		mImageGrid1.setOnItemClickListener(new TwoWayGridView.OnItemClickListener() {

		
			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				switch (position) {
				case 0:
					intent=new Intent(MainActivity.this,MacroActionsActivity.class);
					startActivity(intent);
					break;
					
				case 1:
					intent=new Intent(MainActivity.this,CentralLightsActivity.class);
					startActivity(intent);
					break;
				case 2:
//					intent=new Intent(MainActivity.this,MusicActivity.class);
//					startActivity(intent);
					break;
				case 3:
//					intent=new Intent(MainActivity.this,CentralLightsActivity.class);
//					startActivity(intent);
					break;
				case 4:
					intent=new Intent(MainActivity.this,ClimateActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
				
			}
		});
		
		mImageGrid2.setAdapter(new CommunalServiceAdapter(this));
		mImageGrid2.setOnItemClickListener(new TwoWayGridView.OnItemClickListener() {

		
			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
