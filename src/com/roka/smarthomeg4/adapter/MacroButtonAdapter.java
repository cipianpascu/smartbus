package com.roka.smarthomeg4.adapter;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.business.MacroButton;
import com.roka.smarthomeg4.database.dbconnection.MacroCommandsDB;
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

public class MacroButtonAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<MacroButton> macroButtonList;

	public MacroButtonAdapter(Context context,
			ArrayList<MacroButton> macroButtonList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.macroButtonList = macroButtonList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (macroButtonList != null && macroButtonList.size() > 0)
			return macroButtonList.size();
		return 0;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (macroButtonList != null && macroButtonList.size() > 0)
			return macroButtonList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (macroButtonList != null && macroButtonList.size() > 0)
			return macroButtonList.get(position).getMacroID();
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		MacroButtonViewHolder macroButtonViewHolder;
		if (convertView == null) {
			macroButtonViewHolder = new MacroButtonViewHolder();
			convertView = layoutInflater.inflate(R.layout.mood_grid_item,
					parent, false);
			macroButtonViewHolder.moodImageView = (ImageView) convertView
					.findViewById(R.id.moodImageView);
			macroButtonViewHolder.moodName = (TextView) convertView
					.findViewById(R.id.moodNameTextView);
			macroButtonViewHolder.moodProgressBar = (ProgressBar) convertView
					.findViewById(R.id.moodprogressBar);
			macroButtonViewHolder.progressText = (TextView) convertView
					.findViewById(R.id.progressText);

			convertView.setTag(macroButtonViewHolder);
		} else {
			macroButtonViewHolder = (MacroButtonViewHolder) convertView
					.getTag();
		}

		macroButtonViewHolder.moodName.setText(macroButtonList.get(position)
				.getMacroName());
		macroButtonViewHolder.moodProgressBar.setMax(100);
		macroButtonViewHolder.moodImageView.setImageDrawable(GetDrawablIcon(
				macroButtonList.get(position).getMacroIconID(), context));
		final ProgressBar pro = macroButtonViewHolder.moodProgressBar;
		final TextView text = macroButtonViewHolder.progressText;
		convertView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MacroTask(macroButtonList.get(position).getMacroID(),
						new MyHandler(pro, text, macroButtonList.get(position)
								.getMacroName())).execute();
			}
		});
		return convertView;
	}

	class MacroButtonViewHolder {
		public ImageView moodImageView;
		public TextView moodName, progressText;
		public ProgressBar moodProgressBar;

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

	public class MacroTask extends AsyncTask<Void, Void, Void> {

		private int macroId;
		private MyHandler handler;

		public MacroTask(int macroId, MyHandler handler) {
			// TODO Auto-generated constructor stub
			this.handler = handler;
			this.macroId = macroId;
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
			ArrayList<CommandParameters> commaList = new MacroCommandsDB(
					context).getMacroCommandsWithMicroID(macroId);
			if (commaList != null) {
				handler.setProcessNo(commaList.size());
				SmartSocketConnection.SendMutilCommands(commaList, true, false,
						true, handler);
			} else {
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
		private int step = 1;
		private TextView text;
		private String macroName;

		public MyHandler(ProgressBar progressBar, TextView text,
				String macroName) {
			// TODO Auto-generated constructor stub
			this.progressBar = progressBar;
			this.text = text;
			this.macroName=macroName;
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
				int progress = progressBar.getProgress() + step;
				progressBar.setProgress(progress);
				text.setText(progress + "%");
				break;
			case 1:
				progressBar.setProgress(0);
				text.setText("0 %");
				progressBar.setVisibility(View.INVISIBLE);
				text.setVisibility(View.INVISIBLE);
				break;
			case 2:
				Toast.makeText(context, macroName+" No Data", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}

		public void setProcessNo(int max) {
			this.step = 100 / max;

		}
	}

}
