package com.springtutorials.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.support.PagedListHolder;

import com.springtutorials.constant.SystemContant;

public class PagedListUtil<T> {
	
	public PagedListUtil() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public void handlerPagedList(String type, HttpServletRequest req, List<T> listOfObject, int count, String constantObject) {
		
		req.getSession().setAttribute("pageCount", count);//đếm số lượng trang cần hiển thị
		//Xử lí phân trang 
		PagedListHolder<T> pagedList = null;
		if (type == null) {
			pagedList = new PagedListHolder<T>();
			pagedList.setSource(listOfObject);
			pagedList.setPageSize(SystemContant.FETCH);
			req.getSession().setAttribute(constantObject, pagedList);
		} else if (type.equalsIgnoreCase("next")) {
			pagedList = (PagedListHolder<T>) req.getSession().getAttribute(constantObject);
			pagedList.nextPage();
		} else if (type.equalsIgnoreCase("prev")) {
			pagedList = (PagedListHolder<T>) req.getSession().getAttribute(constantObject);
			pagedList.previousPage();
		} else if (type.equalsIgnoreCase("first")) {
			pagedList = (PagedListHolder<T>) req.getSession().getAttribute(constantObject);
			pagedList.setPage(0);
		} else if (type.equalsIgnoreCase("last")) {
			pagedList = (PagedListHolder<T>) req.getSession().getAttribute(constantObject);
			pagedList.setPage(count - 1);
		} else {
			pagedList = (PagedListHolder<T>) req.getSession().getAttribute(constantObject);
			pagedList.setPage(Integer.parseInt(type) - 1);
		}
	}
	
}
