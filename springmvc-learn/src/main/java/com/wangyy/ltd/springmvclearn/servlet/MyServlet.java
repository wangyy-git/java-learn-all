package com.wangyy.ltd.springmvclearn.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

//@WebServlet(name = "servlet",urlPatterns = "/hello")
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }
//ss宿舍
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
        super.doGet(req, resp);
        String json = "";
        PrintWriter writer = resp.getWriter();
        writer.write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    
}
