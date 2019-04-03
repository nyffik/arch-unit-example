package pl.socodeit.archunit.core.port.primary;

import lombok.RequiredArgsConstructor;
import pl.socodeit.archunit.core.port.DomainDto;

import java.util.Optional;

@RequiredArgsConstructor
public class DomainServiceFacade {

    private final DomainService domainService;

    public void doSomething(String businessId) {
        domainService.doSomething(businessId);
    }
}
