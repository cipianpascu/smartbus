package com.roka.smarthomeg4.frag;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.Zones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AppleTVFragment extends Fragment {

	private Zones zones;
	
	
	public AppleTVFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones=zones;
	}
	
	public AppleTVFragment() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.appel_tv_fragment, container, false);
		
		
		return view;
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone",zones);
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		if(savedInstanceState!=null){
			zones=(Zones)savedInstanceState.getSerializable("zone");
		}
	}
	
	
}
