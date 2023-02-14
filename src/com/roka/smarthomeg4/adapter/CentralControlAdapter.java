package com.roka.smarthomeg4.adapter;

import com.roka.smarthomeg4.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CentralControlAdapter  extends BaseAdapter{

	
	int drawable[]={R.drawable.marcobuttons,R.drawable.lightsmenu,R.drawable.musicmenu,R.drawable.security,R.drawable.climate,R.drawable.reminder,R.drawable.camera,R.drawable.appliances,R.drawable.intercom};
	String names[]={"MarcoButton","Lights","Music","Security","Climate","Schedule","Camera","Appliances","Intercom"};
	int ids[]={1,2,3,4,5,6,7,8,9};
	
	private Context context;
	private LayoutInflater layoutInflater;
	
	public CentralControlAdapter(Context c) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.layoutInflater = LayoutInflater.from(c);
	}
	
	
	class ViewHolder {
		public ImageView imageView;
		public TextView textView;
		int id;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ids.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return ids[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder viewHolder;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.zone_grid_item, parent,
					false);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view
					.findViewById(R.id.zone_image);
			viewHolder.textView = (TextView) view
					.findViewById(R.id.zone_name_textView);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		viewHolder.id=ids[position];
		viewHolder.imageView.setBackgroundResource(drawable[position]);
		viewHolder.textView.setText(names[position]);
		
		return view;
	}

}
