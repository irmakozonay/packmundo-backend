package com.code.packmundo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.code.packmundo.models.Box;
import com.code.packmundo.models.BoxType;
import com.code.packmundo.models.repositories.BoxRepository;
import com.code.packmundo.models.repositories.BoxTypeRepository;

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

}