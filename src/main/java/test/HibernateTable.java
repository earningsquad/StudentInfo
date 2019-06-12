package test;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.StudentInfo;
import com.dev.core.model.User;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

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
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:applicationContext.xml");
        IBaseDao baseDao = (IBaseDao) ctx.getBean("baseDao");
        List<Map> lists=new ArrayList<>();
        lists=baseDao.findBySql("SELECT SI.CLASS_NUMBER,lesson.LE_NAME,AVG(SL.SCORE) FROM student_info SI \n" +
                "LEFT JOIN stu_lesson SL ON SI.ID = SL.STUDENT_ID \n" +
                "LEFT JOIN lesson on lesson.id = LESSON_ID\n" +
                "GROUP BY SI.CLASS_NUMBER,SL.LESSON_ID\n");
        System.out.println(lists.toString());

   }
}
