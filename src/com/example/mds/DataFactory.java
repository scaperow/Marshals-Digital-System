package com.example.mds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.model.Memo;
import com.example.model.People;
import com.example.model.Task;

public class DataFactory {

	static HashMap<Integer, Task> taskes = new HashMap<Integer, Task>();
	static HashMap<Integer, List<Memo>> memos = new HashMap<Integer, List<Memo>>();
	static HashMap<Integer, People> peoples = new HashMap<Integer, People>();
	static String initingLock = "InitializationLock";

	public static void initalization() {

		// 疑犯李四
		People plisi = new People();
		plisi.context = "嫌疑犯李四";
		plisi.gender = true;
		plisi.id = 1;
		plisi.name = "李四";
		plisi.age = 34;
		plisi.statue = "在逃";
		plisi.photo = R.drawable.people1;

		peoples.put(plisi.id, plisi);

		HashMap<String, Integer> peopleReference = new HashMap<String, Integer>();
		peopleReference.put("省通缉犯李四", plisi.id);

		// 东大街执勤
		Date startTime = new Date();
		Date endTime = new Date();
		endTime.setHours(2);
		Task t1 = Task.createDuty(startTime, endTime, "东大街");
		t1.id = 2;
		t1.responsibility = "赵乐";
		t1.publisher = "我自己";

		// 看管李四
		startTime = endTime;
		endTime.setHours(2);
		Task t2 = Task.createWatch(startTime, endTime, peopleReference);
		t2.id = 9;
		t2.responsibility = "陈一男";
		t2.publisher = "李伟";

		// 追逃李四
		startTime = endTime;
		endTime.setHours(12);
		Task t3 = Task.createPursuit(startTime, peopleReference);
		t3.id = 10;
		t3.responsibility = "陈一男，刘欣";
		t3.publisher = "李伟";

		// 四川绵阳协查
		startTime = endTime;
		endTime.setHours(20);
		Task t4 = Task.createInvestigation(startTime, endTime, "四川绵阳");
		t4.id = 3;
		t4.responsibility = "陈一男";
		t4.publisher = "我自己";

		// 甘肃兰州协查
		startTime = endTime;
		endTime.setHours(20);
		Task t5 = Task.createInvestigation(startTime, endTime, "甘肃兰州");
		t5.id = 4;
		t5.responsibility = "陈一男，刘欣";
		t5.publisher = "李伟";

		// 机场安检
		startTime = endTime;
		endTime.setHours(20);
		Task t6 = Task.createSecurity(startTime, endTime, "首都机场");
		t6.id = 5;
		t6.responsibility = "全体无职务法警";
		t6.publisher = "李伟";

		// 机场安检
		startTime = endTime;
		endTime.setHours(20);
		Task t7 = Task.createSecurity(startTime, endTime, "首都机场");
		t7.id = 6;
		t7.responsibility = "陈一男";
		t7.publisher = "李伟";

		// 王五省庭
		startTime = endTime;
		startTime.setHours(-20);
		endTime.setHours(0);
		Task t8 = Task.createExecutiveTribunal(startTime, endTime, "王五省庭");
		t8.id = 7;
		t8.responsibility = "陈一男,刘梅";
		t8.publisher = "我自己";

		taskes.put(t1.id, t1);
		taskes.put(t2.id, t2);
		taskes.put(t3.id, t3);
		taskes.put(t4.id, t4);
		taskes.put(t5.id, t5);
		taskes.put(t6.id, t6);
		taskes.put(t7.id, t7);
		taskes.put(t8.id, t8);

		Memo m1 = new Memo();
		m1.taskId = t2.id;
		m1.id = 2;
		m1.context = "商洛市民警报告，发现疑犯在城区红衣酒店附近活动。";
		m1.publishTime = new Date();
		m1.publisher = "赵乐";

		Memo m2 = new Memo();
		m2.taskId = t2.id;
		m2.id = 3;
		m2.context = "分配该案责任法警：陈一男，赵乐";
		m2.publishTime = new Date();
		m2.publisher = "陈一男";

		Memo m3 = new Memo();
		m3.taskId = t2.id;
		m3.id = 4;
		m3.context = "2010年挪用华山公司720万公款，随后潜逃。";
		m3.publishTime = new Date();
		m3.publisher = "陈一男";

		List<Memo> ms = new ArrayList<Memo>();
		ms.add(m1);
		ms.add(m2);
		ms.add(m3);

		memos.put(t2.id, ms);

	}

	public static List<Task> getTaskes() {

		Task[] ts = new Task[taskes.size()];
		taskes.values().toArray(ts);
		List<Task> result = new ArrayList<Task>();
		for (Task t : ts) {
			result.add(t);
		}

		return result;
	}

	public static People getPeople(Integer id) {
		return peoples.get(id);
	}

	public static Task getTask(Integer id) {
		return taskes.get(id);
	}

	public static List<Memo> getMemo(Integer id) {
		if (memos.containsKey(id)) {
			return memos.get(id);
		} else {
			return new ArrayList<Memo>();
		}
	}
}
