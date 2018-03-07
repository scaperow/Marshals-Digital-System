package com.example.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Memo {
	public Integer id;
	public Integer taskId;
	public String context;
	public String publisher;
	public Date publishTime;
	public HashMap<String,Integer> objectReference;
	public HashMap<String,Integer> gpsReference;
	public List<Integer> videoReference;
	public List<Integer> audeoReference;
}
