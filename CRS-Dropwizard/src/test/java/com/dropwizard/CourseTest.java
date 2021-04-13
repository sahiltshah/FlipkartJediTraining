package com.dropwizard;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.flipkart.bean.Course;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;




public class CourseTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

        @Test
        public void serializesToJSON() throws Exception {

        final Course course = new Course(108,"Social Science",0,500,-1);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/course.json"), Course.class));

        assertThat(MAPPER.writeValueAsString(course)).isEqualTo(expected);
        }


        @Test
        public void deserializesFromJSON() throws Exception {
            final Course course = new Course(108,"Social Science",0,500,-1);

            assertThat(MAPPER.readValue(fixture("fixtures/person.json"), Course.class)).isEqualTo(course);
        }


}
