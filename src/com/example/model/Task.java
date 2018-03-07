package com.example.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.mds.DataFactory;

public class Task {
	public Integer id;
	public String statue;
	public TaskClasses classes;
	public String description;
	public String title;
	public Date startTime;
	public Date endTime;
	public HashMap<String, Integer> objectReference;
	public HashMap<Integer, String> gpsReference;
	public List<Integer> audioReferfence;
	public List<Integer> videoReference;
	public String address;
	public String responsibility;
	public String publisher;
	public Date publishTime;

	public Task(Date startTime, Date endTime, TaskClasses classes) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.classes = classes;
		this.responsibility = "��һ�У�����";
		this.publishTime = new Date();
	}

	public Task(Date startTime, TaskClasses classes) {
		this.startTime = startTime;
		this.classes = classes;
		this.responsibility = "��һ�У�����";
		this.publishTime = new Date();
	}

	public static Task createDuty(Date startTime, Date endTime, String address) {
		Task task = new Task(startTime, endTime, TaskClasses.Duty);
		task.statue = "׼��";
		task.address = address;
		task.title = address + "ִ��";

		return task;
	}

	public static Task createWatch(Date startTime, Date endTime,
			HashMap<String, Integer> objectReference) {

		Task task = new Task(startTime, endTime, TaskClasses.Watch);
		task.statue = "׼��";
		task.objectReference = objectReference;
		task.title = "����������(4��)";

		return task;
	}

	public static Task createPursuit(Date startTime,
			HashMap<String, Integer> objectReference) {
		Task task = new Task(startTime, TaskClasses.Pursuit);
		task.statue = "������";
		task.title = "׷��" + DataFactory.getPeople(getValue(objectReference,0)).name;

		return task;
	}
		
	private static Integer getValue(HashMap<String,Integer> data,int index){
		Integer[] values = new Integer[data.size()];
		data.values().toArray(values);
		return values[index];
	}

	public static Task createInvestigation(Date startTime, Date endTime,
			String address) {
		Task task = new Task(startTime, TaskClasses.Pursuit);
		task.statue = "׼��";
		task.address = "�Ĵ�����Э��";
		task.title = address + "Э��";

		return task;
	}

	public static Task createSecurity(Date startTime, Date endTime,
			String address) {
		Task task = new Task(startTime, endTime, TaskClasses.Security);
		task.statue ="������";
		task.address = address;
		task.title = address + "����";

		return task;
	}

	public static Task createExecutiveTribunal(Date startTime, Date endTime,
			String address) {
		Task task = new Task(startTime, endTime, TaskClasses.ExecutiveTribunal);
		task.statue = "׼��";
		task.address = address;
		task.title = address + "ִͥ";
		
		return task;
	}
	
	// ����ֳ���
	// ��������������
	// ׷��ʡͨ��������
	// Э��
}
