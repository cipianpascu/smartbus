package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.SystemInZone;
import com.roka.smarthomeg4.database.dbconnection.SystemDefinitionDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class RecordMoodAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<SystemInZone> systemInZoneArrayList;
	private ArrayList<Boolean> arrValues;
	public RecordMoodAdapter(Context context,ArrayList<Boolean> arrValues,
			ArrayList<SystemInZone> systemInZoneArrayList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.systemInZoneArrayList = systemInZoneArrayList;
		this.arrValues=arrValues;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (systemInZoneArrayList.size() > 0 && systemInZoneArrayList != null)
			return systemInZoneArrayList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (systemInZoneArrayList.size() > 0 && systemInZoneArrayList != null)
			return systemInZoneArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (systemInZoneArrayList.size() > 0 && systemInZoneArrayList != null)
			return systemInZoneArrayList.get(position).getSystemID();
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RecordMoodSystemViewHolder rViewHolder;
		if (convertView == null) {
			rViewHolder = new RecordMoodSystemViewHolder();
			convertView = inflater.inflate(R.layout.record_mood_system_item,
					parent, false);
			rViewHolder.systemCheckBox = (CheckBox) convertView
					.findViewById(R.id.systemCheckBox);
			convertView.setTag(rViewHolder);
		} else {
			rViewHolder = (RecordMoodSystemViewHolder) convertView.getTag();
		}

		rViewHolder.systemCheckBox.setText(new SystemDefinitionDB(context)
				.getSystemDefinitionWithSystemID(
						systemInZoneArrayList.get(position).getSystemID())
				.get(0).getSystemName());

		rViewHolder.systemCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
					arrValues.set(position, isChecked);
			}
		});
		return convertView;

	}

	class RecordMoodSystemViewHolder {
		public CheckBox systemCheckBox;
	}

}
