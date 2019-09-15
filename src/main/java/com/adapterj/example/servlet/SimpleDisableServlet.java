package com.adapterj.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adapterj.registry.RegistryFactory;
import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;
import com.adapterj.ext.web.AbstractPage;

import com.adapterj.logging.Log;

import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;

/**
 * Delete a source from database. When user first click delete (GET) in list (sourcelist.html), this Servlet response 
 * a confirm view (sourceview.html), and while user click confirm POST a confirm and delete, this Servlet do delete 
 * indeed and response a new list (sourcelist.html).
 * 
 * @author York/GuangYu DENG
 */
public class SimpleDisableServlet extends InitServlet {

	private static final long serialVersionUID = -1317969295329107435L;

	@Override
	protected void doGet(HttpServletRequest httpRequest, 
						 HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		super.doGet(httpRequest, httpResponse);
	}

	@Override
	protected void doPost(HttpServletRequest httpRequest, 
						  HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		final SimpleHttpParametersResolver<Source> resolver = 
				new SimpleHttpParametersResolver<Source>(RegistryFactory.getRegistry());
		final Source source = resolver.getParameter(httpRequest);

		PrintWriter writer = httpResponse.getWriter();
		writer.print(renderDocument(source));
		writer.flush();
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	protected final AbstractPage renderDocument(final Source source) {
		WelcomePage doc = new WelcomePage("AdapterJ Example");
		doc.setOwnerClass(getClass().getSimpleName());
		doc.writeln("<div class=\"page-header\"><h1>Echo POST Form: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.writeln("<ul>");
		doc.write("POST and INSERT: ");
		doc.writeln("<li>");
		doc.write(source.toString());
		doc.writeln("</li>");
		doc.writeln("</ul>");
		doc.writeln("</div>");
		return doc;
	}
	
	/**
	 * 
	 * @param throwable
	 * @return
	 */
	protected final AbstractPage renderDocument(final Throwable throwable) {
		WelcomePage doc = new WelcomePage("AdapterJ Example");
		doc.setOwnerClass(getClass().getSimpleName());
		doc.writeln("<div class=\"page-header\"><h1>Echo POST Form: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(throwable));
		doc.writeln("</div>");
		return doc;
	}
}
