package com.picpay.users.transactions;

import com.picpay.users.exception.TransactionNotAuthorizedException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class TransactionErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == CLIENT_ERROR || response.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            throw new TransactionNotAuthorizedException("Transaction not authorized");
        } else {
            MediaType contentType = response
                    .getHeaders()
                    .getContentType();

            throw new RestClientResponseException("Error trying to create a transaction", response.getRawStatusCode(), response.getStatusText(), response.getHeaders(), FileCopyUtils.copyToByteArray(response.getBody()), contentType.getCharset());
        }
    }
}
