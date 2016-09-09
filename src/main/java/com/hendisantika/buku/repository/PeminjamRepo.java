/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.repository;

import com.hendisantika.buku.model.Peminjam;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author hendisantika
 */
public interface PeminjamRepo extends CrudRepository<Peminjam, String>{
    
}
