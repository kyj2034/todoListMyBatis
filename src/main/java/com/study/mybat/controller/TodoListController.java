package com.study.mybat.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.mybat.dto.TodoList;
import com.study.mybat.result.ErrorResponse;
import com.study.mybat.result.IResultCode;
import com.study.mybat.service.TodoListService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodoListController
{

    @NonNull
    private TodoListService service;

    @GetMapping ( "/all" )
    public ErrorResponse getTodoList ()
    {
        List<TodoList> todoList = null;

        try {
              todoList = service.getTodoList();

            if( service.getTodoList().isEmpty ()) {
                return new ErrorResponse(IResultCode.RESOURCE_NOT_FOUND);
            }
        }
        catch(SQLException e) {
            return new ErrorResponse(IResultCode.DISCONNECTED);
        }
        catch(Exception e) {
            return new ErrorResponse(IResultCode.FAIL);
        }

        return new ErrorResponse(IResultCode.SUCCESS,  "GetTodoList()을 성공적으로 실행.", todoList);
    }


    // TodoList 라는 객체를 리턴하면 이 객체를 json 형태로 자동으로 리턴하여 클라이언트에 전달
    @GetMapping ( "/{key}" )
    public ErrorResponse getTodo ( @PathVariable ( "key" ) String key )
    {
        TodoList todoitem = null;

        try
        {
            Integer idVal =service.get( key ).getId();

        // 유효한 Id 값인지
            if ( idVal != null) {
                todoitem = service.get ( key );
            }
        }
        catch( NullPointerException e) {
            return new ErrorResponse(IResultCode.INVALID_KEY, e.getMessage ());
        }
        catch(SQLException e) {
            return new ErrorResponse(IResultCode.DISCONNECTED, e.getMessage ());
        }
        catch(Exception e) {
            return new ErrorResponse(IResultCode.FAIL,e.getMessage ());
        }

        return new ErrorResponse(IResultCode.SUCCESS, " getTodo()을 성공적으로 실행. ", todoitem);
    }


    // 등록
    @PostMapping ( )
    public ErrorResponse postTodo ( @RequestBody TodoList todoItem )
    {

        Integer postVal = null;

        try {
            // Todo 값이 비어있음
            if( todoItem.getTodo().equals( "" ) ) {

                return new ErrorResponse(IResultCode.RESOURCE_NOT_FOUND, todoItem);
            }

            postVal = service.post(todoItem);

        }
        catch(NullPointerException e) {
            return new ErrorResponse(IResultCode.NULLPOINTER, e.getMessage ());
        }
        catch(Exception e) {
            return new ErrorResponse(IResultCode.FAIL, e.getMessage ());
        }
        return new ErrorResponse(IResultCode.SUCCESS, " 등록이 완료되었습니다. ", todoItem);
    }



    // 수정
    @PutMapping ( "/{key}" )
    public ErrorResponse putTodo ( @PathVariable ( "key" ) String key,
                                   @RequestBody TodoList todoItem )
    {
        try {
            // ID 값 일치하는지 체크
            if (  key.equals( todoItem.getKey()) )
            {
                service.put ( key, todoItem );

            } else if (todoItem.getTodo().equals( ""))
            {
                return new ErrorResponse(IResultCode.RESOURCE_NOT_FOUND, todoItem);
            }
            else if (key.equals( todoItem.getKey())) {
                return new ErrorResponse ( IResultCode.DIFFRENT_KEY, todoItem, "Path Key 값 :" + key + ", 수신 Key 값 : " + todoItem.getKey() );
            }
        }catch(NullPointerException e) {
            return new ErrorResponse(IResultCode.NULLPOINTER, e.getMessage ());
        }
        catch ( Exception e ) {
            // TODO Auto-generated catch block
            return new ErrorResponse(IResultCode.FAIL, e.getMessage ());
        }


        return new ErrorResponse ( IResultCode.SUCCESS, " 수정이 완료되었습니다. ", todoItem);
    }

    // 삭제
    @DeleteMapping ( "/{keyList}" )
    @Transactional
    public ErrorResponse delTodo ( @PathVariable ( "keyList" ) List<String> keyList )
    {
//        String idVal = null;

         try
        {
//             idVal = service.get(key).getKey();
            if( !keyList.isEmpty()) {

                for(String key : keyList) {
                    service.del ( key );
                }
             }
        }
        catch (  NullPointerException e ){
            // TODO Auto-generated catch block
            return new ErrorResponse(IResultCode.NULLPOINTER , e.getMessage ());
        }
        catch ( Exception e ) {
            // TODO Auto-generated catch block
            return new ErrorResponse(IResultCode.FAIL, e.getMessage());
        }

         return new ErrorResponse(IResultCode.SUCCESS, "삭제가 성공적으로 완료되었습니다.");
    }

    // 전체 삭제
    @DeleteMapping ("/all")
    public ErrorResponse delTodoList () {

        if(!service.delTodoList ()) {
          return new ErrorResponse(IResultCode.FAIL, "전체 삭제를 실패했습니다.");
        }

        return new ErrorResponse(IResultCode.SUCCESS, "전체 삭제가 성공적으로 완료되었습니다.");
    }
}
