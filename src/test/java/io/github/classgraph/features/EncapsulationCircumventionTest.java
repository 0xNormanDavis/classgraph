package io.github.classgraph.features;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassGraph.CircumventEncapsulationMethod;
import io.github.classgraph.ScanResult;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;

/**
 * Encapsulation circumvention test.
 */
class EncapsulationCircumventionTest {
    /** Test Narcissus. */
    @Test
    void testNarcissus() {
        ClassGraph.CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.NARCISSUS;
        ReflectionUtils.loadReflectionDriver();
        assertThat(ReflectionUtils.getStaticFieldVal(true, ReflectionUtils.class, "reflectionDriver").getClass()
                .getSimpleName()).isEqualTo("NarcissusReflectionDriver");
        try (ScanResult scanResult = new ClassGraph()
                .acceptPackages(EncapsulationCircumventionTest.class.getPackage().getName()).enableAllInfo()
                .scan()) {
            assertThat(scanResult.getAllClasses().getNames()).isNotEmpty();
        } finally {
            ClassGraph.CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.NONE;
            ReflectionUtils.loadReflectionDriver();
        }
    }

    /** Test JVM-Driver. */
    @Test
    void testJVMDriver() {
        ClassGraph.CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.JVM_DRIVER;
        ReflectionUtils.loadReflectionDriver();
        assertThat(ReflectionUtils.getStaticFieldVal(true, ReflectionUtils.class, "reflectionDriver").getClass()
                .getSimpleName()).isEqualTo("JVMDriverReflectionDriver");
        try (ScanResult scanResult = new ClassGraph()
                .acceptPackages(EncapsulationCircumventionTest.class.getPackage().getName()).enableAllInfo()
                .scan()) {
            assertThat(scanResult.getAllClasses().getNames()).isNotEmpty();
        } finally {
            ClassGraph.CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.NONE;
            ReflectionUtils.loadReflectionDriver();
        }
    }
}
