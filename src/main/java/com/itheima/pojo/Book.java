package com.itheima.pojo;

public class Book {

    private Integer id;
    private String bookname;
    private Float price;
    private String pic;
    private String bookdesc;

    public Book() {
    }

    public Book(Integer id, String bookname, Float price, String pic, String bookdesc) {
        this.id = id;
        this.bookname = bookname;
        this.price = price;
        this.pic = pic;
        this.bookdesc = bookdesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", bookdesc='" + bookdesc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }
}
