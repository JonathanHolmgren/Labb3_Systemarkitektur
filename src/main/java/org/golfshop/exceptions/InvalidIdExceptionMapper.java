package org.golfshop.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class InvalidIdExceptionMapper implements ExceptionMapper<InvalidIdException> {
    private static final Logger logger = LoggerFactory.getLogger(InvalidIdExceptionMapper.class);

    @Override
    public Response toResponse(InvalidIdException e)
    {
        logger.error(e.getMessage());
        return Response.status(Response.Status.fromStatusCode(404)).entity(e.getMessage()).build();
    }
}