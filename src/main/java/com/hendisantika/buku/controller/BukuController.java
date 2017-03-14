/**
 *
 */
package com.hendisantika.buku.controller;

import com.hendisantika.buku.model.Buku;
import com.hendisantika.buku.repository.BukuRepo;
import com.hendisantika.buku.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hendisantika
 *
 */
@RestController
@RequestMapping("api/buku")
public class BukuController {

    @Autowired
    BukuRepo bukuRepo;
    
    @Autowired
    BukuService bukuService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Iterable<Buku> showAll(Pageable page) {
        return bukuRepo.findAll();
    }

    /**
     * GET /create --> Create a new booking and save it in the database.
     *
     * @param buku
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody @Valid Buku buku) {
        buku.setBookId(buku.getBookId());
        buku.setTitle(buku.getTitle());
        buku.setCategory(buku.getCategory());

        List<Buku> data = bukuRepo.findByTitle(buku.getTitle());
        
//        for (int i = 0; i < data.size(); i++) {
//            List<Buku> caribuku = bukuRepo.findById(data.get(i).getId());
//            System.out.println("Data : " + caribuku.toString());
//            bukuRepo.updateTotal(data.size() + 1, buku.getTitle());
//            caribuku.get(i).setTotal(data.size() + 1);
//            System.out.println("Data " + i + " : " + caribuku.get(i));
//            bukuRepo.save(caribuku.get(i));
//
//        }

        buku.setTotal(data.size() + 1);

//        buku.setAvailable(buku.getAvailable());
        buku = bukuRepo.save(buku);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("Payload", buku);
        dataMap.put("message", "Buku created successfully");
        dataMap.put("Success", "True");

        return dataMap;
    }

    /**
     * GET /read --> Read a booking by booking id from the database.
     *
     * @param buku
     * @return
     */
    @RequestMapping(value = "/filter/title", method = RequestMethod.POST)
    public Map<String, Object> findByTitle(@RequestBody Buku buku) {
        List<Buku> data = bukuRepo.findByTitle(buku.getTitle());
        Map<String, Object> dataMap = new HashMap<>();

        if (data.size() > 0) {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku ditemukan!");
            dataMap.put("Success", "True");
        } else {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku tidak ditemukan!");
            dataMap.put("Success", "False");
        }

        return dataMap;
    }

    @RequestMapping(value = "/filter/category", method = RequestMethod.POST)
    public Map<String, Object> findByCategory(@RequestBody Buku buku) {
        List<Buku> data = bukuRepo.findByCategory(buku.getCategory());
        Map<String, Object> dataMap = new HashMap<>();

        if (data.size() > 0) {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku ditemukan!");
            dataMap.put("Success", "True");
        } else {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku tidak ditemukan!");
            dataMap.put("Success", "False");
        }

        return dataMap;
    }

    @RequestMapping(value = "/filter/title/category/", method = RequestMethod.POST)
    public Map<String, Object> findByTitleCategory(@RequestBody Buku buku) {
        List<Buku> data = bukuRepo.findByTitleAndCategory(buku.getTitle(), buku.getCategory());
        Map<String, Object> dataMap = new HashMap<>();

        if (data.size() > 0) {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku ditemukan!");
            dataMap.put("Success", "True");
        } else {
            dataMap.put("Payload", data);
            dataMap.put("message", "Buku tidak ditemukan!");
            dataMap.put("Success", "False");
        }

        return dataMap;
    }

    /**
     * GET /update --> Update a booking record and save it in the database.
     */
	@RequestMapping("/update")
	public Map<String, Object> updateBuku(@RequestBody Buku buku1) {
		Buku buku2 = bukuRepo.findOne(buku1.getBookId());
                Buku buku3 = bukuService.editBuku(buku2);
	
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Booking updated successfully");
		dataMap.put("status", "1");
		dataMap.put("booking", buku2);
	    return dataMap;
	}
}
