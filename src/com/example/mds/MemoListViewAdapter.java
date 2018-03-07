package com.example.mds;

import java.util.List;

import com.example.model.Memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MemoListViewAdapter extends BaseAdapter {

	List<Memo> memos = null;
	Context context = null;
	LayoutInflater inflater = null;

	public MemoListViewAdapter(Context context, List<Memo> memos) {
		this.context = context;
		this.memos = memos;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return memos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return memos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return Long.MIN_VALUE;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Memo memo = memos.get(position);

		MemoHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.template_memo, null);
			holder = new MemoHolder();
			holder.model = memo;
			holder.memo_content = (TextView) convertView
					.findViewById(R.id.memo_content);
			holder.memo_publish = (TextView) convertView
					.findViewById(R.id.memo_publish);
			convertView.setTag(holder);
		} else {
			holder = (MemoHolder) convertView.getTag();
		}

		holder.memo_content.setText(memo.context);
		holder.memo_publish.setText(memo.publisher + " "
				+ memo.publishTime.toLocaleString() );

		return convertView;
	}
}

class MemoHolder {
	public Memo model;
	public TextView memo_content;
	public TextView memo_publish;
}
