/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repository;

import com.hendisantika.buku.model.Buku;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author hendisantika
 */
public interface BukuRepo extends Repository<Buku, String>, PagingAndSortingRepository<Buku, String> {

    List<Buku> findByBookId(String BookId);

    List<Buku> findByTitle(String title);

    List<Buku> findByCategory(String category);

    List<Buku> findByRegistered(Date reg);

    List<Buku> findByTitleAndCategory(String title, String category);

    @Modifying
    @Query("update Buku b set b.total = ?1 where b.title = ?2")
    public void updateTotal(int jml, String title);

}
