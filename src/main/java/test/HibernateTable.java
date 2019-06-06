package test;

import com.alibaba.fastjson.JSON;
import com.dev.core.dao.IBaseDao;
import com.dev.core.dao.impl.BaseDaoImpl;
import com.dev.core.model.User;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.util.EnumSet;
import java.util.List;

public class HibernateTable {

    public static void main(String[] args) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

        //工具类
        SchemaExport export = new SchemaExport();

        export.create(EnumSet.of(TargetType.DATABASE), metadata);

    }

    @Test
    public void test() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");
        String hql = " FROM User WHERE userName='xiaoming'";
        List<User> us = baseDao.find(hql);
        System.out.println(us.get(0).getPassword());
    }

    @Test
    public void test1() {
        User u = new User();
        u.setUserName("xiaoming");
        u.setPassword("123456");
        System.out.println(JSON.toJSONString(u));

    }

    @Test

    public void runsqlBySpringUtils() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");

        File newfile = new File("upload/pan.sql");
        Resource resource = new FileSystemResource(newfile);
        EncodedResource resourceDelegate = new EncodedResource(resource);
        baseDao.getSessionFactory().openSession().doWork(
                new Work() {
                    public void execute(Connection connection) {
                        ScriptUtils.executeSqlScript(connection, resource);
                    }
                }
        );
    }
}

