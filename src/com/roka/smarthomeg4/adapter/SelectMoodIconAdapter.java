package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.MoodIconDefinition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SelectMoodIconAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<MoodIconDefinition> moodIconDList;

	public SelectMoodIconAdapter(Context context,
			ArrayList<MoodIconDefinition> moodIconDList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.moodIconDList = moodIconDList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (moodIconDList.size() > 0 && moodIconDList != null)
			return moodIconDList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (moodIconDList.size() > 0 && moodIconDList != null)
			return moodIconDList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (moodIconDList.size() > 0 && moodIconDList != null)
			return moodIconDList.get(position).getMoodIconID();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		SelectIconViewHolder selectIconViewHolder;
		if (convertView == null) {
			selectIconViewHolder = new SelectIconViewHolder();
			convertView = inflater.inflate(R.layout.select_mood_icon_item,
					parent, false);
			selectIconViewHolder.iconImageView = (ImageView) convertView
					.findViewById(R.id.iconImageView);
			convertView.setTag(selectIconViewHolder);
		} else {
			selectIconViewHolder = (SelectIconViewHolder) convertView.getTag();
		}

//		int id = context.getResources().getIdentifier(
//				context.getPackageName()
//						+ ":drawable/"
//						+ moodIconDList.get(position).getIconName()
//								.toLowerCase(), null, null);
//		Drawable image = context.getResources().getDrawable(id);
		selectIconViewHolder.iconImageView.setImageDrawable(GetDrawablIcon(moodIconDList.get(position).getMoodIconID(), context));

		return convertView;
	}

	class SelectIconViewHolder {
		ImageView iconImageView;
	}

	
	public static Drawable GetDrawablIcon(int intIconType, Context context) {
		Drawable mDrawable = null;
		try {
			Resources res = context.getResources();
			switch (intIconType) {
			case 0:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 1:

				mDrawable = res.getDrawable(R.drawable.meetingtime_macrox2);

				break;

			case 2:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 3:

				mDrawable = res.getDrawable(R.drawable.partytime_macrox2);

				break;

			case 4:

				mDrawable = res.getDrawable(R.drawable.tvtime_macrox2);

				break;
			case 5:

				mDrawable = res.getDrawable(R.drawable.bedtime_macrox2);

				break;
			case 6:

				mDrawable = res.getDrawable(R.drawable.manualtime_macrox2);

				break;
			case 7:

				mDrawable = res.getDrawable(R.drawable.energysaving_macrox2);

				break;
			case 8:

				mDrawable = res.getDrawable(R.drawable.visitormode_macrox2);

				break;

			case 9:

				mDrawable = res.getDrawable(R.drawable.nightvisitor_macrox2);

				break;
			case 10:

				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);

				break;

			case 11:

				mDrawable = res.getDrawable(R.drawable.swimmingtime_macrox2);

				break;

			default:
				mDrawable = res.getDrawable(R.drawable.romatictime_macrox2);
				break;

			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		return mDrawable;
	}

	
	
}
