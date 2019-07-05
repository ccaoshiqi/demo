package com.itheima.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.BookDao;
import com.itheima.pojo.Book;

public class BookDaoImpl implements BookDao {

    public List<Book> queryBookList() {
        List<Book> bookList = new ArrayList<Book>();
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //创建连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tmall", "root", "root");

            //创建执行对象
            prepareStatement = connection.prepareStatement("select * from book");

            //执行查询
            rs = prepareStatement.executeQuery();
            Book book = null;
            //遍历查询结果
            while(rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookname(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setPic(rs.getString("pic"));
                book.setBookdesc(rs.getString("bookdesc"));
                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(prepareStatement != null) {
                    prepareStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.queryBookList();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

}