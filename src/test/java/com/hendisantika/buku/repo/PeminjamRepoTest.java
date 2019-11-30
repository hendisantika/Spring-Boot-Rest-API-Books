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
import java.util.Optional;

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
        Optional<Peminjam> p = pr.findById("U001");
        Assert.assertNotNull(p);
        Assert.assertEquals("Uchiha Sasuke", p.get().getName());
        Assert.assertEquals("Konohagakure", p.get().getAddress());

        Optional<Peminjam> p1 = pr.findById("T002");
        Assert.assertNull(p1);
    }
    
}
