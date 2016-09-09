/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.controller;

import com.hendisantika.buku.model.Peminjam;
import com.hendisantika.buku.repository.PeminjamRepo;
import java.util.HashMap;
import java.util.Map;
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
@RequestMapping(value ="/api/peminjam")
public class PeminjamController {
    @Autowired
    PeminjamRepo pr;
//    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Iterable<Peminjam> showAll(Pageable page) {
        return pr.findAll();
    }

    /**
     * GET /create --> Create a new booking and save it in the database.
     *
     * @param p
     * @param buku
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody @Valid Peminjam p) {
        p.setUid(p.getUid());
        p.setName(p.getName());
        p.setAddress(p.getAddress());
        p = pr.save(p);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Payload", p);
        dataMap.put("message", "Data Peminjam sukses");
        dataMap.put("Success", "True");

        return dataMap;
    }
}
