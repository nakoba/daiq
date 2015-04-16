package daiq.rest;

import daiq.core.lang.AppException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception ex) {
        return AppException.of(ex)
            .map(m ->
                Response.status(Integer.parseInt(m.code()))
                .entity(m.text())
                .type(MediaType.APPLICATION_JSON)
                .build())
            .orElse(Response.status(500)
                .entity(ex.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build()
        );
    }
}
