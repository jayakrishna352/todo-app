package com.njk.todo.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njk.todo.app.dao.ToDoDao;
import com.njk.todo.app.dao.ToDoDaoImpl;
import com.njk.todo.app.model.ToDo;

@WebServlet("/")
public class ToDoController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ToDoDao todoDao;

	public void init() {
		todoDao = new ToDoDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertToDo(request, response);
				break;
			case "/delete":
				deleteToDo(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateToDo(request, response);
				break;
			case "/list":
				listToDo(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listToDo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ToDo> listToDo = todoDao.selectAllToDos();
		request.setAttribute("listToDo", listToDo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todoList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todoForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ToDo existingToDo = todoDao.selectToDo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todoForm.jsp");
		request.setAttribute("todo", existingToDo);
		dispatcher.forward(request, response);

	}

	private void insertToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");

		/*
		 * DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd"); LocalDate
		 * targetDate = LocalDate.parse(request.getParameter("targetDate"),df);
		 */

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		ToDo newToDo = new ToDo(title, username, description, LocalDate.now(), isDone);
		todoDao.insertToDo(newToDo);
		response.sendRedirect("list");
	}

	private void updateToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		ToDo updateToDo = new ToDo((long) id, title, username, description, targetDate, isDone);

		todoDao.updateToDo(updateToDo);

		response.sendRedirect("list");
	}

	private void deleteToDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		todoDao.deleteToDo(id);
		response.sendRedirect("list");
	}
}
