package com.jap.springvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jap.springvalidation.dto.response.FieldErrorValidation;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ResponseBodyMatchers {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> ResultMatcher containsObjectAsJson(Object expectedObject, Class<T> targetClass) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, targetClass);
            assertThat(expectedObject).isEqualToComparingFieldByField(actualObject);
        };
    }

    public ResultMatcher containsError(String expectedFieldName, String expectedMessage) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            ErrorResult errorResult = objectMapper.readValue(json, ErrorResult.class);
            List<FieldErrorValidation> fieldErrors = errorResult.getErrors().stream()
                    .filter(fieldError -> fieldError.getField().equals(expectedFieldName))
                    .filter(fieldError -> fieldError.getMessage().equals(expectedMessage))
                    .collect(Collectors.toList());

            assertThat(fieldErrors)
                    .hasSize(1)
                    .withFailMessage("expecting exactly 1 error message with field name '%s' and message '%s'",
                            expectedFieldName,
                            expectedMessage);
        };
    }


    public static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }
}
