package com.metacraft.assetstore;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class GzipMimeFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    String url = req.getRequestURL().toString();

    if (url.endsWith(".js.gz")) {
      res.setContentType("application/javascript");
      res.setHeader("Content-Encoding", "gzip");
      //System.out.println(res.getContentType());
      //System.out.println(url);
    } else if (url.endsWith(".wasm.gz")) {
      res.setContentType("application/wasm");
      res.setHeader("Content-Encoding", "gzip");
      //System.out.println(url);
    } else if (url.endsWith(".data.gz")) {
      res.setContentType("application/octet-stream");
      res.setHeader("Content-Encoding", "gzip");
      System.out.println(url);
      System.out.println(res.getContentType());
    }

    chain.doFilter(request, response);
  }
}
