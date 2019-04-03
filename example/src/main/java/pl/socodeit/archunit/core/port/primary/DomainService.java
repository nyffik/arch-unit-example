package pl.socodeit.archunit.core.port.primary;

import lombok.RequiredArgsConstructor;
import pl.socodeit.archunit.core.port.DomainDto;
import pl.socodeit.archunit.core.port.secondary.StoreSecondaryPort;

import java.util.Optional;

@RequiredArgsConstructor
class DomainService {

    private final StoreSecondaryPort storeSecondaryPort;

    void doSomething(String businessId) {
        Optional<DomainDto> dto = storeSecondaryPort.findByBusinessId(businessId);
        dto.ifPresent(this::doAndStore);
    }

    private void doAndStore(DomainDto dto) {
        dto.doSomething();

        storeSecondaryPort.save(dto);
    }
}
