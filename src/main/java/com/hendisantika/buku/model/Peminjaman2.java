/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hendisantika.buku.JsonDateSerializer;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.LocalDate;

/**
 *
 * @author hendisantika
 */
@Entity
public class Peminjaman2 {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date startDt  = new Date();
    
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date returnDt  = new LocalDate().plusDays(9).toDate();;
    
    private String bookId;
    
    private String userId;

    public Peminjaman2() {
    }

    public Peminjaman2(int id, Date startDt, Date returnDt, String bookId, String userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.startDt = startDt;
        this.returnDt = returnDt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(Date returnDt) {
        this.returnDt = returnDt;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
    
    
    
    
}
