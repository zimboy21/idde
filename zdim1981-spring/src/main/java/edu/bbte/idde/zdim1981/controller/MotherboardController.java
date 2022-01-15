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

import javax.persistence.EntityNotFoundException;
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
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }

    @PostMapping
    public MotherboardDto addMotherboardToCpu(@PathVariable("id") Long cpuId,
                                              @RequestBody @Valid MotherboardCreationDto motherboardCreationDto) {
        try {
            Cpu cpu = cpuDao.getById(cpuId);
            Collection<Motherboard> motherboardCollection = cpu.getMotherboardCollection();
            Motherboard auxMotherBoard = new Motherboard();
            Motherboard motherboard = motherboardMapper.dtoToModel(motherboardCreationDto);
            motherboard.setCpu(cpu);
            motherboardCollection.add(motherboard);
            cpu.setMotherboardCollection(motherboardCollection);
            cpuDao.save(cpu);
            motherboardCollection = cpu.getMotherboardCollection();
            for (Motherboard i : motherboardCollection) {
                auxMotherBoard = i;
            }
            return motherboardMapper.modelToDto(auxMotherBoard);
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{motherboardId}")
    public void delete(@PathVariable("id") Long cpuId, @PathVariable("motherboardId") Long motherboardId) {
        try {
            Cpu cpu = cpuDao.getById(cpuId);
            Collection<Motherboard> motherboardCollection = cpu.getMotherboardCollection();
            motherboardCollection.removeIf(motherboard -> motherboard.getId().equals(motherboardId));
            cpu.setMotherboardCollection(motherboardCollection);
            cpuDao.save(cpu);
        } catch (EntityNotFoundException e) {
            log.error(e.toString());
            throw new NotFoundException();
        }
    }
}
