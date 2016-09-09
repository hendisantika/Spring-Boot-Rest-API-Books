/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.service;

import com.hendisantika.buku.model.Buku;
import com.hendisantika.buku.repository.BukuRepo;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hendisantika
 */
@Service
@Transactional
public class BukuImpl implements BukuService{
    @Autowired
    BukuRepo bukuRepo;

    @Override
    public Buku editBuku(Buku buku) {
        return bukuRepo.save(buku);
    }
    
}
