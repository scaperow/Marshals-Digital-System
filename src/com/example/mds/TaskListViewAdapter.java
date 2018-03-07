package com.example.mds;

import java.text.DateFormat;
import java.util.List;

import com.example.model.*;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskListViewAdapter extends BaseAdapter {

	public Context context;
	public List<Task> taskes;
	public LayoutInflater inflater;
	public static int COLOR_TASK_DOING = Color.rgb(180, 220, 180);
	public static int COLOR_TASK_READY = Color.rgb(220, 200, 200);
	public static int COLOR_TASK_DELAY = Color.rgb(200, 220, 220);
	public static int COLOR_TASK_DONE = Color.rgb(220, 220, 220);

	public TaskListViewAdapter(Context context, List<Task> taskes) {
		this.context = context;
		this.taskes = taskes;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return taskes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return taskes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return taskes.get(position).id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		TaskHolder holder = null;

		if (convertView == null) {
			holder = new TaskHolder();
			convertView = inflacteView(position, convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (TaskHolder) convertView.getTag();
		}

		holder.model = taskes.get(position);
		setData(holder);
		setClickListener(convertView);
		// if (position % 2 == 0) {
		// convertView.setBackgroundColor(Color.rgb(255, 255, 255));
		// } else {
		// convertView.setBackgroundColor(Color.parseColor("#DEF3F7"));
		// }

		return convertView;
	}

	String[] menu = new String[] { "详情", "完成任务", "开始任务" };

	private void setClickListener(View convertView) {
		TaskHolder holder = (TaskHolder) convertView.getTag();
		final Task task = holder.model;
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (task.statue == "准备") {
					menu = new String[] { "详情", "开始任务" };
				} else if (task.statue == "进行中") {
					menu = new String[] { "详情", "完成任务", "取消任务" };
				}
				// TODO Auto-generated method stub
				new AlertDialog.Builder(context).setTitle("操作")
						.setItems(menu, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								switch (which) {
								case 0:
									TaskDetailsActivity.model = task;
									context.startActivity(new Intent(context,
											TaskDetailsActivity.class)
											.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

									break;

								case 1:

									break;
								}
							}

						}).show();
			}

		});
	}

	private View inflacteView(int position, View convertView, TaskHolder holder) {
		convertView = inflater.inflate(R.layout.template_task, null);
		holder.task_classes = (TextView) convertView
				.findViewById(R.id.task_type);
		holder.task_title = (TextView) convertView
				.findViewById(R.id.task_title);
		holder.task_statue = (TextView) convertView
				.findViewById(R.id.task_statue);
		holder.task_date = (TextView) convertView.findViewById(R.id.task_time);
		return convertView;
	}

	private void setData(TaskHolder holder) {
		Task task = holder.model;

		if (task.classes == TaskClasses.Service) {
			holder.task_classes.setText("送达");
		} else if (task.classes == TaskClasses.Duty) {
			holder.task_classes.setText("执勤");
		} else if (task.classes == TaskClasses.Escort) {
			holder.task_classes.setText("押解");
		} else if (task.classes == TaskClasses.ExecutiveTribunal) {
			holder.task_classes.setText("执庭");
		} else if (task.classes == TaskClasses.Inspect) {
			holder.task_classes.setText("巡查");
		} else if (task.classes == TaskClasses.Investigation) {
			holder.task_classes.setText("协查");
		} else if (task.classes == TaskClasses.InvestigatorsWorkspace) {
			holder.task_classes.setText("办案工作区");
		} else if (task.classes == TaskClasses.Pursuit) {
			holder.task_classes.setText("追逃");
		} else if (task.classes == TaskClasses.Security) {
			holder.task_classes.setText("安检");
		} else if (task.classes == TaskClasses.Watch) {
			holder.task_classes.setText("看管");
		}
		holder.task_statue.setText(task.statue);
		holder.task_title.setText(task.title);
		holder.task_date.setText(DateFormat.getDateInstance(DateFormat.SHORT)
				.format(task.startTime));

	}

	private void setBackgroundColor(Task task, View view) {
		// if (task.statue == TaskStatues.Doing) {
		// view.setBackgroundColor(COLOR_TASK_DOING);
		// } else if (task.statue == TaskStatues.Done) {
		// view.setBackgroundColor(COLOR_TASK_DONE);
		// } else if (task.statue == TaskStatues.Ready) {
		// view.setBackgroundColor(COLOR_TASK_READY);
		// } else if (task.statue == TaskStatues.Delay) {
		// view.setBackgroundColor(COLOR_TASK_DELAY);
		// }
	}

	class TaskHolder {
		public TextView task_statue;
		public TextView task_title;
		public TextView task_classes;
		public TextView task_date;

		public Task model;
	}
}
