package com.roka.smarthomeg4;

import java.util.ArrayList;

import com.roka.smarthomeg4.adapter.MacroButtonAdapter;
import com.roka.smarthomeg4.business.MacroButton;
import com.roka.smarthomeg4.database.dbconnection.MacroButtonDB;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

public class MacroActionsActivity extends Activity {

	private GridView macroGridView;
	private ArrayList<MacroButton> macList;
	private ImageView backImageView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_macro_actions);
		macList=new MacroButtonDB(this).getAllMacroButton();
		macroGridView=(GridView)findViewById(R.id.macroGridView);
		backImageView = (ImageView) findViewById(R.id.backImageView);
		backImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		macroGridView.setAdapter(new MacroButtonAdapter(this, macList));
		
		
		
		
	}

	
}
