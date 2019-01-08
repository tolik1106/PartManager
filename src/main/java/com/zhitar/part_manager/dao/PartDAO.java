package com.zhitar.part_manager.dao;

import com.zhitar.part_manager.entity.Part;

import java.util.List;

public interface PartDAO {

    public void addPart(Part part);
    public void updatePart(Part part);
    public void removePart(Integer id);
    public Part getPartById(Integer id);
    public List<Part> getAllParts();
}
