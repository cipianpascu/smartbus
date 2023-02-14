package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.ShadesInZone;
import com.roka.smarthomeg4.udp_socket.ShadesAndSwitchUDPSocket;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ShadesAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ShadesInZone> shadesInZoneArrayList;

	public ShadesAdapter(Context context,
			ArrayList<ShadesInZone> shadesInZoneArrayList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.shadesInZoneArrayList = shadesInZoneArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (shadesInZoneArrayList.size() > 0 && shadesInZoneArrayList != null)
			return shadesInZoneArrayList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (shadesInZoneArrayList.size() > 0 && shadesInZoneArrayList != null)
			return shadesInZoneArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (shadesInZoneArrayList.size() > 0 && shadesInZoneArrayList != null)
			return shadesInZoneArrayList.get(position).getShadeID();
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		return shadesInZoneArrayList.get(position).getHasStop();

	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ShadesViewHolder shadesViewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.shades_item, parent, false);
			shadesViewHolder = new ShadesViewHolder();
			shadesViewHolder.closeButton = (Button) convertView
					.findViewById(R.id.closeShadebutton);
			shadesViewHolder.openButton = (Button) convertView
					.findViewById(R.id.openShadebutton);
			shadesViewHolder.stopButton = (Button) convertView
					.findViewById(R.id.stopShadebutton);
			shadesViewHolder.shadesNameTextView = (TextView) convertView
					.findViewById(R.id.shadNameTextView);
			convertView.setTag(shadesViewHolder);
		} else {
			shadesViewHolder = (ShadesViewHolder) convertView.getTag();
		}

		final ShadesInZone shadesInZone = shadesInZoneArrayList.get(position);

		if (shadesInZone.getHasStop() == 0) {
			shadesViewHolder.stopButton.setVisibility(View.GONE);
		} else {
			shadesViewHolder.stopButton.setVisibility(View.VISIBLE);
		}
		
		shadesViewHolder.shadesNameTextView.setText(shadesInZone.getShadeName());

		shadesViewHolder.openButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		shadesViewHolder.closeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		shadesViewHolder.stopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		return convertView;
	}

	class ShadesViewHolder {
		TextView shadesNameTextView;
		Button openButton, stopButton, closeButton;
	}
	
	
	
	
	public class ShadesCommands extends AsyncTask<Void, Void, Void>{
		
		private ShadesInZone shadesInZone;
		private int type;
		
		
		public ShadesCommands(ShadesInZone shadesInZone,int type) {
			// TODO Auto-generated constructor stub
			this.shadesInZone=shadesInZone;
			this.type=type;
		}
		
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
//			new ShadesUDPSocketConnection().shadesControl(shadesInZone.get, byteDeviceID, intCurtainNo, intCurtainStatus, blnNeedResend, blnNeedToWaitANS)
			
			return null;
		}
	}

}
