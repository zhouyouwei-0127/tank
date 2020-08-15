package com.awei.dp.cor.v1;

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
        filterChain.doFilter(request,response,filterChain);
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

interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}

class Add1Filter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str += " 1 ";
        filterChain.doFilter(request,response,filterChain);
        response.str += " 1 ";
    }
}

class Add2Filter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str += " 2 ";
        filterChain.doFilter(request,response,filterChain);
        response.str += " 2 ";
    }
}

class FilterChain implements Filter {

    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request,response,filterChain);
    }
}
