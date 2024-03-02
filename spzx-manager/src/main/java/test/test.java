package test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;


public class test {
    public static void main(String[] args) throws IOException {
        test2 t1= new test2();
        Class  tclass = t1.getClass();
        StringBuilder log = new StringBuilder("");
        int err=0;
        Method[] declaredMethods = tclass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if(m.isAnnotationPresent(testano.class)){
                try {
                    m.setAccessible(true);
                    m.invoke(t1,null);
                }catch (Exception e){
                    err++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }

            }

        }
        log.append(tclass.getSimpleName());
        log.append(" has  ");
        log.append(err);
        log.append(" error.");
        // 生成测试报告
        System.out.println(log.toString());

    }

}
