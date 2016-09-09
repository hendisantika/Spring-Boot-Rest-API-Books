/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.controller;

import com.hendisantika.buku.model.Buku;
import com.hendisantika.buku.model.Peminjam;
import com.hendisantika.buku.model.Peminjaman;
import com.hendisantika.buku.repository.BukuRepo;
import com.hendisantika.buku.repository.PeminjamRepo;
import com.hendisantika.buku.repository.PeminjamanRepo;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hendisantika
 */
@RestController
@RequestMapping(value = "/api/peminjaman")
public class PeminjamanController {

    @Autowired
    BukuRepo br;
    
    @Autowired
    PeminjamRepo pe;
    
    @Autowired
    PeminjamanRepo pr;

    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Peminjaman> showAll(Pageable page) {
        return pr.findAll();
    }

    /**
     * GET /create --> Create a new booking and save it in the database.
     *
     * @param bookId
     * @param uid
     * @param p
     * @param pm
     * @param buku
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody Peminjaman p) {
//        p.setBookId(p.getBookId());
//        p.setUserId(p.getUserId());
//        Book bookA = new Book("Book A");
//        Buku b = new Buku(p.getBuku().getBookId());
        Buku b = new Buku("B001");
//        Peminjam p1 = new Peminjam(p.getPeminjam().getUid());
        Peminjam p1 = new Peminjam("U001");
        
        Peminjaman p2 = new Peminjaman();
        
        p2.setBuku(b);
        p2.setPeminjam(p1);
        System.out.println("ID Buku : " + b.getBookId() + "|ID Peminjam : " + p1.getUid());
//        b.getPeminjaman().add(p2);
        b.setPeminjaman((Set<Peminjaman>) p2);
        
//        br.save(b);
//        pe.save(p1);
        
        
        p2 = pr.save(p2);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Payload", p2);
        dataMap.put("message", "Data Peminjaman sukses");
        dataMap.put("Success", "True");

        return dataMap;
    }

}
