package com.study.mybat.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.study.mybat.dto.TodoList;
import com.study.mybat.mapper.TodoListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoListService{
    
    @NonNull
    private TodoListMapper mapper;
    
    public int post(TodoList newTodoItem) {
        return mapper.postTodo ( newTodoItem );
    }
    
    public List<TodoList> getTodoList () throws Exception {
        return mapper.getTodo();
    }
    
    public TodoList get(String key) throws Exception {
        return mapper.getByKey ( key );
    }
    
    public boolean put(String key, TodoList todo) {
        int ans = mapper.put ( key, todo );
        return ans > 0;
    }
    
    public boolean del(String key) {
        return mapper.del( key ) > 0;
    }
    
    public boolean delTodoList () {
        return mapper.delTodoList() > 0;
    }

}

