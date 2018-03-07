package com.example.mds;

import java.util.List;

import com.example.model.Memo;
import com.example.model.People;
import com.example.model.Task;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TaskDetailsActivity extends Activity implements OnClickListener {

	public static Task model;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taskdetails);
		resources = this.getResources();
		setupControls();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_task, menu);
		return true;
	}

	Resources resources = null;
	Button button_memo_add;
	Button button_people_details;
	TextView text_people_short_details;
	TextView text_people_statue;
	ListView list_memo;
	TextView text_responsibility;
	ImageView img_people_photo;
	LinearLayout layout_people_information;
	LinearLayout layout_memo;
	LinearLayout layout_task;
	LinearLayout layout_people;

	public void setupControls() {

		button_memo_add = (Button) findViewById(R.id.button_add_memo);
		button_people_details = (Button) findViewById(R.id.button_details);
		text_people_short_details = (TextView) findViewById(R.id.people_short_details);
		list_memo = (ListView) findViewById(R.id.list_memo);
		text_people_statue = (TextView) findViewById(R.id.people_statue);
		img_people_photo = (ImageView) findViewById(R.id.img_people_photo);
		layout_people_information = (LinearLayout) findViewById(R.id.layout_people_information);
		layout_memo = (LinearLayout) findViewById(R.id.layout_memo);
		layout_task = (LinearLayout) findViewById(R.id.layout_task);
		layout_people = (LinearLayout) findViewById(R.id.layout_people);

		button_memo_add.setOnClickListener(this);
		button_people_details.setOnClickListener(this);

		bindTaskModel();
		bindPeopleModel();
		bindMemoModel();
	}

	private void setTextToView(LinearLayout parent, String name, String value) {
		if (name == null || name == "" || value == null || value == "") {
			return;
		}

		String text = name + " : " + value;

		TextView text_child = new TextView(this);
		text_child.setText(text);
		text_child.setTextSize(18);
		parent.addView(text_child);
	}

	private void setTextToView(LinearLayout parent, String text) {
		if (text == null || text == "") {
			return;
		}
		TextView text_child = new TextView(this);
		text_child.setText(text);
		text_child.setTextSize(18);
		parent.addView(text_child);
	}

	private void setLinkTextToView(LinearLayout parent, String name,
			String value, OnClickListener listener) {
		if (name == null || name == "" || value == null || value == "") {
			return;
		}

		TextView text_child = new TextView(this);
		String htmlLinkText = name + " : " + "<a href='#' mce_href='#'>"
				+ value + "</a>";
		text_child.setText(Html.fromHtml(htmlLinkText));
		text_child.setMovementMethod(LinkMovementMethod.getInstance());
		CharSequence text = text_child.getText();
		if (text instanceof Spannable) {
			int end = text.length();
			Spannable sp = (Spannable) tv.getText();
			URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(text);
			style.clearSpans();// should clear old spans
			for (URLSpan url : urls) {
				URLSpan myURLSpan = new URLSpan(url.getURL());
				style.setSpan(myURLSpan, sp.getSpanStart(url),
						sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			text_child.setText(style);
		}
	}

	private void bindTaskModel() {
		setTextToView(layout_task, "任        务", model.title);
		setTextToView(layout_task, "地        点", model.address);
		setTextToView(layout_task, "状        态", model.statue);
		setTextToView(layout_task, "责任法警", model.description);

		if (model.publisher == "我自己") {
			setTextToView(layout_task, "发布人    ", model.publisher);
		} else {
			final String[] contacts = new String[] {
					"查看 " + model.publisher + "信息", "呼叫 " + model.publisher };

			setLinkTextToView(layout_task, "发布人    ", model.publisher,
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(TaskDetailsActivity.this)
									.setItems(contacts,
											new Dialog.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													switch (which) {
													case 0:
														break;

													case 1:
														Intent intent = new Intent(
																Intent.ACTION_DIAL,
																Uri.parse("tel:15353777650"));
														TaskDetailsActivity.this
																.startActivity(intent);
														break;
													}
												}

											});
						}

					});
		}

		if (model.endTime != null)
			setTextToView(layout_task, "结束时间", model.endTime.toLocaleString());
		if (model.startTime != null)
			setTextToView(layout_task, "开始时间", model.startTime.toLocaleString());
	}

	private void bindPeopleModel() {
		if (model.objectReference == null) {
			layout_people.setVisibility(View.GONE);
		} else {
			Integer[] p1_ids = new Integer[model.objectReference.size()];
			model.objectReference.values().toArray(p1_ids);
			if (p1_ids.length == 0) {
				layout_people_information.setVisibility(View.GONE);
			} else {
				layout_people_information.setVisibility(View.VISIBLE);
				People p1 = DataFactory.getPeople(p1_ids[0]);

				String gender = "男";
				if (p1.gender == false) {
					gender = "女";
				}
				text_people_short_details.setText(p1.name + "," + gender + ","
						+ p1.age);
				text_people_statue.setText(p1.statue);
				img_people_photo.setImageBitmap(BitmapFactory.decodeResource(
						resources, p1.photo));
			}
		}
	}

	private void bindMemoModel() {
		List<Memo> memos = DataFactory.getMemo(model.id);

		if (memos == null || memos.size() == 0) {
			list_memo.setVisibility(View.GONE);
			setTextToView(layout_memo, "暂无备注信息");
		} else {
			MemoListViewAdapter adapter = new MemoListViewAdapter(this, memos);
			list_memo.setAdapter(adapter);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private class URLSpan extends ClickableSpan {

		private String url;
		private OnClickListener processor;
		URLSpan(String url,OnClickListener ner) {
			this.url = url;
			this.processor = listener;
		}

		@Override
		public void onClick(View widget) {
			// TODO Auto-generated method stub

		}
	}
}
