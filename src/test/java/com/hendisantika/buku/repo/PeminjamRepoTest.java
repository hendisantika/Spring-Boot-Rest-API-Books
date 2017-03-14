/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repo;

import com.hendisantika.buku.model.Peminjam;
import com.hendisantika.buku.repository.PeminjamRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 *
 * @author hendisantika
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/peminjam.sql"
)
public class PeminjamRepoTest {
    @Autowired
    PeminjamRepo pr;

    @Autowired
    DataSource ds;
    
//    
//    @Test
//    public void testHitung() {
//        Long jumlah = pr.count();
//        Assert.assertEquals(4L, jumlah.longValue());
//    }
    
    @Test
    public void testCariById() {
        Peminjam p = pr.findOne("U001");
        Assert.assertNotNull(p);
        Assert.assertEquals("Uchiha Sasuke", p.getName());
        Assert.assertEquals("Konohagakure", p.getAddress());

        Peminjam p1 = pr.findOne("T002");
        Assert.assertNull(p1);
    }
    
}
