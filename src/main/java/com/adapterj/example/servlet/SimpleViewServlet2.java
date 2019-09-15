package com.adapterj.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adapterj.registry.RegistryFactory;
import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.HttpParametersResolver;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;
import com.adapterj.ext.web.AbstractPage;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;

/**
 * Show a source in read only view
 * 
 * @author York/GuangYu DENG
 */
public class SimpleViewServlet2 extends InitServlet {

	private static final long serialVersionUID = -1317969295329107435L;
	
	@SuppressWarnings("unused")
	private static final boolean DEBUG = Debugger.DEBUG;
	
    @SuppressWarnings("unused")
	private static final String TAG = SimpleViewServlet2.class.getName();

    private final Properties _properties = new Properties();

    @SuppressWarnings("unused")
	private ServletContext _context;

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		try {
//			_properties.load(SimpleViewServlet2.class.getResourceAsStream("/adapterj.http.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		_context = config.getServletContext();
//	}

	@Override
	protected void doGet(HttpServletRequest httpRequest, 
						 HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		final HttpParametersResolver<Source> resolver = new SimpleHttpParametersResolver<Source>(RegistryFactory.getRegistry());
		final Source pSrc = resolver.getParameter(httpRequest);
		if (pSrc == null) {
			final String error = (
					"IllegalArgumentException: http parameters resolver return a null. " + 
					"See your POJO define, or the HttpParametersResolver for more information.");
			throw new IllegalArgumentException(error);
		}
		
//		@SuppressWarnings("unused")
//		final String base = new StringBuffer()
//				.append(httpRequest.getScheme()).append("://").append(httpRequest.getServerName()).append(':')
//				.append(httpRequest.getServerPort()).append(httpRequest.getContextPath()).append('/').toString();

		final String host = _properties.getProperty("host");
		final String port = _properties.getProperty("port");
		final boolean defaultPort = "80".equals(port);
		@SuppressWarnings("unused")
		final String base = new StringBuffer()
			.append(httpRequest.getScheme()).append("://").append(host).append(defaultPort ? "" : ':')
			.append(defaultPort ? "" : port).append(httpRequest.getContextPath()).append('/').toString();

		// 未实现 ... 
		
		PrintWriter writer = httpResponse.getWriter();
		writer.print(render(pSrc));
		writer.flush();
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	protected final AbstractPage render(final Source source) {
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
	protected final AbstractPage render(final Throwable throwable) {
		WelcomePage doc = new WelcomePage("Source News");
		doc.setOwnerClass(getClass().getSimpleName());
		doc.writeln("<div class=\"page-header\"><h1>Echo POST Form: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(throwable));
		doc.writeln("</div>");
		return doc;
	}
}
