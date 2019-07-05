package com.itheima.search;

import com.itheima.dao.BookDao;
import com.itheima.dao.BookDaoImpl;
import com.itheima.pojo.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IndexManagerTest {

    @Test
    public void testCreateIndex() throws Exception {
        //1、采集数据
        BookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.queryBookList();

        //2、创建文档对象
        List<Document> docList = new ArrayList<Document>();
        for (Book book : bookList) {
            Document doc = new Document();
            /**
             * IntField整型类型域，TextField文本类型域，FloatField浮点型类型域
             * 参数1：域名--对应数据库中字段名
             * 参数2：域值
             * 参数3：是否存储--是否需要将该域对应的值存储到文档中
             */
            doc.add(new TextField("bookId", book.getId()+"", Store.YES));
            doc.add(new TextField("bookName", book.getBookname(), Store.YES));
            doc.add(new TextField("bookPrice", book.getPrice()+"", Store.YES));
            doc.add(new TextField("bookPic", book.getPic(), Store.YES));
            doc.add(new TextField("bookDesc", book.getBookdesc(), Store.YES));

            docList.add(doc);
        }
        //3、创建分词器analyzer
        //Analyzer analyzer = new StandardAnalyzer();
        //Analyzer analyzer = new SmartChineseAnalyzer();
        Analyzer analyzer = new IKAnalyzer();

        //4、创建文档索引配置对象IndexWriterConfig
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //5、创建存放索引目录Directory，指定索引存放路径
        File file = new File("D:\\itcast108\\w36+\\lucene\\test\\new_directory");
        Directory directory = FSDirectory.open(file);

        //6、创建索引编写器IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //7、利用索引编写器写入文档到索引目录
        for (Document document : docList) {
            //把文档对象写入到索引库中
            indexWriter.addDocument(document);
        }
        //8、释放资源
        indexWriter.close();
    }

}