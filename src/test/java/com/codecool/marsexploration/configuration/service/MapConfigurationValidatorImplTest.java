package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

class MapConfigurationValidatorImplTest {

    private static final String mountainSymbol = "#";
    private static final String pitSymbol = "&";
    private static final String mineralSymbol = "%";
    private static final String waterSymbol = "*";

    private static final MapElementConfiguration mountainsCfg = new MapElementConfiguration(
            mountainSymbol,
            "mountain",
            List.of(
                    new ElementToSize(2, 20),
                    new ElementToSize(1, 30)
            ),
            3,
            ""
    );

    private static final MapElementConfiguration pitsCfg = new MapElementConfiguration(
            pitSymbol,
            "pits",
            List.of(
                    new ElementToSize(2, 10),
                    new ElementToSize(1, 20)
            ),
            10,
            ""
    );

    private static final MapElementConfiguration mineralsCfg = new MapElementConfiguration(
            mineralSymbol,
            "minerals",
            List.of(new ElementToSize(10, 1)),
            0,
            ""
    );

    private static final MapElementConfiguration watersCfg = new MapElementConfiguration(
            waterSymbol,
            "waters",
            List.of(new ElementToSize(10, 1)),
            0,
            ""
    );

    private static final MapElementConfiguration invalid_watersCfg = new MapElementConfiguration(
            waterSymbol,
            "waters",
            List.of(new ElementToSize(10, 1)),
            10,
            ""
    );

    private static final MapElementConfiguration invalid_mountainsCfg = new MapElementConfiguration(
            mountainSymbol,
            "mountain",
            List.of(
                    new ElementToSize(2, 1),
                    new ElementToSize(1, 1)
            ),
            3,
            ""
    );

    private final static List<MapElementConfiguration> VALID_TEST_CONFIG_1 = List.of(mountainsCfg, pitsCfg, mineralsCfg, watersCfg);
    private final static List<MapElementConfiguration> INVALID_TEST_CONFIG_1 = List.of(mountainsCfg, pitsCfg, mineralsCfg, invalid_watersCfg);
    private final static List<MapElementConfiguration> INVALID_TEST_CONFIG_2 = List.of(invalid_mountainsCfg, invalid_watersCfg);
    private final static List<MapElementConfiguration> INVALID_TEST_CONFIG_3 = List.of(invalid_mountainsCfg, pitsCfg, mineralsCfg, watersCfg);

    public static Stream<Arguments> parameters() {
        return Stream.of(
                // NORMAL VALID TEST
                of(true, new MapConfiguration(
                        1000,
                        0.5,
                        VALID_TEST_CONFIG_1)),
                // CHECK THE SMALLER OF EQUAL RATIO CASE:
                of(true, new MapConfiguration(
                        260,
                        0.5,
                        VALID_TEST_CONFIG_1)),
                // CHECK IF THE RATIO IS LOWER:
                of(false, new MapConfiguration(
                        100,
                        0.5,
                        VALID_TEST_CONFIG_1)),
                // CHECK AN INVALID CONFIGURATION LIST
                of(false, new MapConfiguration(
                        1000,
                        0.5,
                        INVALID_TEST_CONFIG_1)),
                // CHECK ANOTHER INVALID CONFIGURATION LIST
                of(false, new MapConfiguration(
                        1000,
                        0.5,
                        INVALID_TEST_CONFIG_2)),
                // CHECK ANOTHER INVALID CONFIGURATION LIST
                of(false, new MapConfiguration(
                        1000,
                        0.5,
                        INVALID_TEST_CONFIG_3))
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void validate(boolean expected, MapConfiguration mapConfiguration) {
        MapConfigurationValidatorImpl mapConfigurationValidator = new MapConfigurationValidatorImpl();
        boolean actual = mapConfigurationValidator.validate(mapConfiguration);

        assertEquals(expected, actual);
    }
}