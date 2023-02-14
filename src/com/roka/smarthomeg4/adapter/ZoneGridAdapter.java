package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.My_app;
import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.ZoneIconDefine;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.dbconnection.LogoDB;
import com.roka.smarthomeg4.database.dbconnection.ZoneIconDefineDB;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZoneGridAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<Zones> zonesArrayList;
	private ZoneIconDefineDB zoneIconDefineDB;

	public ZoneGridAdapter(Context c, ArrayList<Zones> zonesArrayList) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.zoneIconDefineDB=new ZoneIconDefineDB(context);
		this.layoutInflater = LayoutInflater.from(c);
		this.zonesArrayList = zonesArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (zonesArrayList != null)
			return zonesArrayList.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (zonesArrayList != null)
			return zonesArrayList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (zonesArrayList != null)
			return zonesArrayList.get(position).getZoneID();
		else
			return 0;
	}

	class ViewHolder {
		public ImageView imageView;
		public TextView textView;
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
		
		viewHolder.textView.setText(zonesArrayList.get(position).getZoneName());
//		ArrayList<ZoneIconDefine> zonArrayList=zoneIconDefineDB.getZoneIconDefineWithZoneIconID(zonesArrayList.get(position).getZoneIconID());
//		Log.e("icon name ", zonArrayList.get(0).getIconName());
//		int id = context.getResources().getIdentifier( context.getPackageName()+":drawable/"+zonArrayList.get(0).getIconName().toLowerCase(),null, null);
//		Drawable image = context.getResources().getDrawable(id);
//		Bitmap back=new LogoDB(context).getImage(1, zonesArrayList.get(position).getZoneIconID());
		if(My_app.hasmap==null){
			My_app.fillImages(context);
		}
			
		viewHolder.imageView.setImageBitmap(My_app.hasmap.get(zonesArrayList.get(position).getZoneIconID()).getBlogData());
		
			
	
		return view;
	}

}
