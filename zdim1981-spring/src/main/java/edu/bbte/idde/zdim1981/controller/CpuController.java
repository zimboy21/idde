package edu.bbte.idde.zdim1981.controller;

import edu.bbte.idde.zdim1981.controller.exception.NotFoundException;
import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.dto.incoming.CpuCreationDto;
import edu.bbte.idde.zdim1981.dto.outgoing.CpuDetailedDto;
import edu.bbte.idde.zdim1981.mapper.CpuMapper;
import edu.bbte.idde.zdim1981.model.Cpu;
import edu.bbte.idde.zdim1981.model.LastUpdatedAt;
import edu.bbte.idde.zdim1981.model.LastViewedAt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/cpus")
public class CpuController {
    @Autowired
    CpuDao cpuDao;
    @Autowired
    CpuMapper cpuMapper;

    @GetMapping
    @ResponseBody
    public Collection<CpuDetailedDto> getCpus(@RequestParam(value = "clockSpeed",
            required = false) Integer clockSpeed) {
        if (clockSpeed == null) {
            return cpuMapper.collectModelsToDtos(cpuDao.findAll());
        }
        return cpuMapper.collectModelsToDtos(cpuDao.readByClockSpeed(clockSpeed));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CpuDetailedDto getCpusById(@PathVariable("id") Long id) {
        try {
            Cpu cpu = cpuDao.getById(id);
            cpu.setLastViewedAt(new LastViewedAt(LocalDate.now(), LocalTime.now()));
            return cpuMapper.modelToDto(cpu);
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }

    @PostMapping
    @ResponseBody
    public CpuDetailedDto create(@RequestBody @Valid CpuCreationDto cpuCreationDto) {
        Cpu cpu = cpuMapper.dtoToModel(cpuCreationDto);
        return cpuMapper.modelToDto(cpuDao.saveAndFlush(cpu));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CpuCreationDto cpuCreationDto) {
        try {
            Cpu cpu = cpuMapper.dtoToModel(cpuCreationDto);
            cpu.setId(id);
            cpu.setLastUpdatedAt(new LastUpdatedAt(LocalDate.now(), LocalTime.now()));
            cpuDao.save(cpu);
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        try {
            cpuDao.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }
}