package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.frag.ZoneGridFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ZoneViewFlowAdapter extends FragmentStatePagerAdapter{

	private ArrayList<ZoneGridFragment> zoneGridFragments;
	public ZoneViewFlowAdapter(FragmentManager fragmentManager,ArrayList<ZoneGridFragment> zoneGridFragments) {
		// TODO Auto-generated constructor stub
		super(fragmentManager);
		this.zoneGridFragments=zoneGridFragments;
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return zoneGridFragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return zoneGridFragments.size();
	}

}
