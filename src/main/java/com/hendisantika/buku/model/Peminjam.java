/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendisantika.buku.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hendisantika.buku.JsonDateSerializer;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.LocalDate;

/**
 *
 * @author hendisantika
 */
@Entity
public class Peminjam {

    @Id
    String uid;

    String name;

    String address;

    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    Date registered = new Date();

    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    Date expired = new LocalDate().plusMonths(2).toDate();

    @OneToMany(mappedBy = "peminjam", cascade = CascadeType.ALL)
    private Set<Peminjaman> peminjaman;

    public Peminjam() {
    }

    public Peminjam(String uid) {
        this.uid = uid;
    }
    
    public Peminjam(String uid, String name, String address, Date registered, Date expired) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.registered = registered;
        this.expired = expired;
    }

    public Set<Peminjaman> getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Set<Peminjaman> peminjaman) {
        this.peminjaman = peminjaman;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

}
