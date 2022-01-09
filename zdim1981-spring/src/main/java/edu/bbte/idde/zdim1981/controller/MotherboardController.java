package edu.bbte.idde.zdim1981.controller;

import edu.bbte.idde.zdim1981.controller.exception.NotFoundException;
import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.dto.incoming.MotherboardCreationDto;
import edu.bbte.idde.zdim1981.dto.outgoing.MotherboardDto;
import edu.bbte.idde.zdim1981.mapper.MotherboardMapper;
import edu.bbte.idde.zdim1981.model.Cpu;
import edu.bbte.idde.zdim1981.model.Motherboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/cpus/{id}/motherboards")
public class MotherboardController {
    @Autowired
    private CpuDao cpuDao;
    @Autowired
    private MotherboardMapper motherboardMapper;

    @GetMapping
    public Collection<Motherboard> findMotherboardsForCpu(@PathVariable("id") Long cpuId) {
        try {
            Cpu cpu = cpuDao.getById(cpuId);
            return cpu.getMotherboardCollection();
        } catch (NotFoundException e) {
            log.error(e.toString());
            return null;
        }
    }

    @PostMapping
    public MotherboardDto addMotherboardToCpu(@PathVariable("id") Long cpuId,
                                              @RequestBody @Valid MotherboardCreationDto motherboardCreationDto) {
        try {
            Cpu cpu = cpuDao.getById(cpuId);
            Collection<Motherboard> motherboardCollection = cpu.getMotherboardCollection();
            Motherboard motherboard = motherboardMapper.dtoToModel(motherboardCreationDto);
            motherboard.setCpu(cpu);
            motherboardCollection.add(motherboard);
            cpu.setMotherboardCollection(motherboardCollection);
            cpuDao.save(cpu);
            return motherboardMapper.modelToDto(motherboard);
        } catch (NotFoundException e) {
            log.error(e.toString());
            return null;
        }
    }

    @DeleteMapping("/{motherboardId}")
    public void delete(@PathVariable("id") Long cpuId, @PathVariable("motherboardId") Long motherboardId) {
        try {
            Cpu cpu = cpuDao.getById(cpuId);
            Collection<Motherboard> motherboardCollection = cpu.getMotherboardCollection();
            motherboardCollection.removeIf(motherboard -> motherboard.getId() == motherboardId);
            cpu.setMotherboardCollection(motherboardCollection);
            cpuDao.save(cpu);
        } catch (NotFoundException e) {
            log.error(e.toString());
        }
    }
}
