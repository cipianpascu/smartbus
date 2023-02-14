package com.roka.smarthomeg4.frag;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.Zones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TVFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private RelativeLayout controlLayout, numLatout;
	private Button controlButton, numButton;

	public TVFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	public TVFragment() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone",zones);
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.tv_fragment, container, false);
		controlLayout = (RelativeLayout) view.findViewById(R.id.controlLatout);
		numLatout = (RelativeLayout) view.findViewById(R.id.numLatout);
		controlButton = (Button) view.findViewById(R.id.controlButton);
		numButton = (Button) view.findViewById(R.id.numButton);

		controlButton.setOnClickListener(this);
		
		numButton.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState!=null){
			zones=(Zones)savedInstanceState.getSerializable("zone");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.controlButton:
			controlLayout.setVisibility(View.VISIBLE);
			numLatout.setVisibility(View.GONE);
			controlButton.setBackgroundResource(R.drawable.mediasattitlebtn_b);
			numButton.setBackgroundResource(R.drawable.btn_normal);
			break;

		case R.id.numButton:
			controlLayout.setVisibility(View.GONE);
			numLatout.setVisibility(View.VISIBLE);
			numButton.setBackgroundResource(R.drawable.mediasattitlebtn_b);
			controlButton.setBackgroundResource(R.drawable.btn_normal);

			break;
		default:
			break;
		}
	}

}
