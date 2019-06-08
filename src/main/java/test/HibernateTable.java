package test;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.MyBasicInfo;
import com.dev.core.model.MyWholeInfo;
import com.dev.core.model.User;
import com.dev.core.service.MyInfoService;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.EnumSet;
import java.util.List;

public class HibernateTable {

    public static void main(String[] args){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

        //工具类
        SchemaExport export=new SchemaExport();

        export.create(EnumSet.of(TargetType.DATABASE), metadata);

    }
    @Test
    public void test(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");
        String hql=" FROM User WHERE userName='xiaoming'";
        List<User> us = baseDao.find(hql);
        System.out.println(us.get(0).getPassword());
    }
    @Test
    public void test1(){
        MyWholeInfo studentInfo=new MyWholeInfo();
        studentInfo.setClassNumber(1);
        System.out.println(studentInfo.getClassNumber());
    }

    @Test
    public void test2(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        MyInfoService myInfoService= (MyInfoService) ctx.getBean("myInfoService");
        MyBasicInfo myBasicInfo=myInfoService.getBasic(1);
        System.out.println(myBasicInfo);
        //   IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");

    }

    //修改我的基本信息
    @Test
    public void test7(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        MyInfoService myInfoService= (MyInfoService) ctx.getBean("myInfoService");
        MyBasicInfo myBasicInfo=myInfoService.getBasic(1);
        System.out.println(myBasicInfo);
        //   IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");
        myBasicInfo.setName("小小明");
        myBasicInfo.setFileLocation("c:\\");
        myInfoService.update(myBasicInfo);// myBasicInfo.getStudentInfoE();
    }

   //查看全部信息
    @Test
    public void test3(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        MyInfoService myInfoService= (MyInfoService) ctx.getBean("myInfoService");
        System.out.println(myInfoService.getWholeInfo(1));
        //   IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");

    }
    //查看我的成绩
    @Test
    public void test4(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        MyInfoService myInfoService= (MyInfoService) ctx.getBean("myInfoService");
        System.out.println(myInfoService.getMyScores(1));
        //   IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");

    }

    //查看我的荣誉
    @Test
    public void test5(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        MyInfoService myInfoService= (MyInfoService) ctx.getBean("myInfoService");
        System.out.println(myInfoService.getMyHonour(1));
        //   IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");

    }

}
