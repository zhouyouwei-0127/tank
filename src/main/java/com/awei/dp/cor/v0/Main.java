package com.awei.dp.cor.v0;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "request";
        Response response = new Response();
        response.str = "response";
        FilterChain chain = new FilterChain();
        chain.add(new AddHello()).add(new AddWord()).add(new AddPerfect());
        chain.doFilter(request,response);
        System.out.println("request:\t" + request.str + "\n" + "response\t" + response.str);
    }
}

class Request {
    String str;
}

class Response {
    String str;
}

interface Filter {
    void doFilter(Request request,Response response,FilterChain chain);
}

class AddHello implements Filter {

    @Override
    public void doFilter(Request request,Response response,FilterChain chain) {
        request.str += " Hello ";
        chain.doFilter(request,response);
        response.str += " Hello ";
    }
}

class AddWord implements Filter {

    @Override
    public void doFilter(Request request, Response response,FilterChain chain) {
        request.str += " Word ";
        chain.doFilter(request,response);
        response.str += " Word ";
    }
}

class AddPerfect implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str += " Perfect ";
        chain.doFilter(request,response);
        response.str += " Perfect ";
    }
}

class FilterChain {
    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request,response,this);
    }
}
