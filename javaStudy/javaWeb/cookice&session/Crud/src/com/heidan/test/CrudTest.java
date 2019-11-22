package com.heidan.test;

import com.heidan.dao.UserDao;
import com.heidan.domain.User;
import org.junit.Test;

import java.util.List;

/**
 * Create by heidan on 2019/11/20 20:58
 */

public class CrudTest {

    @Test
    public void a1(){
        UserDao userDao = new UserDao();
        List<User> users = userDao.listAll();
        System.out.println(users);
    }


    @Test
    public void a2(){
        UserDao userDao = new UserDao();
        User user = userDao.UserById(1);
        System.out.println(user);
    }

    @Test
    public void  a3(){
        User user = new User("黑蛋",'男',14,"安徽合肥","214@qq.com");
        UserDao userdao = new UserDao();
        int i = userdao.UserAdd(user);
        if (i>=1){
            System.out.println("新增成功！");
        }else {
            System.out.println("新增失败！");
        }
    }

    @Test
    public void a4(){
        User user = new User(2,"黑蛋a",'男',14,"安徽合肥","214@qq.com");
        UserDao userdao = new UserDao();
        int i = userdao.UserUpdate(user);
        if (i>=1){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
    }

    @Test
    public void a5(){
        UserDao userdao = new UserDao();
        int i = userdao.UserDelete(5);
        if (i>=1){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }


}
