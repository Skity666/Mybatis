package com.Skity666;

import com.Skity666.entity.User;
import com.Skity666.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Skity666
 * @Date 2020/11/9 0009 19:33
 * @Version 1.0
 **/
public class TestMain {
    public static void main(String[] args) throws IOException {
        //s1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//s2.创建 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//s3.获取SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
//s4.获取 SqlSession对象
        SqlSession session = factory.openSession();
//s5.使用 SqlSession 获取dao
        UserMapper userMapper = session.getMapper(UserMapper.class);

//6.调用dao的方法
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("查询全部user");

        User me=new User(1L,"Skity666", new Date(), new BigDecimal(1000));
        userMapper.insert(me);
//        要修改到数据库再取消注释
//        提交
//        session.commit();


        System.out.println("id为 1 ：" +userMapper.findById(1L));

        users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("查询全部user\r\n");


        users = userMapper.findByFilter(me);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("findByFilter 查询全部user\r\n");

        Map<String,Object> filter=new HashMap<String, Object>();
        filter.put("name","S");
        users = userMapper.findByFilter(filter);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("findByFilter 通过name模糊查询user\r\n");

        List<Long> ids=new ArrayList<Long>();
        ids.add(1L);
        users = userMapper.findByIds(ids);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("findByIds 通过id集合查询user\r\n");







//        要修改到数据库再取消注释
//        提交
//        session.commit();
//7.释放资源
        session.close();
        in.close();

    }

    @Test
    public void test() throws IOException {
        //分页开始
//s1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//s2.创建 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//s3.获取SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
//s4.获取 SqlSession对象
        SqlSession session = factory.openSession();
//s5.使用 SqlSession 获取dao
        UserMapper userMapper = session.getMapper(UserMapper.class);

        // pageNum表示当前页数(从1开始)，pageSize表示每页显示记录条数
        Page<Object> page = PageHelper.startPage(1, 3);
        List<User> users = userMapper.findAll();
        System.out.println("每页记录数:" + page.getPageSize());

        System.out.println("总记录数:" + page.getTotal());
        System.out.println("总页数:" + page.getPages());

        System.out.println("当前页数:" + page.getPageNum());
        System.out.println("当前记录数:" + page.getResult().size());

        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("查询完成");
    }
}
