package com.example.mds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Task;
import com.example.model.TaskClasses;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;

public class MainActivity extends Activity {

	TabHost tab_host;
	ListView list_task;
	Resources resources;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		//		R.layout.title_main);

		DataFactory.initalization();
		resources = this.getResources();

		setupTables();
	} 

	private void setupTables() {
		tab_host = (TabHost) this.findViewById(R.id.tab_host);
		tab_host.setup();

		LayoutInflater inflater_tab = LayoutInflater.from(this);
		inflater_tab.inflate(R.layout.activity_task,
				tab_host.getTabContentView());
		inflater_tab.inflate(R.layout.activity_message,
				tab_host.getTabContentView());
		inflater_tab.inflate(R.layout.activity_setting,
				tab_host.getTabContentView());

		tab_host.addTab(tab_host
				.newTabSpec("Task")
				.setIndicator("",
						resources.getDrawable(R.drawable.device_access_storage))
				.setContent(R.id.activity_task));
		tab_host.addTab(tab_host
				.newTabSpec("Message")
				.setIndicator("",
						resources.getDrawable(R.drawable.content_email))
				.setContent(R.id.activity_message));
		tab_host.addTab(tab_host
				.newTabSpec("Setting")
				.setIndicator("",
						resources.getDrawable(R.drawable.action_settings))
				.setContent(R.id.activity_setting));

		setupTaskControl();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void setupTaskControl() {
		List<Task> data = DataFactory.getTaskes();
		TaskListViewAdapter adapter = new TaskListViewAdapter(this, data);

		list_task = (ListView) this.findViewById(R.id.list_task);
		list_task.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_exit:
			this.finish();
			return true;
		case R.id.menu_task_new:
			this.startActivity(new Intent(this, CreateTaskActivity.class));
			return true;
		}

		return false;
	}
}
