package pl.socodeit.archunit.core.port.secondary;

import pl.socodeit.archunit.core.port.DomainDto;

import java.util.Optional;

public interface StoreSecondaryPort {
    Optional<DomainDto> findByBusinessId(String businessId);

    void save(DomainDto dto);
}
