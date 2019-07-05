package com.itheima.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class SearchManagerTest {

    //检索索引数据
    @Test
    public void testSearchIndex() throws Exception {
        //1、创建分词器Analyzer
        //Analyzer analyzer = new StandardAnalyzer();
        //Analyzer analyzer = new SmartChineseAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        //2、创建查询对象Query
        //2.1、创建查询分析器；参数1：默认查询的域，参数2：分词器
        //QueryParser queryParser = new QueryParser("bookName", analyzer);
        //2.2、创建Query对象
        QueryParser queryParser  = new MultiFieldQueryParser(new String[]{"bookName","bookDesc"},analyzer);
        Query query = queryParser.parse("我想买一本人民邮电出版社出版的java书籍");

        //3、创建存放索引目录Directory，指定索引存放路径
        Directory directory = FSDirectory.open(new File("D:\\itcast108\\w36+\\lucene\\test\\new_directory"));
        //4、创建索引读对象IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);
        //5、创建索引搜索对象IndexSearcher，执行搜索，返回结果
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        /**
         * 参数1：查询对象
         * 参数2：查询前n个文档
         * 返回结果：得分文档（包含文档数组，总的命中数）
         */
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("符合本次查询的总命中文档数为：" + topDocs.totalHits);

        //6、处理搜索结果
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            System.out.println("文档在Lucene中的id为：" + scoreDoc.doc + "；文档分值为：" + scoreDoc.score);
            //根据lucene中的文档id查询到文档
            Document document = indexSearcher.doc(scoreDoc.doc);

            System.out.println("文档id为：" + document.get("bookId"));
            System.out.println("名称为：" + document.get("bookName"));
            System.out.println("价格为：" + document.get("bookPrice"));
            System.out.println("图片为：" + document.get("bookPic"));
            System.out.println("描述为：" + document.get("bookDesc"));
            System.out.println("---------------------------------------");
            System.out.println((float)(18+30)*5/130000);
            System.out.println((float)1/130);

        }
        //7、释放资源
        indexReader.close();
    }

}