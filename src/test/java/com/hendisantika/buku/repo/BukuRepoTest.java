/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repo;

import com.hendisantika.buku.model.Buku;
import com.hendisantika.buku.repository.BukuRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author hendisantika
 */
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/clean.sql","/data/buku.sql"}
)
public class BukuRepoTest {

    @Autowired
    BukuRepo bukuRepo;

    @Autowired
    DataSource ds;

    @Test
    public void testInsert() throws SQLException, java.text.ParseException {
        Buku b = new Buku();
        b.setBookId("T001");
        b.setTitle("30 Hari Mencarimu");
        b.setCategory("Novel");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date reg = formatter.parse("2018-08-17");
        b.setTotal(10);
        b.setAvailable(2);
        bukuRepo.save(b);
        String sql = "select count(*) as jumlah "
                + "from buku "
                + "where book_id = 'T001'";

        try (Connection c = ds.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }
    }

    @Test
    public void testHitung() {
        Long jumlah = bukuRepo.count();
        Assert.assertEquals(11L, jumlah.longValue());
    }

    @Test
    public void testCariById() {
        Optional<Buku> b = bukuRepo.findById("B001");
//        Buku b = (Buku) bukuRepo.findByBookId("T001");
        Assert.assertNotNull(b);
        Assert.assertEquals("30 Hari Mencari Cinta", b.get().getTitle());
        Assert.assertEquals("Novel", b.get().getCategory());

        Optional<Buku> b1 = bukuRepo.findById("T002");
        Assert.assertNull(b1);
    }

    @After
    public void hapusData() throws Exception {
        String sql = "delete from buku where book_id = 'T001'";
        try (Connection c = ds.getConnection()) {
            c.createStatement().executeUpdate(sql);
        }
    }
}
