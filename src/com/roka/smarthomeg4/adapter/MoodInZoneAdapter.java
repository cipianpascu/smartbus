package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.business.MoodInZone;
import com.roka.smarthomeg4.database.dbconnection.MoodCommandsDB;
import com.roka.smarthomeg4.udp_socket.SmartSocketConnection;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MoodInZoneAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<MoodInZone> moodInZoneList;

	public MoodInZoneAdapter(Context context,
			ArrayList<MoodInZone> moodInZoneList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.moodInZoneList = moodInZoneList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (moodInZoneList != null && moodInZoneList.size() > 0)
			return moodInZoneList.size();
		return 0;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (moodInZoneList != null && moodInZoneList.size() > 0)
			return moodInZoneList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (moodInZoneList != null && moodInZoneList.size() > 0)
			return moodInZoneList.get(position).getMoodID();
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		MoodInZoneViewHolder moodInZoneViewHolder;
		if (convertView == null) {
			moodInZoneViewHolder = new MoodInZoneViewHolder();
			convertView = layoutInflater.inflate(R.layout.mood_grid_item,
					parent, false);
			moodInZoneViewHolder.moodImageView = (ImageView) convertView
					.findViewById(R.id.moodImageView);
			moodInZoneViewHolder.moodName = (TextView) convertView
					.findViewById(R.id.moodNameTextView);
			moodInZoneViewHolder.moodProgressBar = (ProgressBar) convertView
					.findViewById(R.id.moodprogressBar);
			moodInZoneViewHolder.progressText=(TextView)convertView.findViewById(R.id.progressText);
			convertView.setTag(moodInZoneViewHolder);
		} else {
			moodInZoneViewHolder = (MoodInZoneViewHolder) convertView.getTag();
		}

		moodInZoneViewHolder.moodName.setText(moodInZoneList.get(position)
				.getMoodName());
		moodInZoneViewHolder.moodProgressBar.setMax(100);
		// ArrayList<MoodIconDefinition> moodIconDefinitions=new
		// MoodIconDefinitionDB(context).getMoodIconDefinitionWithMoodIconID(moodInZoneList.get(position).getMoodIconID());
		// int id = context.getResources().getIdentifier(
		// context.getPackageName()+":drawable/"+moodIconDefinitions.get(0).getIconName().toLowerCase(),null,
		// null);
		// Drawable image = context.getResources().getDrawable(id);
		final ProgressBar pro=moodInZoneViewHolder.moodProgressBar;
		final TextView text=moodInZoneViewHolder.progressText;
		moodInZoneViewHolder.moodImageView.setImageDrawable(GetDrawablIcon(
				moodInZoneList.get(position).getMoodIconID(), context));
		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MoodTask(moodInZoneList.get(position).getMoodID(),new MyHandler(pro,text,moodInZoneList.get(position)
						.getMoodName()))
						.execute();
			}
		});

		return convertView;
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

	class MoodInZoneViewHolder {
		public ImageView moodImageView;
		public TextView moodName;
		public ProgressBar moodProgressBar;
		public TextView progressText;
	}

	public class MoodTask extends AsyncTask<Void, Void, Void> {

		private int moodId;
		private MyHandler handler;

		public MoodTask(int moodId,MyHandler handler) {
			// TODO Auto-generated constructor stub

			this.moodId = moodId;
			this.handler=handler;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected Void doInBackground(Void... message) {
			ArrayList<CommandParameters> commaList = new MoodCommandsDB(context)
					.getMoodCommandsWithMoodID(moodId);
			if(commaList!=null){
			handler.setProcessNo(commaList.size());
			SmartSocketConnection.SendMutilCommands(commaList, true, false,
					true, handler);
			}else{
				handler.sendEmptyMessage(2);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void message) {
			// TODO Auto-generated method stub
			super.onPostExecute(message);

		}

	}

	 class MyHandler extends Handler {
		private ProgressBar progressBar;
		private int step=1;
		private TextView text;
		private String moodName;
		public MyHandler(ProgressBar progressBar,TextView text,
				String moodName) {
			// TODO Auto-generated constructor stub
			this.progressBar=progressBar;
			this.text=text;
			this.moodName=moodName;
			progressBar.setProgress(0);
			text.setText("0 %");
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				progressBar.setVisibility(View.VISIBLE);
				text.setVisibility(View.VISIBLE);
				int progress=progressBar.getProgress()+step;
				progressBar.setProgress(progress);
				text.setText(progress+"%");
				break;
			case 1:
				progressBar.setProgress(0);
				text.setText("0 %");
				progressBar.setVisibility(View.INVISIBLE);
				text.setVisibility(View.INVISIBLE);
				break;
			case 2:
				Toast.makeText(context, moodName+" No Data", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
		
		
		public void setProcessNo(int max) {			
			this.step=100/max;
			
		}
	}

	

}
