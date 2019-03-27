package pl.socodeit.archunit.adapter.primary;

import lombok.RequiredArgsConstructor;
import pl.socodeit.archunit.core.port.primary.DomainService;

@RequiredArgsConstructor
class RestController {

    private final DomainService domainService;

    void restCall(SomeRequest request) {
        domainService.doSomething(request.getId());
    }

}
