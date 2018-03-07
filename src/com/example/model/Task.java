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
		this.responsibility = "陈一男，赵乐";
		this.publishTime = new Date();
	}

	public Task(Date startTime, TaskClasses classes) {
		this.startTime = startTime;
		this.classes = classes;
		this.responsibility = "陈一男，赵乐";
		this.publishTime = new Date();
	}

	public static Task createDuty(Date startTime, Date endTime, String address) {
		Task task = new Task(startTime, endTime, TaskClasses.Duty);
		task.statue = "准备";
		task.address = address;
		task.title = address + "执勤";

		return task;
	}

	public static Task createWatch(Date startTime, Date endTime,
			HashMap<String, Integer> objectReference) {

		Task task = new Task(startTime, endTime, TaskClasses.Watch);
		task.statue = "准备";
		task.objectReference = objectReference;
		task.title = "看管嫌疑人(4名)";

		return task;
	}

	public static Task createPursuit(Date startTime,
			HashMap<String, Integer> objectReference) {
		Task task = new Task(startTime, TaskClasses.Pursuit);
		task.statue = "进行中";
		task.title = "追逃" + DataFactory.getPeople(getValue(objectReference,0)).name;

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
		task.statue = "准备";
		task.address = "四川绵阳协查";
		task.title = address + "协查";

		return task;
	}

	public static Task createSecurity(Date startTime, Date endTime,
			String address) {
		Task task = new Task(startTime, endTime, TaskClasses.Security);
		task.statue ="进行中";
		task.address = address;
		task.title = address + "安检";

		return task;
	}

	public static Task createExecutiveTribunal(Date startTime, Date endTime,
			String address) {
		Task task = new Task(startTime, endTime, TaskClasses.ExecutiveTribunal);
		task.statue = "准备";
		task.address = address;
		task.title = address + "执庭";
		
		return task;
	}
	
	// 东大街出勤
	// 看管嫌疑人李四
	// 追逃省通缉犯李四
	// 协查
}
