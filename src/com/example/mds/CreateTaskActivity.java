package com.example.mds;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class CreateTaskActivity extends Activity implements OnClickListener {

	Spinner spinner_classes;
	ImageButton button_finish;
	AutoCompleteTextView text_peoples;
	AutoCompleteTextView text_address;

	private static final String[] peoples = { "李四", "孙航" };
	private static final String[] address = { "西安市", "钟楼", "北大街" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);

		setupControls();
		bindSource();
	}

	private void setupControls() {
		spinner_classes = (Spinner) findViewById(R.id.spinner_classes);
		button_finish = (ImageButton) findViewById(R.id.button_finish);
		text_peoples = (AutoCompleteTextView) findViewById(R.id.text_people);
		text_address = (AutoCompleteTextView) findViewById(R.id.text_address);
	}

	private void bindSource() {
		// Spinner
		String[] classes = new String[] { "执勤", "看管", "送达", "押解", "追逃", "协查",
				"巡查", "安检", "执庭", "办案工作区", "其他" };
		SpinnerAdapter adapter = new SpinnerAdapter(this,
				android.R.layout.simple_spinner_item, classes);

		spinner_classes.setAdapter(adapter);

		// Back
		button_finish.setOnClickListener(this);

		// Autocomplete
		ArrayAdapter<String> adapter_peoples = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, peoples);
		text_peoples.setAdapter(adapter_peoples);
		text_peoples.setThreshold(1);
		
		ArrayAdapter<String> adapter_address = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, address);
		text_address.setAdapter(adapter_address);
		text_address.setThreshold(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_create_task, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {


		case R.id.button_finish:
			this.finish();
			break;
		}
	}
}
