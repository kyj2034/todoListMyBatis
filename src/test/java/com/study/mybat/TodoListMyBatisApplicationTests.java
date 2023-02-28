package com.study.mybat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.study.mybat.dto.TodoList;
import com.study.mybat.mapper.TodoListMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
class TodoListMyBatisApplicationTests {
	@NonNull
	private DataSource ds;
	
	@NonNull
	TodoListMapper mapper;
	
//	@Test
//	public void mapperTest() throws Exception{
//		TodoList todolist = mapper.getTodoVal("1");
//		log.debug("TodoList : {}", todolist);
//		assertEquals("", todolist.getTodo());
//	}
//		
//	
//	@Ignore @Test
//	public void testDataSource() throws Exception {
//		System.out.println("DS=" + ds);
//		try (Connection conn = ds.getConnection()){
//			log.debug("Coooooooooonn = {}", conn.toString());
//			assertThat(conn).isInstanceOf(Connection.class);
//			} catch ( Exception e) {
//			e.printStackTrace();
//		}
//	}


}