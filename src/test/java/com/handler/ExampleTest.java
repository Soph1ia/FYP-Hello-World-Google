package com.handler;

import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class ExampleTest {
    @Mock private HttpRequest request;
    @Mock private HttpResponse response;

    private BufferedWriter writerOut;
    private StringWriter responseOut;
    private static final Gson gson = new Gson();

    @Before
    public void beforeTest() throws IOException {
        MockitoAnnotations.initMocks(this);

        // use an empty string as the default request content
        BufferedReader reader = new BufferedReader(new StringReader(""));
        when(request.getReader()).thenReturn(reader);

        responseOut = new StringWriter();
        writerOut = new BufferedWriter(responseOut);
        when(response.getWriter()).thenReturn(writerOut);
    }

    @Test
    public void helloHttp_noParamsGet() throws Exception {
        new Example().service(request, response);

        writerOut.flush();
        assertThat(responseOut.toString()).isEqualTo("Hello world!");
    }
}