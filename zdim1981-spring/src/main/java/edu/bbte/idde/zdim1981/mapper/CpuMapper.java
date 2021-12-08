package edu.bbte.idde.zdim1981.mapper;

import edu.bbte.idde.zdim1981.dto.incoming.CpuCreationDto;
import edu.bbte.idde.zdim1981.dto.outgoing.CpuDetailedDto;
import edu.bbte.idde.zdim1981.model.Cpu;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class CpuMapper {
    public abstract Cpu dtoToModel(CpuCreationDto dto);

    public abstract CpuDetailedDto modelToDto(Cpu cpu);

    @IterableMapping(elementTargetType = CpuDetailedDto.class)
    public abstract Collection<CpuDetailedDto> collectModelsToDtos(Collection<Cpu> cpus);
}
