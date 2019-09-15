package com.adapterj.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adapterj.registry.RegistryFactory;
import com.adapterj.web.Htmlable;
import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.db.QueryException;
import com.adapterj.example.db.SourceQuery;
import com.adapterj.example.db.VersionQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;

/**
 * Insert or Update the source into database
 * 
 * @author York/GuangYu DENG
 */
@SuppressWarnings("unused")
public class SimpleInsertOrUpdateServlet extends InitServlet {

	private static final long serialVersionUID = -1317969295329107435L;
	private static final boolean DEBUG = Debugger.DEBUG;
	private static final String TAG = SimpleInsertOrUpdateServlet.class.getName();

	@Override
	protected void doPost(HttpServletRequest httpRequest, 
						  HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		final SimpleHttpParametersResolver<Source> resolver = new SimpleHttpParametersResolver<Source>(RegistryFactory.getRegistry());
		final Source parameter = resolver.getParameter(httpRequest);
		if (parameter == null) {
			final String error = (
					"IllegalArgumentException: \n" + 
					"http parameter resolver return a null. \n" + 
					"See your POJO define, or the HttpParametersResolver for more information.");
			throw new IllegalArgumentException(error);
		}

		Long id = null;
		String html = null;
		try {
			final SourceQuery query = SourceQuery.getInstance();
			
			Source pojo = null;
			if (query.exists(parameter)) {
				pojo = query.update(parameter);
			} else {
				pojo = query.create(parameter, VersionQuery.getInstance().findLatest());
			}
			
			id = pojo.getId();
			html = pojo.toHTMLString();
		} catch (QueryException e) {
			html = Log.getStackTraceHTMLString(e);
		}

		final String baseURI = getBaseURI(httpRequest);
		final StringBuffer s = new StringBuffer(baseURI);
		
		httpResponse.setContentType("text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("utf-8");

		final String ifRedirect = getServletConfig().getInitParameter("redirect");
		if ("true".equalsIgnoreCase(ifRedirect)) {
			s.delete(baseURI.length(), s.length());
			final String link = s.append("simple/view/html").append('?').append("source.id").append('=').append(id).toString().toString();
			
			httpResponse.sendRedirect(link);
		} else {
			s.delete(baseURI.length(), s.length());
			final String link = s.append("simple/form/html").append('?').append("source.id").append('=').append(id).toString().toString();
			
			final PrintWriter writer = httpResponse.getWriter();
			writer.print(render(html, link).toHTMLString());
			writer.flush();
		}
	}
	
	/**
	 * 
	 * @param html
	 * @param link
	 * @return
	 */
	protected final Htmlable render(final String html, final String link) {
		final WelcomePage doc = new WelcomePage("Echo");
		doc.writeln("<div class=\"page-header\">");
		doc.writeln("<h1>Echo: </h1>");
		doc.writeln("</div>");
		doc.writeln("<div class=\"content\">");
		doc.write("<p>");
		doc.write(html);
		doc.write("</p>");
		doc.write("<p>");
		doc.write(String.format("<a href=\"%s\">%s</a>", link, "Edit"));
		doc.writeln("</p>");
		doc.writeln("</div>");
		return (doc);
	}
}
