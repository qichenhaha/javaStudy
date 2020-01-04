package com.heidan.test;

import com.heidan.dao.IUserDao;
import com.heidan.entity.Account;
import com.heidan.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by heidan on 2019/12/1 9:57
 */

public class UserTest {

   private InputStream in;
   private SqlSession sqlSession;
   private IUserDao mapper;

   @Before
   public void init() throws Exception{
      // 1.读取配置文件
       in = Resources.getResourceAsStream("mybatis-config.xml");
      // 2.创建SqlSessionFactory 工厂
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      SqlSessionFactory factory = builder.build(in);
      // 3.使用工厂生产SqlSession对象
       sqlSession = factory.openSession();

      // 4.使用SqlSession创建dao接口的代理对象
       mapper = sqlSession.getMapper(IUserDao.class);

   }
   @After
   public void destroy() throws Exception{
      System.out.println("方法接受");
      sqlSession.commit();
      sqlSession.close();
      in.close();
   }

   @Test
    public void  a1() {
       // 5.使用代理对象执行方法
       List<User> users = mapper.listAll();
       for (User user : users) {
           System.out.println(user);
       }
   }

   @Test
   public void  a2(){
      User user = new User();
      user.setUsername("李5");
      user.setSex("男");
      user.setBirthday(new Date());
      user.setAddress("安徽合肥");
      int add = mapper.add(user);
      System.out.println("user:"+ user);

      if (add>=1){
         System.out.println("新增成功！");
      }else {
         System.out.println("新增失败！");
      }
   }

   @Test
   public void a3(){
      User user = new User();
      user.setUsername("李四");
      user.setSex("男");
      user.setBirthday(new Date());
      user.setAddress("安徽合肥");
      user.setId(51);

      mapper.Update(user);
   }

   @Test
   public void a4(){
      int del = mapper.del(51);
      System.out.println(del);
   }

   @Test
   public void  a5(){
      User user = mapper.ById(48);
      System.out.println(user);
   }

   @Test
   public void a6(){

      User user = new User();
      user.setUsername("小二王");
      List<User> users = mapper.byName(user);
      for (User user1 : users) {
         System.out.println(user1);
      }
   }

   @Test
   public void a7(){
      List<Integer> list = new ArrayList<>();

      list.add(41);
      list.add(42);

      List<User> users = mapper.listById(list);
      for (User user : users) {
         System.out.println(user);
      }
   }

   @Test
   public void a8(){
      List<Account> accounts = mapper.listAccount();
      for (Account account : accounts) {
         System.out.println(account.getId());
      }

   }

   @Test
   public void  a9(){
      List<User> accounts = mapper.listfindbyId();
      for (User account : accounts) {
         System.out.println(account);
      }
   }


}
