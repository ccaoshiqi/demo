package com.itheima.dao;

import java.util.List;

import com.itheima.pojo.Book;

public interface BookDao {

    /**
     * 查询图书列表
     * @return 图书列表
     */
    List<Book> queryBookList();
}
