package com.code.packmundo.controllers;

import java.util.HashMap;

import com.code.packmundo.models.Box;
import com.code.packmundo.models.BoxType;
import com.code.packmundo.services.BoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/box")
public class BoxController {

    private final BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping(value = "save")
    public Box saveBox(@RequestBody Box box) {
        return boxService.saveBox(box);
    }

    //boxtype

    @RequestMapping(value = "type/mains")
    public Iterable<BoxType> getMainBoxTypes() {
        return boxService.getMainBoxTypes();
    }

    @RequestMapping(value = "type/subs")
    public Iterable<BoxType> getSubBoxTypes(@RequestParam(value = "maintypeid") int mainTypeId) {
        return boxService.getSubBoxTypes(mainTypeId);
    }

    @RequestMapping(value = "type/fields")
    public HashMap<String, Object> getBoxTypeFields(@RequestParam(value = "typeid") int typeid) {
        return boxService.getBoxTypeFields(typeid);
    }

    //test

    @RequestMapping(value = "all")
    public Iterable<Box> getBoxes() {
        return boxService.getBoxes();
    }

}