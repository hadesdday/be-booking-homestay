package com.group12.bookinghomestay.admin.controller;

import com.group12.bookinghomestay.admin.model.Hotel;
import com.group12.bookinghomestay.admin.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class HotelController {
    private static final String PATH = "/hotel";
    @Autowired
    private HotelService hotelService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(PATH)
    public List<Hotel> getHotelList() {
        return hotelService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(PATH + "/{id}")
    public Hotel getHotelById(@PathVariable(name = "id") Long id) {
        return hotelService.findById(id).get();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(PATH)
    public ResponseEntity addHotel(@RequestBody Hotel hotel) {
        hotelService.save(hotel);
        return ResponseEntity.ok().body(hotel);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity deleteHotel(@PathVariable(name = "id") Integer id) {
        hotelService.remove(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = PATH + "/{id}", consumes = {"application/json"})
    public Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable("id") Long id) {
        return hotelService.findById(id).map(hotel -> {
            hotel.setName(newHotel.getName());
//            hotel.setOwner(newHotel.getOwner());
//            hotel.setPlace(newHotel.getPlace());
            hotel.setInfo(newHotel.getInfo());
            hotel.setPolicy(newHotel.getPolicy());
            hotel.setStatus(newHotel.getStatus());
            return hotelService.save(hotel);
        }).orElseGet(() -> {
            newHotel.setId(id);
            return hotelService.save(newHotel);
        });
    }

    @GetMapping(PATH + "/findHotelGood")
    @CrossOrigin("*")
    public List<Hotel> findHotelGood() {
        return hotelService.findGoodHotelList();
    }

    @GetMapping(PATH + "/getHotelListDiscount")
    @CrossOrigin("*")
    public List<Hotel> getHotelListDiscount() {
        return hotelService.getHotelListDiscount();
    }

    @GetMapping(PATH + "/search/{dateCheckout}&{dateCheckin}&{adult}&{children}&{location}")
    @CrossOrigin("*")
    public List<Hotel> searchByParam(@PathVariable("dateCheckout") String dateCheckout,
                                     @PathVariable("dateCheckin") String dateCheckin,
                                     @PathVariable("adult") int adult,
                                     @PathVariable("children") int children,
                                     @PathVariable("location") String location) {
        return hotelService.searchHotelByDateAndPeople(dateCheckout,
                dateCheckin,
                adult,
                children,
                location);
    }

    @GetMapping(PATH + "/findHotelGood/{location}")
    @CrossOrigin("*")
    public List<Hotel> getGoodHotelByLocation(@PathVariable("location") String location) {
        return hotelService.getGoodHotelListByLocation(location);
    }
}
