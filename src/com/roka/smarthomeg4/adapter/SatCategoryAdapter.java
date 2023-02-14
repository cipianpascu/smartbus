package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.SATCategory;
import com.roka.smarthomeg4.business.SATChannels;
import com.roka.smarthomeg4.database.dbconnection.SATChannelsDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class SatCategoryAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<SATCategory> satCategories;
	private GridView channelGridVew;

	public SatCategoryAdapter(Context context,
			ArrayList<SATCategory> satCategories, GridView channelGridVew) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.satCategories = satCategories;
		this.channelGridVew = channelGridVew;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (satCategories.size() > 0 && satCategories != null)
			return satCategories.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (satCategories.size() > 0 && satCategories != null)
			return satCategories.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (satCategories.size() > 0 && satCategories != null)
			return satCategories.get(position).getCategoryID();
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SatCategoryViewHolder saViewHolder;
		if (convertView == null) {
			saViewHolder = new SatCategoryViewHolder();
			convertView = inflater.inflate(R.layout.sat_category_list_item,
					parent, false);
			saViewHolder.satCatButton = (Button) convertView
					.findViewById(R.id.sat_cat_button);
			convertView.setTag(saViewHolder);
		} else {
			saViewHolder = (SatCategoryViewHolder) convertView.getTag();
		}
		saViewHolder.satCatButton.setText(satCategories.get(position)
				.getCategoryName());
		saViewHolder.satCatButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Toast.makeText(context, "button",
						// Toast.LENGTH_LONG).show();
						ArrayList<SATChannels> channelsArr = new SATChannelsDB(
								context)
								.getSATChannelsWithCategoryID(satCategories
										.get(position).getCategoryID());
						if (channelsArr == null) {
							channelsArr =new ArrayList<SATChannels>();
						}
							SatChannelAdapter satChannelAdapter = new SatChannelAdapter(
									context, channelsArr);
							channelGridVew.setAdapter(satChannelAdapter);
							satChannelAdapter.notifyDataSetChanged();
						
					}
				});

		return convertView;
	}

	public class SatCategoryViewHolder {
		public Button satCatButton;
	}

}
