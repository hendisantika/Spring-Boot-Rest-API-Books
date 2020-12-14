/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repo;

import com.hendisantika.buku.model.Peminjam;
import com.hendisantika.buku.repository.PeminjamRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author hendisantika
 */
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
//        assertEquals(4L, jumlah.longValue());
//    }
    
    @Test
    public void testCariById() {
        Optional<Peminjam> p = pr.findById("U001");
        assertNotNull(p);
        assertEquals("Uchiha Sasuke", p.get().getName());
        assertEquals("Konohagakure", p.get().getAddress());

        Optional<Peminjam> p1 = pr.findById("T002");
        assertNull(p1);
    }
    
}
