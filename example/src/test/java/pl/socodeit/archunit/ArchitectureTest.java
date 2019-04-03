package pl.socodeit.archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import pl.socodeit.archunit.adapter.primary.MyControllerAnnotation;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "pl.socodeit.archunit")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule should_class_with_name_controller_should_be_in_adapter_primary_package = classes().that().haveSimpleNameContaining("Controller")
            .should().resideInAPackage("..adapter.primary..");


    @ArchTest
    static void core_elements_should_not_depens_from_adapter(JavaClasses classes) {

        ArchRule rule  = noClasses().that().resideInAPackage("..core..").should().dependOnClassesThat().resideInAPackage("..adapter..");
        rule.check(classes);
    }

    @ArchTest
    static final ArchRule secondary_port_should_be_interfaces = classes().that().resideInAPackage("..core.port.secondary..").should().beInterfaces();

    @ArchTest
    static final ArchRule primary_port_should_implement_interface_from_secondary =
            classes().that().resideInAnyPackage("..adapter.secondary..").and().haveSimpleNameContaining("Adapter")
                    .should().implement(resideInAnyPackage("..core.port.secondary.."));

    @ArchTest
    static final ArchRule controllers_should_be_annotated_with_annotation = classes().that().haveSimpleNameEndingWith("Controller").and().resideInAnyPackage(
            "..adapter.primary..").should().beAnnotatedWith(MyControllerAnnotation.class);

    @ArchTest
    static final ArchRule domain_services_should_be_not_public =
            classes().that().resideInAPackage("..core.port.primary..").and().haveSimpleNameEndingWith("Service").should().bePackagePrivate();

    @ArchTest
    static final ArchRule domain_services_facades_should_be_public =
            classes().that().resideInAPackage("..core.port.primary..").and().haveSimpleNameEndingWith("Facade").should().bePublic();


    @ArchTest
    static final ArchRule methods_in_domain_services_should_not_be_public =
            methods().that().areDeclaredInClassesThat(areServicesInPrimaryPort()).should().notBePublic();

    @ArchTest
    static final ArchRule fields_in_domain_services_should_private_and_final =
            fields().that().areDeclaredInClassesThat(areServicesInPrimaryPort()).should().haveModifier(JavaModifier.FINAL).andShould().bePrivate();

    private static DescribedPredicate<JavaClass> areServicesInPrimaryPort() {
        return packageForCorePrimaryPort().and(classIsService());
    }

    private static DescribedPredicate<JavaClass> classIsService() {
        return JavaClass.Predicates.simpleNameEndingWith(                    "Service");
    }

    private static DescribedPredicate<JavaClass> packageForCorePrimaryPort() {
        return JavaClass.Predicates.resideInAPackage("..core.port.primary..");
    }


}
