package com.code.packmundo.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import com.code.packmundo.models.Box;
import com.code.packmundo.models.BoxType;
import com.code.packmundo.models.UserBox;
import com.code.packmundo.models.repositories.BoxRepository;
import com.code.packmundo.models.repositories.BoxTypeRepository;
import com.code.packmundo.models.repositories.UserBoxRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoxService {

    private final BoxTypeRepository boxTypeRepository;
    private final BoxRepository boxRepository;
    private final UserBoxRepository userBoxRepository;

    @Autowired
    public BoxService(BoxTypeRepository boxTypeRepository, BoxRepository boxRepository, UserBoxRepository userBoxRepository) {
        this.boxTypeRepository = boxTypeRepository;
        this.boxRepository = boxRepository;
        this.userBoxRepository = userBoxRepository;
    }

    public Box saveBox(Box box) {
        boolean isNewBox = box.getUuid() == null;
        if (isNewBox) {
            box.setUuid(UUID.randomUUID());
        } else {
            box.setId(boxRepository.getIdByUuid(box.getUuid()));
        }
        box.setUpdateTime(LocalDateTime.now());
        box = boxRepository.save(box);
        if (isNewBox) {
            UserBox userBox = new UserBox(1, box.getId()); //todo userid
            userBoxRepository.save(userBox);
        }
        return box;
    }

    //type

    public Iterable<BoxType> getMainBoxTypes() {
        return boxTypeRepository.findByMainTypeIdIsNull();
    }

    public Iterable<BoxType> getSubBoxTypes(int mainTypeId) {
        return boxTypeRepository.findByMainTypeId(mainTypeId);
    }

    public HashMap<String, Object> getBoxTypeFields(int typeid) {
        Type mapType = new TypeToken<HashMap<String, Object>>(){}.getType();
        return new Gson().fromJson(boxTypeRepository.getFieldsById(typeid), mapType);
    }

    //test

    public Iterable<Box> getBoxes() {
        return boxRepository.findAll();
    }

}