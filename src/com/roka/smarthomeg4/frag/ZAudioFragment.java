package com.roka.smarthomeg4.frag;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.ZAudio_Album;
import com.roka.smarthomeg4.business.ZaudioInZone;
import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.Zaudio_DB;
import com.roka.smarthomeg4.database.dbconnection.ZaudioInZoneDB;
import com.roka.smarthomeg4.helper.VerticalSeekBar;
import com.roka.smarthomeg4.udp_socket.ZAudioUDBSocket;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ZAudioFragment extends Fragment implements OnClickListener {

	private Zones zones;
	private ZaudioInZone zaudioInZone;
	private ArrayList<ZAudio_Album> albumsList = new ArrayList<ZAudio_Album>();
	private ArrayList<ZAudio_Album> songsList = new ArrayList<ZAudio_Album>();
	private ImageView sdCardImageView, networkImageView, radioImageView,
			soundImageView;

	private Button zoneNameButton, nextButton, stopButton, playButton,
			previousButton, playMoodbutton, volumButton, eqlizerButton;
	private Zaudio_DB zaudio_DB;
	// private final static int PLAY_MODE_Not_Repeated = 1;
	// private final static int PLAY_MODE_Repeat_One_Song = 2;
	// private final static int PLAY_MODE_Repeat_One_Album = 3;
	// private final static int PLAY_MODE_Repeat_All_Album = 4;

	private final static int Sourse_SD = 1;
	private final static int Sourse_FTP = 2;
	private final static int Sourse_Radio = 3;
	private final static int Sourse_SoundIN = 4;

	private int currentBass = 0, currentTreble = 0;
	private VerticalSeekBar verticalSeekBar;

	private int current_play_mode = 1;
	private int current_Source = Sourse_SD;

	private Button album_list_button;

	private ListView songsListView;
	private static int currentAlbumNo = 1;
	private int albumListPositionSelect = 0;

	private static final int QTY_of_Big_Packages = 1;
	private static final int Read_Album = 2;
	private static final int Read_Song = 3;

	private int totalQuantityOfBigPak = 0;
	private int totalQuantityOfBigPakSong = 0;
	private ZAudio_Album currentAlbum;
	private TextView zoneNameTextView;
	private int currentAlbumIndex = 0, currentSong = 0;

	public ZAudioFragment(Zones zones) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
	}

	private boolean sdcard, network, radio, input;

	private RelativeLayout sdcardLayout, networkLayout, radioLayout,
			soundInLayout;

	public ZAudioFragment(Zones zones, boolean sdcard, boolean network,
			boolean radio, boolean input) {
		// TODO Auto-generated constructor stub
		this.zones = zones;
		this.sdcard = sdcard;
		this.network = network;
		this.radio = radio;
		this.input = input;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putSerializable("zone", zones);
	}

	public ZAudioFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.z_audio_fragment, container,
				false);

		zoneNameButton = (Button) view.findViewById(R.id.zoneNameButton);

		sdcardLayout = (RelativeLayout) view.findViewById(R.id.sdcardLayout);
		networkLayout = (RelativeLayout) view.findViewById(R.id.networkLayout);
		radioLayout = (RelativeLayout) view.findViewById(R.id.radioLayout);
		soundInLayout = (RelativeLayout) view.findViewById(R.id.soundInLayout);
		 zoneNameTextView = (TextView) view.findViewById(R.id.zoneNametext);
		
		nextButton = (Button) view.findViewById(R.id.nextButton);
		stopButton = (Button) view.findViewById(R.id.stopButton);
		playButton = (Button) view.findViewById(R.id.playButton);
		previousButton = (Button) view.findViewById(R.id.previousButton);
		album_list_button = (Button) view.findViewById(R.id.album_list_button);

		verticalSeekBar = (VerticalSeekBar) view
				.findViewById(R.id.vertical_Seekbar);
		verticalSeekBar.setMax(79);

		verticalSeekBar
				.setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub

						new SendAudioControllCommandAsync(zaudioInZone,
								(byte) 5, (byte) 1, (byte) 3,
								(byte) (79 - progress)).execute();
					}
				});
		nextButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		previousButton.setOnClickListener(this);

		playMoodbutton = (Button) view.findViewById(R.id.playMoodbutton);
		volumButton = (Button) view.findViewById(R.id.volumButton);
		eqlizerButton = (Button) view.findViewById(R.id.eqlizerButton);

		playMoodbutton.setOnClickListener(this);
		volumButton.setOnClickListener(this);
		eqlizerButton.setOnClickListener(this);

		sdCardImageView = (ImageView) view.findViewById(R.id.sdCardImageView);
		networkImageView = (ImageView) view.findViewById(R.id.networkImageView);
		radioImageView = (ImageView) view.findViewById(R.id.radioImageView);
		soundImageView = (ImageView) view.findViewById(R.id.soundImageView);

		sdCardImageView.setOnClickListener(this);
		networkImageView.setOnClickListener(this);
		radioImageView.setOnClickListener(this);
		soundImageView.setOnClickListener(this);
		album_list_button.setOnClickListener(this);

		sdCardImageView.setBackgroundResource(R.drawable.a);
		networkImageView.setBackgroundResource(R.drawable.b);
		radioImageView.setBackgroundResource(R.drawable.c);
		soundImageView.setBackgroundResource(R.drawable.d);

		songsListView = (ListView) view.findViewById(R.id.songsListView);
		songsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// custom adapter
		// String[] names = new String[] { "Android", "Windows7", "Symbian",
		// "iPhone", "Android", "Windows7", "Symbian", "iPhone",
		// "Android", "Windows7", "Symbian", "iPhone" };
		songsListView.setItemsCanFocus(false);
		songsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		songsListView.setAdapter(new ArrayAdapter<ZAudio_Album>(getActivity(),
				R.layout.zaudio_song_list_item, R.id.songNameText, songsList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView, parent);
				ImageView icon = (ImageView) v
						.findViewById(R.id.checkImageView);
				TextView songNameText = (TextView) v
						.findViewById(R.id.songNameText);
				if (songsListView.isItemChecked(position)) {
					icon.setVisibility(View.VISIBLE);
					songNameText.setTextColor(Color.YELLOW);
				} else {
					icon.setVisibility(View.INVISIBLE);
					songNameText.setTextColor(Color.WHITE);
				}
				return v;
			}
		});

		songsListView
				.setOnItemClickListener(new ListView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						if (currentSong == position) {
							new SendAudioControllCommandAsync(zaudioInZone,
									(byte) 6, (byte) currentAlbum.getNo(),
									(byte) songsList.get(position)
											.getSongNoHigh(), (byte) songsList
											.get(position).getSongNoLow())
									.execute();
						} else {
							currentSong = position;
							songsListView.setSelection(position);
							((ArrayAdapter) songsListView.getAdapter())
									.notifyDataSetChanged();
						}

					}
				});
		
		ImageView backImageView = (ImageView) view.findViewById(R.id.backImageView);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		
		
		ImageView mainImageViewChange = (ImageView) view
				.findViewById(R.id.mainImageViewChange);
		mainImageViewChange.setVisibility(View.VISIBLE);
		mainImageViewChange.setBackgroundResource(R.drawable.setup);
		mainImageViewChange.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.zaudio_data_setting);
				final TextView zoneNameTextView = (TextView) dialog
						.findViewById(R.id.zoneNameTextView);
				zoneNameTextView.setText(zones.getZoneName());
				final CheckBox clearSDBox = (CheckBox) dialog
						.findViewById(R.id.clearSDCheckBox);
				final LinearLayout sdLayout = (LinearLayout) dialog
						.findViewById(R.id.sdcardLayout);
				final CheckBox clearFTPBox = (CheckBox) dialog
						.findViewById(R.id.ftpcheckBox);
				final LinearLayout FTPLayout = (LinearLayout) dialog
						.findViewById(R.id.ftpLaypout);
				final CheckBox updateFTPBox = (CheckBox) dialog
						.findViewById(R.id.updateFTPcheckBox);
				final LinearLayout updateFTPLayout = (LinearLayout) dialog
						.findViewById(R.id.updateFTPLayout);
				final Button okButton = (Button) dialog
						.findViewById(R.id.okBtn);
				final Button cancelButton = (Button) dialog
						.findViewById(R.id.cancelBtn);

				sdLayout.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						clearFTPBox.setChecked(false);
						updateFTPBox.setChecked(false);
						clearSDBox.setChecked(true);
					}
				});

				FTPLayout.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						clearFTPBox.setChecked(true);
						updateFTPBox.setChecked(false);
						clearSDBox.setChecked(false);
					}
				});

				updateFTPLayout
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								clearFTPBox.setChecked(false);
								updateFTPBox.setChecked(true);
								clearSDBox.setChecked(false);
							}
						});

				okButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(clearSDBox.isChecked()){
						new Zaudio_DB(getActivity()).clearDB(zones.getZoneID(),Sourse_SD);
						}else if(clearFTPBox.isChecked()){
							
						}else if(updateFTPBox.isChecked()){
							
						}
						dialog.dismiss();
					}
				});

				cancelButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			zones = (Zones) savedInstanceState.getSerializable("zone");
		}
		zaudioInZone = new ZaudioInZoneDB(getActivity())
				.getZaudioInZoneWithZoneID(zones.getZoneID()).get(0);
		zoneNameButton.setText(zones.getZoneName());
		zaudio_DB = new Zaudio_DB(getActivity());
		zoneNameTextView.setText(zones.getZoneName());
		//

	}

	// @Override
	// public void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	// Toast.makeText(getActivity(), "start", Toast.LENGTH_LONG).show();
	// }
	//
	//
	// @Override
	// public void onResume() {
	// // TODO Auto-generated method stub
	// super.onResume();
	// Toast.makeText(getActivity(), "Resume", Toast.LENGTH_LONG).show();
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sdCardImageView:
			current_Source = Sourse_SD;
			sdCardImageView.setBackgroundResource(R.drawable.a);
			networkImageView.setBackgroundResource(R.drawable.b);
			radioImageView.setBackgroundResource(R.drawable.c);
			soundImageView.setBackgroundResource(R.drawable.d);

			sdCardImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			networkImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			radioImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			soundImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

			sdcardLayout.setVisibility(View.VISIBLE);
			networkLayout.setVisibility(View.GONE);
			radioLayout.setVisibility(View.GONE);
			soundInLayout.setVisibility(View.GONE);
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 1, (byte) 1,
					(byte) 0, (byte) 0).execute();

			break;

		case R.id.networkImageView:
			current_Source = Sourse_FTP;
			sdCardImageView.setBackgroundResource(R.drawable.e);
			networkImageView.setBackgroundResource(R.drawable.network);
			radioImageView.setBackgroundResource(R.drawable.c);
			soundImageView.setBackgroundResource(R.drawable.d);
			networkImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			sdCardImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			radioImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			soundImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

			sdcardLayout.setVisibility(View.GONE);
			networkLayout.setVisibility(View.VISIBLE);
			radioLayout.setVisibility(View.GONE);
			soundInLayout.setVisibility(View.GONE);
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 1, (byte) 3,
					(byte) 0, (byte) 0).execute();
			handler.sendEmptyMessage(0);

			break;

		case R.id.radioImageView:
			current_Source = Sourse_Radio;
			sdCardImageView.setBackgroundResource(R.drawable.e);
			networkImageView.setBackgroundResource(R.drawable.f);
			radioImageView.setBackgroundResource(R.drawable.h);
			soundImageView.setBackgroundResource(R.drawable.d);

			radioImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			networkImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			sdCardImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			soundImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));

			sdcardLayout.setVisibility(View.GONE);
			networkLayout.setVisibility(View.GONE);
			radioLayout.setVisibility(View.VISIBLE);
			soundInLayout.setVisibility(View.GONE);
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 1, (byte) 4,
					(byte) 0, (byte) 0).execute();

			break;

		case R.id.soundImageView:
			current_Source = Sourse_SoundIN;
			sdCardImageView.setBackgroundResource(R.drawable.e);
			networkImageView.setBackgroundResource(R.drawable.f);
			radioImageView.setBackgroundResource(R.drawable.i);
			soundImageView.setBackgroundResource(R.drawable.j);

			soundImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			networkImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			radioImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			sdCardImageView.setLayoutParams(new LinearLayout.LayoutParams(
					(int) getResources().getDimension(
							R.dimen.un_selected_image_z_audio_width),
					(int) getResources().getDimension(
							R.dimen.image_z_audio_high)));
			sdcardLayout.setVisibility(View.GONE);
			networkLayout.setVisibility(View.GONE);
			radioLayout.setVisibility(View.GONE);
			soundInLayout.setVisibility(View.VISIBLE);
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 1, (byte) 2,
					(byte) 0, (byte) 0).execute();

			break;

		case R.id.playButton:
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 6,
					(byte) currentAlbum.getNo(), (byte) songsList.get(
							currentSong).getSongNoHigh(), (byte) songsList.get(
							currentSong).getSongNoLow()).execute();
			// new SendAudioControllCommandAsync(zaudioInZone, (byte) 4, (byte)
			// 3,
			// (byte) 0, (byte) 0).execute();
			break;

		case R.id.nextButton:
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 4, (byte) 2,
					(byte) 0, (byte) 0).execute();
			break;

		case R.id.previousButton:
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 4, (byte) 1,
					(byte) 0, (byte) 0).execute();
			break;

		case R.id.stopButton:
			new SendAudioControllCommandAsync(zaudioInZone, (byte) 4, (byte) 4,
					(byte) 0, (byte) 0).execute();
			break;

		case R.id.playMoodbutton:

			if (current_play_mode < 4 && current_play_mode > 0) {
				current_play_mode++;
			} else if (current_play_mode == 4) {
				current_play_mode = 1;
			}

			switch (current_play_mode) {
			case 1:
				playMoodbutton.setBackgroundResource(R.drawable.songplaymode1);
				break;

			case 2:
				playMoodbutton.setBackgroundResource(R.drawable.songplaymode2);
				break;

			case 3:
				playMoodbutton.setBackgroundResource(R.drawable.songplaymode3);
				break;

			case 4:
				playMoodbutton.setBackgroundResource(R.drawable.songplaymode4);
				break;

			default:
				break;
			}

			new SendAudioControllCommandAsync(zaudioInZone, (byte) 2,
					(byte) current_play_mode, (byte) 0, (byte) 0).execute();
			break;

		case R.id.volumButton:
			//
			if (verticalSeekBar.getVisibility() == View.VISIBLE) {
				verticalSeekBar.setVisibility(View.GONE);
			} else {
				verticalSeekBar.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.eqlizerButton:

			final int max = 7,
			min = -7;

			final Dialog dialog = new Dialog(getActivity());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.zaudio_sound_effect);
			Button BassMinusbtn = (Button) dialog
					.findViewById(R.id.BassMinusbtn);
			Button Bassplusbtn = (Button) dialog.findViewById(R.id.Bassplusbtn);
			Button TrebleMinusbtn = (Button) dialog
					.findViewById(R.id.TrebleMinusbtn);
			Button TreblePlusbtn = (Button) dialog
					.findViewById(R.id.TreblePlusbtn);
			final TextView BasstextView = (TextView) dialog
					.findViewById(R.id.BasstextView);
			final TextView TrebleTextView = (TextView) dialog
					.findViewById(R.id.TrebleTextView);
			final SeekBar BassSeekBar = (SeekBar) dialog
					.findViewById(R.id.BassSeekBar);
			final SeekBar TrebleSeekBar = (SeekBar) dialog
					.findViewById(R.id.TrebleSeekBar);

			dialog.getWindow().getAttributes().width = LayoutParams.MATCH_PARENT;
			dialog.getWindow().setBackgroundDrawableResource(
					R.drawable.transparent);
			dialog.show();
			BassMinusbtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (currentBass - 1 > min) {
						currentBass--;
						BassSeekBar.setProgress(currentBass + 7);
						BasstextView.setText(currentBass + "");
						new SendAudioControllCommandAsync(zaudioInZone,
								(byte) 5, (byte) 3, (byte) 1, (byte) 0)
								.execute();
					}
				}
			});

			Bassplusbtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (currentBass + 1 < max) {
						currentBass++;
						BassSeekBar.setProgress(currentBass + 7);
						BasstextView.setText(currentBass + "");
						new SendAudioControllCommandAsync(zaudioInZone,
								(byte) 5, (byte) 3, (byte) 2, (byte) 0)
								.execute();
					}
				}
			});

			TrebleMinusbtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (currentTreble - 1 > min) {
						currentTreble--;
						TrebleSeekBar.setProgress(currentTreble + 7);
						TrebleTextView.setText(currentTreble + "");
						new SendAudioControllCommandAsync(zaudioInZone,
								(byte) 5, (byte) 2, (byte) 1, (byte) 0)
								.execute();
					}
				}
			});

			TreblePlusbtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (currentTreble + 1 < max) {
						currentTreble++;
						TrebleSeekBar.setProgress(currentTreble + 7);
						TrebleTextView.setText(currentTreble + "");
						new SendAudioControllCommandAsync(zaudioInZone,
								(byte) 5, (byte) 2, (byte) 2, (byte) 0)
								.execute();
					}
				}
			});

			break;

		case R.id.album_list_button:

			final Dialog dialog2 = new Dialog(getActivity());
			dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog2.setContentView(R.layout.zaudio_album_list);
			final ListView albumListView = (ListView) dialog2
					.findViewById(R.id.albumListView);
			final Button okButton,
			cancelButton;
			okButton = (Button) dialog2.findViewById(R.id.okBtn);
			cancelButton = (Button) dialog2.findViewById(R.id.cancelBtn);
			dialog2.getWindow().getAttributes().width = LayoutParams.WRAP_CONTENT;
			dialog2.getWindow().getAttributes().height = LayoutParams.WRAP_CONTENT;
			albumListView.setAdapter(new ArrayAdapter<ZAudio_Album>(
					getActivity(),
					android.R.layout.simple_list_item_single_choice,
					android.R.id.text1, albumsList));
			dialog2.show();

			okButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// int position=albumListView.getSelectedItemPosition();

					songsList = new ArrayList<ZAudio_Album>();

					if (currentAlbum != null) {
						songsList = zaudio_DB.getSongWithAlbumNo(currentAlbum
								.getNo(),zones.getZoneID(),current_Source);
						if (songsList != null) {
							if (songsList.size() > 0) {
								songsListView
										.setAdapter(new ArrayAdapter<ZAudio_Album>(
												getActivity(),
												R.layout.zaudio_song_list_item,
												R.id.songNameText, songsList) {
											@Override
											public View getView(int position,
													View convertView,
													ViewGroup parent) {
												View v = super.getView(
														position, convertView,
														parent);
												ImageView icon = (ImageView) v
														.findViewById(R.id.checkImageView);
												TextView songNameText = (TextView) v
														.findViewById(R.id.songNameText);
												if (songsListView
														.isItemChecked(position)) {
													icon.setVisibility(View.VISIBLE);
													songNameText
															.setTextColor(Color.YELLOW);
												} else {
													icon.setVisibility(View.INVISIBLE);
													songNameText
															.setTextColor(Color.WHITE);
												}
												return v;
											}
										});

							} else {
								songsList = new ArrayList<ZAudio_Album>();
								handler.sendEmptyMessage(Read_Album);
							}
						} else {
							songsList = new ArrayList<ZAudio_Album>();
							handler.sendEmptyMessage(Read_Album);
						}
					}
					dialog2.dismiss();
				}
			});

			cancelButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					dialog2.dismiss();
				}
			});
			albumListView.setItemsCanFocus(false);
			albumListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			albumListView
					.setOnItemClickListener(new ListView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							currentAlbumIndex = position;
							currentAlbumNo = albumsList.get(currentAlbumIndex)
									.getNo();
							albumListPositionSelect = currentAlbumIndex;
							currentAlbum = albumsList.get(currentAlbumIndex);

							album_list_button.setText(albumsList.get(
									currentAlbumIndex).getName());
						}
					});
			break;

		default:
			break;
		}
	}

	public class SendAudioControllCommandAsync extends
			AsyncTask<Void, Void, Void> {

		private ZaudioInZone zaudioInZone;
		private byte controllType, para1, para2, para3;

		public SendAudioControllCommandAsync(ZaudioInZone zaudioInZone,
				byte controllType, byte para1, byte para2, byte para3) {
			// TODO Auto-generated constructor stub
			this.zaudioInZone = zaudioInZone;
			this.controllType = controllType;
			this.para1 = para1;
			this.para2 = para2;
			this.para3 = para3;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			new ZAudioUDBSocket().audioControllCommand(zaudioInZone,
					controllType, para1, para2, para3);
			return null;
		}

	}

	public class ReadTotalQTYofBigPackagesforZAudioAlbum extends
			AsyncTask<Void, Void, Void> {

		private byte[] arraybyteBufWithoutAA = null;
		private ZaudioInZone zaudioInZone;

		public ReadTotalQTYofBigPackagesforZAudioAlbum(ZaudioInZone zaudioInZone) {
			// TODO Auto-generated constructor stub

			this.zaudioInZone = zaudioInZone;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new ZAudioUDBSocket()
					.ReadTotalQTYofBigPackagesforZAudioAlbum(
							(byte) zaudioInZone.getSubnetID(),
							(byte) zaudioInZone.getDeviceID(),
							(byte) current_Source);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				totalQuantityOfBigPak = arraybyteBufWithoutAA[10];
				if (totalQuantityOfBigPak > 0) {
					handler.sendEmptyMessage(QTY_of_Big_Packages);
				}
			}

		}

	}

	public class ReadTotalQTYofBigPackagesforZAudioSong extends
			AsyncTask<Void, Void, Void> {

		private byte[] arraybyteBufWithoutAA = null;
		private ZaudioInZone zaudioInZone;
		private ZAudio_Album album;

		public ReadTotalQTYofBigPackagesforZAudioSong(
				ZaudioInZone zaudioInZone, ZAudio_Album album) {
			// TODO Auto-generated constructor stub

			this.zaudioInZone = zaudioInZone;
			this.album = album;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new ZAudioUDBSocket()
					.ReadTotalQTYofBigPackagesforZAudioSong(
							(byte) zaudioInZone.getSubnetID(),
							(byte) zaudioInZone.getDeviceID(),
							(byte) current_Source, (byte) album.getNo());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				totalQuantityOfBigPakSong = arraybyteBufWithoutAA[11];
				album.setBig_Package_No_song(totalQuantityOfBigPakSong);
				if (totalQuantityOfBigPakSong > 0) {
					handler.sendEmptyMessage(Read_Song);
				}
			}

		}

	}

	public class Z_AudioReadAlbum extends AsyncTask<Void, Void, Void> {

		private ZaudioInZone zaudioInZone;

		private byte[] arraybyteBufWithoutAA = null;
		private int currentBigPak;
		private boolean last = false;

		public Z_AudioReadAlbum(ZaudioInZone zaudioInZone, int currentBigPak,
				boolean last) {
			// TODO Auto-generated constructor stub
			this.zaudioInZone = zaudioInZone;
			this.currentBigPak = currentBigPak;
			this.last = last;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new ZAudioUDBSocket().readTotalAlbum(
					(byte) zaudioInZone.getSubnetID(),
					(byte) zaudioInZone.getDeviceID(), (byte) current_Source,
					(byte) currentBigPak);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				int currentBigPakNo = arraybyteBufWithoutAA[12];
				int quntityOfAlbum = arraybyteBufWithoutAA[13];
				int currentIndex = 14;

				for (int i = 0; i < quntityOfAlbum; i++) {
					ZAudio_Album album = new ZAudio_Album();
					album.setBig_Package_No(currentBigPakNo);
					album.setNo(arraybyteBufWithoutAA[currentIndex]);
					currentIndex++;
					int albumNameLength = arraybyteBufWithoutAA[currentIndex];
					currentIndex++;
					StringBuffer stringBuffer = new StringBuffer();
					for (int j = 0; j < albumNameLength; j++) {
						char c = (char) arraybyteBufWithoutAA[currentIndex + j];
						stringBuffer.append(c);
					}
					album.setName(stringBuffer.toString());
					albumsList.add(album);
					zaudio_DB.insertAlbum(album,zones.getZoneID(),current_Source);
					currentIndex += albumNameLength;
				}
				album_list_button.setText(albumsList.get(currentAlbumIndex)
						.getName());
				currentAlbumNo = albumsList.get(currentAlbumIndex).getNo();

				if (last) {
					handler.sendEmptyMessage(Read_Album);
				}

			}

		}

	}

	public class Z_AudioReadSongs extends AsyncTask<Void, Void, Void> {

		private ZaudioInZone zaudioInZone;
		private ZAudio_Album album;
		private byte[] arraybyteBufWithoutAA = null;
		private boolean last = false;
		private int Package_No_song;
		private boolean showProgress;
		private ProgressDialog pd;

		public Z_AudioReadSongs(ZaudioInZone zaudioInZone, ZAudio_Album album,
				boolean last, int Package_No_song, boolean showProgress) {
			// TODO Auto-generated constructor stub
			this.zaudioInZone = zaudioInZone;
			this.album = album;
			this.last = last;
			this.Package_No_song = Package_No_song;
			this.showProgress = showProgress;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			if (showProgress) {
				pd = new ProgressDialog(getActivity());
				// pd.setMessage("Please Wait");
				pd.setCancelable(true);
				pd.show();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			arraybyteBufWithoutAA = new ZAudioUDBSocket().readTotalAlbumSong(
					(byte) zaudioInZone.getSubnetID(),
					(byte) zaudioInZone.getDeviceID(), (byte) current_Source,
					(byte) album.getNo(), (byte) Package_No_song);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (arraybyteBufWithoutAA != null) {
				int quntityOfsongs = arraybyteBufWithoutAA[14];
				int currentIndex = 15;

				for (int i = 0; i < quntityOfsongs; i++) {
					ZAudio_Album album = new ZAudio_Album();
					album.setNo(this.album.getNo());
					album.setSongNoHigh(arraybyteBufWithoutAA[currentIndex]);
					currentIndex++;
					album.setSongNoLow(arraybyteBufWithoutAA[currentIndex]);

					currentIndex++;
					int albumNameLength = arraybyteBufWithoutAA[currentIndex];
					currentIndex++;
					String stringBuffer = "";
					for (int j = 0; j < albumNameLength; j++) {
						char c = (char) arraybyteBufWithoutAA[currentIndex + j];
						stringBuffer += c;
					}
					try {
						album.setName(new String(stringBuffer.getBytes(),
								"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						album.setName(stringBuffer);
					}
					songsList.add(album);
					zaudio_DB.insertSong(album,zones.getZoneID(),current_Source);
					currentIndex += albumNameLength;
				}

				if (last) {
					songsListView.setAdapter(new ArrayAdapter<ZAudio_Album>(
							getActivity(), R.layout.zaudio_song_list_item,
							R.id.songNameText, songsList) {
						@Override
						public View getView(int position, View convertView,
								ViewGroup parent) {
							View v = super.getView(position, convertView,
									parent);
							ImageView icon = (ImageView) v
									.findViewById(R.id.checkImageView);
							TextView songNameText = (TextView) v
									.findViewById(R.id.songNameText);
							if (songsListView.isItemChecked(position)) {
								icon.setVisibility(View.VISIBLE);
								songNameText.setTextColor(Color.YELLOW);
							} else {
								icon.setVisibility(View.INVISIBLE);
								songNameText.setTextColor(Color.WHITE);
							}
							return v;
						}
					});

				}
				if (showProgress) {
					pd.dismiss();
				}
			}

		}

	}

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				if (songsList == null || songsList.size() == 0) {
					albumsList = zaudio_DB.getAllAlbum(zones.getZoneID(),current_Source);
					if (albumsList != null) {
						if (albumsList.size() > 0) {

							album_list_button.setText(albumsList.get(0)
									.getName());
							currentAlbum = albumsList.get(0);
							songsList = zaudio_DB.getSongWithAlbumNo(albumsList
									.get(0).getNo(),zones.getZoneID(),current_Source);
							if (songsList != null) {
								if (songsList.size() > 0) {
									songsListView
											.setAdapter(new ArrayAdapter<ZAudio_Album>(
													getActivity(),
													R.layout.zaudio_song_list_item,
													R.id.songNameText,
													songsList) {
												@Override
												public View getView(
														int position,
														View convertView,
														ViewGroup parent) {
													View v = super
															.getView(
																	position,
																	convertView,
																	parent);
													ImageView icon = (ImageView) v
															.findViewById(R.id.checkImageView);
													TextView songNameText = (TextView) v
															.findViewById(R.id.songNameText);
													if (songsListView
															.isItemChecked(position)) {
														icon.setVisibility(View.VISIBLE);
														songNameText
																.setTextColor(Color.YELLOW);
													} else {
														icon.setVisibility(View.INVISIBLE);
														songNameText
																.setTextColor(Color.WHITE);
													}
													return v;
												}
											});

								} else {
									currentAlbumIndex = 0;
									songsList = new ArrayList<ZAudio_Album>();
									handler.sendEmptyMessage(Read_Album);
								}
							} else {
								currentAlbumIndex = 0;
								songsList = new ArrayList<ZAudio_Album>();
								handler.sendEmptyMessage(Read_Album);
							}
						} else {
							albumsList = new ArrayList<ZAudio_Album>();
							new ReadTotalQTYofBigPackagesforZAudioAlbum(
									zaudioInZone).execute();
						}
					} else {
						albumsList = new ArrayList<ZAudio_Album>();
						new ReadTotalQTYofBigPackagesforZAudioAlbum(
								zaudioInZone).execute();
					}
				}
				break;
			case QTY_of_Big_Packages:
				for (int i = 1; i <= totalQuantityOfBigPak; i++) {
					if (i == totalQuantityOfBigPak) {
						new Z_AudioReadAlbum(zaudioInZone, i, true).execute();
					} else {

						new Z_AudioReadAlbum(zaudioInZone, i, false).execute();
					}
				}

				break;
			case Read_Album:
				
				currentAlbum = albumsList.get(currentAlbumIndex);
				new ReadTotalQTYofBigPackagesforZAudioSong(zaudioInZone,
						albumsList.get(currentAlbumIndex)).execute();

			
				break;
			case Read_Song:
				int pakquantity = albumsList.get(currentAlbumIndex)
						.getBig_Package_No_song();
				for (int j = 1; j <= pakquantity; j++) {
					if (j == pakquantity) {
						new Z_AudioReadSongs(zaudioInZone,
								albumsList.get(currentAlbumIndex), true, j,
								true).execute();
					} else {

						new Z_AudioReadSongs(zaudioInZone,
								albumsList.get(currentAlbumIndex), false, j,
								true).execute();
					}
				}

				break;
			default:
				break;
			}
		}
	};
}
