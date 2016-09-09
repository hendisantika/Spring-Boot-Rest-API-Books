/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.controller;

import com.hendisantika.buku.model.Peminjaman2;
import com.hendisantika.buku.repository.Peminjaman2Repo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value = "/api/peminjaman2")
public class Peminjaman2Controller {

    @Autowired
    Peminjaman2Repo pr;

    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Peminjaman2> showAll(Pageable page) {
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
    public Map<String, Object> create(@RequestBody Peminjaman2 p) {
//        p.setBookId(p.getBookId());
//        p.setUserId(p.getUserId());
        p = pr.save(p);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Payload", p);
        dataMap.put("message", "Data Peminjaman sukses");
        dataMap.put("Success", "True");

        return dataMap;
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> find(@RequestBody Peminjaman2 p) {
        List<Peminjaman2> p1 = pr.findBystartDtOrBookIdOrUserId(p.getStartDt(), p.getBookId(), p.getUserId());
        System.out.println("Peminjaman : " + p.getStartDt() + "|" + p.getBookId() + "|" + p.getUserId());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Payload", p1);
        dataMap.put("message", "Data Peminjaman berhasil ditemukan");
        dataMap.put("Success", "True");

        return dataMap;
    }

}
