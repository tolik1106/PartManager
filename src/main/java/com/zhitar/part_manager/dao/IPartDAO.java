package com.zhitar.part_manager.dao;

import com.zhitar.part_manager.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPartDAO extends JpaRepository<Part, Integer> {

    @Query("from Part where number = (select min(p.number) from Part p where necessary = true)")
    public List<Part> findMinNumber();

    public Part findPartByName(String name);

    Page<Part> findAll(Pageable pageable);
}
