package org.john.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by JeongHeon on 2016. 7. 4..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
    @Inject
    private DataSource ds;

    @Test
    public void testConection()throws Exception{
        try{
            Connection con = ds.getConnection();
            System.out.println("111");
            System.out.println("\n Con : " + con);
            System.out.println("222");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
