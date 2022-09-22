package com.njk.todo.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.njk.todo.app.model.ToDo;

public interface ToDoDao {
	void insertToDo(ToDo todo) throws SQLException;

	ToDo selectToDo(long todoId);

	List<ToDo> selectAllToDos();

	boolean deleteToDo(int id) throws SQLException;

	boolean updateToDo(ToDo todo) throws SQLException;
}
