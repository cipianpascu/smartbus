package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.SATChannels;
import com.roka.smarthomeg4.database.dbconnection.LogoDB;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SatChannelAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<SATChannels> satChannels;

	public SatChannelAdapter(Context context, ArrayList<SATChannels> satChannels) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.satChannels = satChannels;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (satChannels.size() > 0 && satChannels != null)
			return satChannels.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (satChannels.size() > 0 && satChannels != null)
			return satChannels.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (satChannels.size() > 0 && satChannels != null)
			return satChannels.get(position).getChannelID();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SatChannelViewHolder satChannelViewHolder;
		if (convertView == null) {
			satChannelViewHolder = new SatChannelViewHolder();
			convertView = inflater.inflate(R.layout.sat_channel_grid_item,
					parent, false);
			satChannelViewHolder.satChannelImageView = (ImageView) convertView
					.findViewById(R.id.channelImageView);
			satChannelViewHolder.satChannelText = (TextView) convertView
					.findViewById(R.id.channelTextView);
			convertView.setTag(satChannelViewHolder);
		} else {
			satChannelViewHolder = (SatChannelViewHolder) convertView.getTag();
		}

		satChannelViewHolder.satChannelText.setText(satChannels.get(position)
				.getChannelName());
//		satChannelViewHolder.satChannelImageView.setBackground(SatChannelAdapter.GetDrawablSatIcon(satChannels.get(position).getLogoID(),context));
		Bitmap back=new LogoDB(context).getImage(4, satChannels.get(position).getLogoID());
		satChannelViewHolder.satChannelImageView.setImageBitmap(back);
		return convertView;
	}

	public class SatChannelViewHolder {
		public ImageView satChannelImageView;
		public TextView satChannelText;
	}

	public static Drawable GetDrawablSatIcon(int id, Context context) {
		Drawable mDrawable = null;
		try {
			Resources res = context.getResources();
			switch (id) {
			case 1:
				mDrawable = res.getDrawable(R.drawable.sattemp01);
				break;
				
			case 2:
				mDrawable = res.getDrawable(R.drawable.sattemp02);
				break;
			case 3:
				mDrawable = res.getDrawable(R.drawable.sattemp03);
				break;
			case 4:
				mDrawable = res.getDrawable(R.drawable.sattemp04);
				break;
			case 5:
				mDrawable = res.getDrawable(R.drawable.sattemp05);
				break;
			case 6:
				mDrawable = res.getDrawable(R.drawable.sattemp06);
				break;
			case 7:
				mDrawable = res.getDrawable(R.drawable.sattemp07);
				break;
			case 8:
				mDrawable = res.getDrawable(R.drawable.sattemp08);
				break;
			case 9:
				mDrawable = res.getDrawable(R.drawable.sattemp05);
				break;
			case 10:
				mDrawable = res.getDrawable(R.drawable.sattemp05);
				break;

			case 11:
				mDrawable = res.getDrawable(R.drawable.sattemp01);
				break;
				
			case 12:
				mDrawable = res.getDrawable(R.drawable.sattemp02);
				break;
			case 13:
				mDrawable = res.getDrawable(R.drawable.sattemp03);
				break;
			case 14:
				mDrawable = res.getDrawable(R.drawable.sattemp04);
				break;
			case 15:
				mDrawable = res.getDrawable(R.drawable.sattemp05);
				break;
			case 16:
				mDrawable = res.getDrawable(R.drawable.sattemp06);
				break;
			case 17:
				mDrawable = res.getDrawable(R.drawable.sattemp07);
				break;
			case 18:
				mDrawable = res.getDrawable(R.drawable.sattemp08);
				break;
			case 0:
				mDrawable = res.getDrawable(R.drawable.sattemp05);
				break;
			

			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		return mDrawable;

	}

}
