package pl.socodeit.archunit.adapter.secondary;

import pl.socodeit.archunit.core.port.DomainDto;
import pl.socodeit.archunit.core.port.secondary.StoreSecondaryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryDbSecondaryPort implements StoreSecondaryPort {

    private final List<MemoryEntity> memoryStore = new ArrayList<>();



    @Override
    public Optional<DomainDto> findByBusinessId(String businessId) {
        return memoryStore.stream().filter(me->me.getBusinessId().equals(businessId)).findAny().map(this::toDto);
    }

    private DomainDto toDto(MemoryEntity memoryEntity) {
        return new DomainDto(memoryEntity.getBusinessId());
    }

    @Override
    public void save(DomainDto dto) {
        memoryStore.add(toMemoryStore(dto));
    }

    private MemoryEntity toMemoryStore(DomainDto dto) {
        return new MemoryEntity();
    }
}
