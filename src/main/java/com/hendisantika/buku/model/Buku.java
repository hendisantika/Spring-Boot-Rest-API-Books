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

/**
 *
 * @author hendisantika
 */
@Entity
public class Buku {

    @Id
    String bookId;

    String title;

    String category;

    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    Date registered = new Date();

    int total;

    int available;

    @OneToMany(mappedBy = "buku", cascade = CascadeType.ALL)
    private Set<Peminjaman> peminjaman;

    public Buku() {
    }

    public Buku(String bookId) {
        this.bookId = bookId;
    }

    public Buku(String bookId, String title, String category, int total, int available, Date registered) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
        this.registered = registered;
        this.total = total;
        this.available = available;

    }

    public Set<Peminjaman> getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Set<Peminjaman> peminjaman) {
        this.peminjaman = peminjaman;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Buku{" + "bookId=" + bookId + ", title=" + title + ", category=" + category + ", registered=" + registered + ", total=" + total + ", available=" + available + ", peminjaman=" + peminjaman + '}';
    }

}
