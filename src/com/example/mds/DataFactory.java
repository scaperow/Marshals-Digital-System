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

		// �ɷ�����
		People plisi = new People();
		plisi.context = "���ɷ�����";
		plisi.gender = true;
		plisi.id = 1;
		plisi.name = "����";
		plisi.age = 34;
		plisi.statue = "����";
		plisi.photo = R.drawable.people1;

		peoples.put(plisi.id, plisi);

		HashMap<String, Integer> peopleReference = new HashMap<String, Integer>();
		peopleReference.put("ʡͨ��������", plisi.id);

		// �����ִ��
		Date startTime = new Date();
		Date endTime = new Date();
		endTime.setHours(2);
		Task t1 = Task.createDuty(startTime, endTime, "�����");
		t1.id = 2;
		t1.responsibility = "����";
		t1.publisher = "���Լ�";

		// ��������
		startTime = endTime;
		endTime.setHours(2);
		Task t2 = Task.createWatch(startTime, endTime, peopleReference);
		t2.id = 9;
		t2.responsibility = "��һ��";
		t2.publisher = "��ΰ";

		// ׷������
		startTime = endTime;
		endTime.setHours(12);
		Task t3 = Task.createPursuit(startTime, peopleReference);
		t3.id = 10;
		t3.responsibility = "��һ�У�����";
		t3.publisher = "��ΰ";

		// �Ĵ�����Э��
		startTime = endTime;
		endTime.setHours(20);
		Task t4 = Task.createInvestigation(startTime, endTime, "�Ĵ�����");
		t4.id = 3;
		t4.responsibility = "��һ��";
		t4.publisher = "���Լ�";

		// ��������Э��
		startTime = endTime;
		endTime.setHours(20);
		Task t5 = Task.createInvestigation(startTime, endTime, "��������");
		t5.id = 4;
		t5.responsibility = "��һ�У�����";
		t5.publisher = "��ΰ";

		// ��������
		startTime = endTime;
		endTime.setHours(20);
		Task t6 = Task.createSecurity(startTime, endTime, "�׶�����");
		t6.id = 5;
		t6.responsibility = "ȫ����ְ�񷨾�";
		t6.publisher = "��ΰ";

		// ��������
		startTime = endTime;
		endTime.setHours(20);
		Task t7 = Task.createSecurity(startTime, endTime, "�׶�����");
		t7.id = 6;
		t7.responsibility = "��һ��";
		t7.publisher = "��ΰ";

		// ����ʡͥ
		startTime = endTime;
		startTime.setHours(-20);
		endTime.setHours(0);
		Task t8 = Task.createExecutiveTribunal(startTime, endTime, "����ʡͥ");
		t8.id = 7;
		t8.responsibility = "��һ��,��÷";
		t8.publisher = "���Լ�";

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
		m1.context = "�������񾯱��棬�����ɷ��ڳ������¾Ƶ긽�����";
		m1.publishTime = new Date();
		m1.publisher = "����";

		Memo m2 = new Memo();
		m2.taskId = t2.id;
		m2.id = 3;
		m2.context = "����ð����η�������һ�У�����";
		m2.publishTime = new Date();
		m2.publisher = "��һ��";

		Memo m3 = new Memo();
		m3.taskId = t2.id;
		m3.id = 4;
		m3.context = "2010��Ų�û�ɽ��˾720�򹫿���Ǳ�ӡ�";
		m3.publishTime = new Date();
		m3.publisher = "��һ��";

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
