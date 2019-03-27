package pl.socodeit.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "pl.socodeit.archunit")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule classesWithNameControllerShouldBeInPackageAdapter = classes().that().haveSimpleNameContaining("Controller")
            .should().resideInAPackage("..adapter.primary..");


    @ArchTest
    static void core_elements_should_not_depens_from_adapter(JavaClasses classes) {

        ArchRule rule  = noClasses().that().resideInAPackage("..core..").should().dependOnClassesThat().resideInAPackage("..adapter..");
        rule.check(classes);
    }

    @ArchTest
    static final ArchRule secondaryPortShouldContainsOnlyInterfaces = classes().that().resideInAPackage("..core.port.secondary..").should().beInterfaces();
}
