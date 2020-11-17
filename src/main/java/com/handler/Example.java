package com.handler;

import com.benchmark.BenchMark;
import com.benchmark.ImageRotationBenchMark;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import java.io.BufferedWriter;

public class Example implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        BufferedWriter writer = response.getWriter();

        // run the main code for benchmark cpu
        BenchMark bm = new BenchMark();
        bm.service(request,response);

        // run the main code for the benchmark ram
        ImageRotationBenchMark irb = new ImageRotationBenchMark();
        irb.service(request,response);


        writer.write("Hello world!");

    }

}