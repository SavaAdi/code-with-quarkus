package com.adisava.service;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GreetingServiceTest {

    @Inject
    GreetingService greetingService;

    @Test
    public void testGreetingServiceForYoungers() {
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> greetingService.greetingMessage(15));
    }

    @Test
    public void testGreetingServiceForTeenagers() {
        String message = greetingService.greetingMessage(18);
        Assertions.assertThat(message).isEqualTo("Hey boys and girls");
    }

    @Test
    public void testGreetingServiceForAdult() {
        String message = greetingService.greetingMessage(21);
        Assertions.assertThat(message).isEqualTo("Hey ladies and gentlemen");
    }

}