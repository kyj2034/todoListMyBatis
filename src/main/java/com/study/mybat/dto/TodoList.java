package com.study.mybat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class TodoList {
	int id;
	String todo;
	boolean finish;
	String key;
}