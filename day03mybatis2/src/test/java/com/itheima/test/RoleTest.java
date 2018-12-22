package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IRoleDao roledao;

    @Before
    public  void init() throws IOException {
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory= new SqlSessionFactoryBuilder().build(in);
        session=factory.openSession();
       roledao = session.getMapper(IRoleDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testfindAll(){
        List<Role> all = roledao.findAll();
        for (Role role : all) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}
