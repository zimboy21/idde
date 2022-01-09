package edu.bbte.idde.zdim1981.mapper;

import edu.bbte.idde.zdim1981.dto.incoming.MotherboardCreationDto;
import edu.bbte.idde.zdim1981.dto.outgoing.CpuDetailedDto;
import edu.bbte.idde.zdim1981.dto.outgoing.MotherboardDto;
import edu.bbte.idde.zdim1981.model.Motherboard;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class MotherboardMapper {
    public abstract Motherboard dtoToModel(MotherboardCreationDto dto);

    public abstract MotherboardDto modelToDto(Motherboard motherboard);

    @IterableMapping(elementTargetType = CpuDetailedDto.class)
    public abstract Collection<MotherboardDto> collectModelsToDtos(Collection<Motherboard> motherboards);
}
