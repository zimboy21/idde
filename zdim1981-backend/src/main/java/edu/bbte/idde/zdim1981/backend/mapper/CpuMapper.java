package edu.bbte.idde.zdim1981.backend.mapper;

import edu.bbte.idde.zdim1981.backend.dto.Cpu;

public class CpuMapper {
    public Cpu cpuToDto(edu.bbte.idde.zdim1981.backend.model.Cpu cpu) {
        Cpu cpuDto = new Cpu();
        cpuDto.setClockSpeed(cpu.getClockSpeed());
        cpuDto.setCoreCount(cpu.getCoreCount());
        cpuDto.setName(cpu.getName());
        cpuDto.setOverClocking(cpu.getOverClocking());
        cpuDto.setPrice(cpu.getPrice());
        cpuDto.setId(cpu.getId());
        return cpuDto;
    }
}
