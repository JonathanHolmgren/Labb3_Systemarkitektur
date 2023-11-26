package org.golfshop.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class EmptyListExceptionMapper implements ExceptionMapper<EmptyListException> {

    private static final Logger logger = LoggerFactory.getLogger(EmptyListExceptionMapper.class);

    @Override
    public Response toResponse(EmptyListException e) {
        logger.error(e.getMessage());
        return Response.status(Response.Status.fromStatusCode(204)).entity(e.getMessage()).build();
    }
}
