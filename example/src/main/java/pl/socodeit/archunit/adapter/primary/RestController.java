package pl.socodeit.archunit.adapter.primary;

import lombok.RequiredArgsConstructor;
import pl.socodeit.archunit.core.port.primary.DomainServiceFacade;

@RequiredArgsConstructor
@MyControllerAnnotation
class RestController {

    private final DomainServiceFacade domainServiceFacade;

    void restCall(SomeRequest request) {
        domainServiceFacade.doSomething(request.getId());
    }

}
