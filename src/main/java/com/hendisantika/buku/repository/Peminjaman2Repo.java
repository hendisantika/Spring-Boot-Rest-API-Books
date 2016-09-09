/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repository;

import com.hendisantika.buku.model.Peminjaman2;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author hendisantika
 */
public interface Peminjaman2Repo extends  CrudRepository<Peminjaman2, String>{
    public List<Peminjaman2> findBystartDtOrBookIdOrUserId(Date startDt, String BookId, String userId);
    
}
