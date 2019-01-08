package com.zhitar.part_manager.service;

import com.zhitar.part_manager.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartService {

    public void addPart(Part part);
    public void updatePart(Part part);
    public void removePart(Integer id);
    public Part getPartById(Integer id);
    public List<Part> getAllParts();
    public Part findMinNumber();
    public Part findPartByName(String name);
    Page<Part> findAll(Pageable pageable);

}
