package com.zhitar.part_manager.controller;

import com.zhitar.part_manager.entity.Part;
import com.zhitar.part_manager.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PartController {

    private PartService partService;

    private int sort = 0;
    private int showPage = 0;

    @Autowired
    @Qualifier(value = "partServiceImpl")
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/parts")
    public String parts(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page, @RequestParam(name = "sort", required = false) Optional<Integer> sort) {
        model.addAttribute("allParts", partService.getAllParts());
        model.addAttribute("part", new Part());
        model.addAttribute("parts", partService.findAll(createPageRequest(getPage(page.orElse(showPage)), getSort(sort.orElse(this.sort)))).getContent());
        model.addAttribute("minPart", partService.findMinNumber());
        model.addAttribute("page", showPage);
        return "parts";
    }

    @PostMapping("/parts/new")
    public String addPart(@ModelAttribute("part") @Valid Part part, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("parts", partService.findAll(createPageRequest(showPage, sort)).getContent());
            model.addAttribute("allParts", partService.getAllParts());
            model.addAttribute("minPart", partService.findMinNumber());
            if (part.getName() != null) {
                part.setName("");
                model.addAttribute("part", part);
            }
            return "/parts";
        }
        Part findPart = partService.findPartByName(part.getName());
        if (findPart != null) {
            int id = findPart.getId();
            part.setId(id);
            partService.updatePart(part);
        }
        if (part.getId() == 0) {
            partService.addPart(part);
        }
        return "redirect:/parts";
    }

    @PostMapping("/parts/find")
    public String findPartByName(@RequestParam(name = "name") String name, Model model) {
        Part fPart = partService.findPartByName(name);
        if(fPart == null) return "redirect:/parts";
        model.addAttribute("part", fPart);
        return "redirect:/partdata/" + fPart.getId();
    }

    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") Integer id) {
        partService.removePart(id);
        return "redirect:/parts";
    }

    @GetMapping("edit/{id}")
    public String editPart(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("part", partService.getPartById(id));
        model.addAttribute("allParts", partService.getAllParts());
        model.addAttribute("parts", partService.findAll(createPageRequest(showPage, sort)).getContent());
        model.addAttribute("minPart", partService.findMinNumber());
        return "parts";
    }

    @GetMapping("/partdata/{id}")
    public String partData(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("part", partService.getPartById(id));
        return "partdata";
    }

    @SuppressWarnings("deprecation")
    private Pageable createPageRequest(int page, int sort) {
        switch (sort) {
            case 1:
                return new PageRequest(page, 10, Sort.Direction.ASC, "necessary", "name");
            case 2:
                return new PageRequest(page, 10, Sort.Direction.DESC, "necessary", "name");
            default:
                return new PageRequest(page, 10, Sort.Direction.ASC, "name");
        }
    }

    public int getPage(int page) {
            showPage = page;
            return showPage;
    }

    public int getSort(int sort) {
            this.sort = sort;
            return this.sort;
    }
}
