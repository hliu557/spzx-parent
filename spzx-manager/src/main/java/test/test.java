package test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.*;

import java.io.IOException;
import java.io.InputStream;


public class test {
    public static void main(String[] args) throws IOException {
        String resource = "mapper/user/SysUserMapper.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sql = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sql.openSession();
        sqlSession.selectList("com.lhy.spzx.manager.mapper.SysUserMapper.selectUsers");
    }
}
