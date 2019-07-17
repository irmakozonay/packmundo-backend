package com.code.packmundo.controllers;

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

    @RequestMapping(value = "maintypes")
    public Iterable<BoxType> getMainBoxTypes() {
        return boxService.getMainBoxTypes();
    }

    @RequestMapping(value = "subtypes")
    public Iterable<BoxType> getSubBoxTypes(@RequestParam(value = "maintypeid") int mainTypeId) {
        return boxService.getSubBoxTypes(mainTypeId);
    }

    @RequestMapping(value = "all")
    public Iterable<Box> getBoxes() {
        return boxService.getBoxes();
    }

    @PostMapping(value = "save")
    public Box saveBox(@RequestBody Box box) {
        return boxService.saveBox(box);
    }

}