/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hendisantika.buku.JsonDateSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.joda.time.LocalDate;

import java.util.Date;

/**
 *
 * @author hendisantika
 */
@Entity
public class Peminjaman {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date startDt;
    
    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date returnDt  = new LocalDate().plusDays(9).toDate();

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Buku buku;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Peminjam peminjam;
    
    public Peminjaman() {
    }

    public Peminjaman(Long pid, Date startDt, Date returnDt,Buku buku, Peminjam peminjam) {
        this.pid = pid;
        this.startDt = startDt;
        this.returnDt = returnDt;
        this.buku = buku;
        this.peminjam = peminjam;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Peminjam getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(Peminjam peminjam) {
        this.peminjam = peminjam;
    }

}
