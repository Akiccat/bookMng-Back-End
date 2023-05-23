package com.accenture.powerup.bookmng.fliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.wrapper.FieldErrorEntity;
import com.accenture.powerup.bookmng.wrapper.ResponseBodyBindWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    private static final String VALIDATION_FAILURE = "VALIDATION_FAILURE";
    private static final String BUSINESS_FAILURE = "BUSINESS_FAILURE";

    private static final Set<Class<?>> CLASSES_HANDLED_BY_VALIDATION_ERROR_BUILDER_ADVICE =
        new HashSet<>(Arrays.asList(BusinessFailureException.class));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            if (exception.getCause() instanceof BusinessFailureException) {
                hundleException(exception.getCause(), response);
            } else if (exception.getCause() != null
                && CLASSES_HANDLED_BY_VALIDATION_ERROR_BUILDER_ADVICE.contains(exception.getCause().getClass())) {
                handleByValidationErrorBuilderAdvice(exception, response);
            } else {
                unHandleException(response, exception);
            }
        }
    }

    private void handleByValidationErrorBuilderAdvice(Exception exception, HttpServletResponse response) {
        Exception cause = (Exception) exception.getCause();
        if (cause instanceof BusinessFailureException) {
            ResponseBodyBindWrapper responseBody = createBusinessFailureExceptionResponse((BusinessFailureException) cause);
            writeToResponse(response, responseBody);
        }
    }

    private void unHandleException(HttpServletResponse response, Exception exception) {
        try {
            ResponseBodyBindWrapper responseBody = new ResponseBodyBindWrapper("SYSTEM_ERROR",
                exception, new ArrayList<>());
            LOGGER.debug(exception.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            String responseJson = mapper.writeValueAsString(responseBody);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseJson);
        } catch (Exception e) {
            LOGGER.warn("response has already closed.", e);
        }
    }

    private ResponseBodyBindWrapper createBusinessFailureExceptionResponse(BusinessFailureException exception) {

        Errors errors = exception.getErrors();
        try {
            if (errors != null) {
                return createValidationErrorsResponseByErrors(errors);
            }
        } catch (Exception e) {
            LOGGER.warn("createBusinessFailureExceptionResponse failed.", e);
        }
        return new ResponseBodyBindWrapper(exception.getIsFormInvalid() ? VALIDATION_FAILURE : BUSINESS_FAILURE , new HashMap<>(),
            new ArrayList<>());
    }


    private void writeToResponse(HttpServletResponse response, ResponseBodyBindWrapper responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String responseJson = mapper.writeValueAsString(responseBody);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseJson);
        } catch (Exception e) {
            LOGGER.warn("response has already closed.", e);
        }
    }

    private ResponseBodyBindWrapper createValidationErrorsResponseByErrors(Errors errors) {
        List<FieldErrorEntity> resultfieldErrors = new ArrayList<>();
        try {
            List<FieldError> fieldErrors = errors.getFieldErrors();
            for (FieldError fe : fieldErrors) {
                FieldErrorEntity errorContent = new FieldErrorEntity();
                errorContent.setField(fe.getField());
                String message = getMessage(fe);
                errorContent.setMessage(message);
                resultfieldErrors.add(errorContent);
            }

        } catch (Exception ex) {
            LOGGER.warn("createValidationErrorsResponseByErrors failed", ex);
        }

        return new ResponseBodyBindWrapper(VALIDATION_FAILURE, new HashMap<>(), resultfieldErrors);
    }

    private String getMessage(ObjectError objectError) {
        return objectError != null ? objectError.getDefaultMessage() : null;
    }

    private void hundleException(Throwable exception, HttpServletResponse response) {
        responseValidationFailure((BusinessFailureException) exception, response);
    }

    private void responseValidationFailure(BusinessFailureException exception, HttpServletResponse response) {
        if (null != exception.getCause()) {
            LOGGER.info("Validation error has occurred.", exception);
        }

        List<FieldErrorEntity> validations = new ArrayList<>();

        if (!exception.getIsFormInvalid()) {
            writeResponseData(new ResponseBodyBindWrapper(BUSINESS_FAILURE, new HashMap<>(), validations), response);
            return;
        }

        Errors errors = exception.getErrors();
        if (errors != null) {
            errors.getFieldErrors().forEach(error -> {
                FieldErrorEntity fieldErrorEntity = new FieldErrorEntity(getMessage(error), error.getField());
                validations.add(fieldErrorEntity);
            });
        }

        writeResponseData(new ResponseBodyBindWrapper(VALIDATION_FAILURE, new HashMap<>(), validations), response);
    }

    private void writeResponseData(Object responseData, HttpServletResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String responseJson = mapper.writeValueAsString(responseData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseJson);
        } catch (Exception e) {
            LOGGER.warn("response has already closed.", e);
        }
    }
}
