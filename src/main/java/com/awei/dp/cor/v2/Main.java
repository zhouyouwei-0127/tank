package com.awei.dp.cor.v2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "request";
        Response response = new Response();
        response.str = "response";
        FilterChain filterChain = new FilterChain();
        filterChain.add(new Add1Filter()).add(new Add2Filter());
        filterChain.doFilter(request,response);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}

class Response {
    String str;
}

class Request {
    String str;
}

class Add1Filter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        request.str += " 1 ";
        filterChain.doFilter(request,response);
        response.str += " 1 ";
        return true;
    }
}

class Add2Filter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        request.str += " 2 ";
        filterChain.doFilter(request,response);
        response.str += " 2 ";
        return true;
    }
}

interface Filter {
    boolean doFilter(Request request, Response response, FilterChain filterChain);
}

class FilterChain {

    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public boolean doFilter(Request request, Response response) {
        if (index == filters.size()) {
            return false;
        }
        Filter filter = filters.get(index);
        index++;
        return filter.doFilter(request,response,this);
    }
}