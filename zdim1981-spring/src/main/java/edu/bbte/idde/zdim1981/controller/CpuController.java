package edu.bbte.idde.zdim1981.controller;

import edu.bbte.idde.zdim1981.controller.exception.NotFoundException;
import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.dto.incoming.CpuCreationDto;
import edu.bbte.idde.zdim1981.dto.outgoing.CpuDetailedDto;
import edu.bbte.idde.zdim1981.mapper.CpuMapper;
import edu.bbte.idde.zdim1981.model.Cpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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
            return cpuMapper.collectModelsToDtos(cpuDao.readAll());
        }
        return cpuMapper.collectModelsToDtos(cpuDao.readByMinClockSpeed(clockSpeed));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CpuDetailedDto getCpusById(@PathVariable("id") Long id) {
        Cpu cpu = cpuDao.read(id);
        if (cpu == null) {
            throw new NotFoundException();
        }
        return cpuMapper.modelToDto(cpu);
    }

    @PostMapping
    @ResponseBody
    public CpuDetailedDto create(@RequestBody @Valid CpuCreationDto cpuCreationDto) {
        Cpu cpu = cpuMapper.dtoToModel(cpuCreationDto);
        return cpuMapper.modelToDto(cpuDao.create(cpu));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CpuCreationDto cpuCreationDto) {
        Cpu cpu = cpuDao.read(id);
        if (cpu == null) {
            throw new NotFoundException();
        }
        cpuDao.update(cpuMapper.dtoToModel(cpuCreationDto), id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        if (!cpuDao.delete(id)) {
            throw new NotFoundException();
        }
    }
}