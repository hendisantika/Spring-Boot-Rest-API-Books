/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repo;

import com.hendisantika.buku.model.Buku;
import com.hendisantika.buku.repository.BukuRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author hendisantika
 */
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
            assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            assertEquals(1L, jumlahRow.longValue());
        }
    }

    @Test
    public void testHitung() {
        Long jumlah = bukuRepo.count();
        assertEquals(11L, jumlah.longValue());
    }

    @Test
    public void testCariById() {
        Optional<Buku> b = bukuRepo.findById("B001");
//        Buku b = (Buku) bukuRepo.findByBookId("T001");
        assertNotNull(b);
        assertEquals("30 Hari Mencari Cinta", b.get().getTitle());
        assertEquals("Novel", b.get().getCategory());

        Optional<Buku> b1 = bukuRepo.findById("T002");
        assertNull(b1);
    }

    @AfterEach
    public void hapusData() throws Exception {
        String sql = "delete from buku where book_id = 'T001'";
        try (Connection c = ds.getConnection()) {
            c.createStatement().executeUpdate(sql);
        }
    }
}
