package com.cv;

import com.cv.api.LabseqApi;
import com.cv.service.LabseqService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.Getter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

/**
 * The LabseqApiTest.
 *
 * @author carlosvicente
 * @version 1.0.0
 * @since 01/10/2023
 */
@Getter
@QuarkusTest
@TestHTTPEndpoint(LabseqApi.class)
public class LabseqApiTest {

    @Inject
    LabseqService service;

    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000})
    void testTimeLimit(final int n) {
        given()
                    .pathParam("n", n)
               .when()
                    .get("/{n}")
               .then()
                    .statusCode(200)
                    .time(lessThan(10000L));
    }


    @ParameterizedTest
    @ValueSource(ints = {50})
    void testLabSeqOne(final int n) {
        given()
                    .pathParam("n", n)
                .when()
                    .get("/{n}")
                .then()
                    .statusCode(200)
                    .body(containsString("\"value\":\"8505\""));
    }
}
