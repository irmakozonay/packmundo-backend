package com.code.packmundo.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.code.packmundo.models.Box;
import com.code.packmundo.models.BoxType;
import com.code.packmundo.models.repositories.BoxRepository;
import com.code.packmundo.models.repositories.BoxTypeRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxService {

    private final BoxTypeRepository boxTypeRepository;
    private final BoxRepository boxRepository;

    @Autowired
    public BoxService(BoxTypeRepository boxTypeRepository, BoxRepository boxRepository) {
        this.boxTypeRepository = boxTypeRepository;
        this.boxRepository = boxRepository;
    }

    public Iterable<BoxType> getMainBoxTypes() {
        return boxTypeRepository.findByMainTypeIdIsNull();
    }

    public Iterable<BoxType> getSubBoxTypes(int mainTypeId) {
        return boxTypeRepository.findByMainTypeId(mainTypeId);
    }

    public Iterable<Box> getBoxes() {
        return boxRepository.findAll();
    }

    public Box saveBox(Box box) {
        if (box.getUuid() == null) {
            box.setUuid(UUID.randomUUID());
        } else {
            box.setId(boxRepository.getIdByUuid(box.getUuid()));
        }
        box.setUpdateTime(LocalDateTime.now());
        return boxRepository.save(box);
    }

    public HashMap<String, Object> getBoxTypeFields(int typeid) {
        Type mapType = new TypeToken<HashMap<String, Object>>(){}.getType();
        return new Gson().fromJson(boxTypeRepository.getFieldsById(typeid), mapType);
    }

}