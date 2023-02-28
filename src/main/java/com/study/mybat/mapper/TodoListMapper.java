package com.study.mybat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.study.mybat.dto.TodoList;

@Mapper
public interface TodoListMapper {
    /**
     * 
     * <pre>
     * Id 값으로 TodoList 를 Select.
     * 
     * </pre>
     * @param id
     * @return TodoList
     * @throws Exception
     */
	@Select("select * from todolist where key = #{key}")
    @Results(id="todoitemResultMap", value = {
                    @Result(property = "id", column = "id", id = true, jdbcType = JdbcType.INTEGER),
                    @Result(property = "todo", column = "todo", jdbcType = JdbcType.VARCHAR),
                    @Result(property = "finish", column = "finish", jdbcType = JdbcType.BOOLEAN),
                    @Result(property = "key", column = "key", jdbcType = JdbcType.VARCHAR)
    })
	public TodoList getByKey(@Param("key") String key) throws Exception;
	
	
	/**
	 * 
	 * <pre>
	 * 전체 TodoList 를 Select.
	 * 
	 * </pre>
	 * @return List<TodoList>
	 * @throws Exception
	 */
	@Select("select * from todolist")
    @Results(id="todolistResultMap", value = {
                    @Result(property = "id", column = "id", id = true, jdbcType = JdbcType.INTEGER),
                    @Result(property = "todo", column = "todo", jdbcType = JdbcType.VARCHAR),
                    @Result(property = "finish", column = "finish", jdbcType = JdbcType.BOOLEAN),
                    @Result(property = "key", column = "key", jdbcType = JdbcType.VARCHAR),
    })
	public List<TodoList> getTodo() throws Exception;
	
	
	
	/**
	 * 
	 * <pre>
	 * TODO 등록.
	 * 
	 * </pre>
	 * @param todoitem
	 * @return int
	 * 
	 * 어떤 컬럼에 어떤 값을 넣을 것인지 정확하게 명시하지 않았다면, id 값도 sequence를 통해서 값을 주어야 한다!
	 */
	@Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
	@Insert("insert into todolist values(nextval('todolist_id_seq'),  #{todoitem.todo}, #{todoitem.finish}, #{todoitem.key})")
	int postTodo(@Param("todoitem") TodoList todoitem );
	
	
	/**
	 * 
	 * <pre>
	 * Id 인자값과 같은 Id 를 가진 TodoList 의 Todo 컬럼값을 수정
	 * 
	 * </pre>
	 * @param key
	 * @param todo
	 * @return
	 */
	@Update("update todolist set todo = #{todoitem.todo}, finish = #{todoitem.finish} where key = #{key}")
	int put(@Param("key") String key, @Param("todoitem") TodoList todoitem);
	
	
	/**
	 * 
	 * <pre>
	 * Id 인자값과 같은 Id 를 가진 TodoList 를 삭제
	 * 
	 * </pre>
	 * @param id
	 * @return
	 */
	@Delete("delete from todolist where key= #{key}")
	int del(@Param("key") String key);
	
	@Delete("delete from todolist")
	int delTodoList();
}