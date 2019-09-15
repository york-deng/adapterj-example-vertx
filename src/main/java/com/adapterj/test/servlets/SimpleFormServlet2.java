package com.adapterj.test.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adapterj.annotation.POJO;
import com.adapterj.widget.SelectOptions;
import com.adapterj.widget.SimpleFormAdapter;
import com.adapterj.widget.SimpleSelectOptions;
import com.adapterj.widget.View;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.adapterj.registry.RegistryFactory;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.example.db.SourceQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;
import com.adapterj.web.Htmlable;
import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;

/**
 * Show the form to create or update a source
 * 
 * @author York/GuangYu DENG
 */
public class SimpleFormServlet2 extends InitServlet {

	 static final long serialVersionUID = -1317969295329107435L;
	
	 static final boolean DEBUG = Debugger.DEBUG;
     static final String TAG = SimpleFormServlet2.class.getName();

	@Override
	protected void doGet(HttpServletRequest httpRequest, 
						 HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		final SimpleHttpParametersResolver<Source> resolver = new SimpleHttpParametersResolver<Source>(RegistryFactory.getRegistry());
		final Source parameter = resolver.getParameter(httpRequest);
		if (DEBUG) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String format = "(%s:%d) %s: parameter is %s";
            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter));
		}

//		final String host = getHost();
//		final String port = getPort();
//		final boolean eighty = "80".equals(port);
//
//		final String scheme = httpRequest.getScheme();
//		final String contextPath = httpRequest.getContextPath();
//		final String baseURI = new StringBuffer().append(scheme).append("://").append(host).append(eighty ? "" : ':').append(eighty ? "" : port).append(contextPath).append('/').toString();

		final SimpleFormAdapter<Source> adapter = new SimpleFormAdapter<Source>();
		if (parameter != null) {
			final Long id = parameter.getId();
			final String domain = parameter.getDomain();
			final String www = parameter.getWWW();
			
			if ((id != null && id > 0) || 
				(domain != null && !domain.isEmpty()) || 
				(www != null && !www.isEmpty())) {
				final Source source = SourceQuery.getInstance().findByMatching(parameter);
				if (source != null) {
					// Sets data into adapter
					adapter.setData(source);
					
					// Build type select option and put into the form adapter
					final Integer type = source.getType();
					final String selectId = ("type"); // See Source class getType method define for more information
					final SelectOptions options = new SimpleSelectOptions(selectId, Source.getTypes(), type);
					adapter.putSelectOptions(selectId, options);
					if (DEBUG) {
			            StackTraceElement t = (new Throwable()).getStackTrace()[0];
			            String format = "(%s:%d) %s: type is %s, selectId is %s, options is %s";
			            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), type, selectId, options.toHTMLString()));
					}
					
					// Build type1 select option and put into the form adapter
					final Integer type1 = source.getType1();
					final String selectId1 = ("type1"); // See Source class getType1 method define for more information
					final SelectOptions options1 = new SimpleSelectOptions(selectId1, Source.getSubtypes(), type1);
					adapter.putSelectOptions(selectId1, options1);
					if (DEBUG) {
			            StackTraceElement t = (new Throwable()).getStackTrace()[0];
			            String format = "(%s:%d) %s: type1 is %s, selectId1 is %s, options1 is %s";
			            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), type1, selectId1, options1.toHTMLString()));
					}
					
					// Build type2 select option and put into the form adapter
					final Integer type2 = source.getType2();
					final String selectId2 = ("type2"); // See Source class getType2 method define for more information
					final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), type2);
					adapter.putSelectOptions(selectId2, options2);
					
					// Build type3 select option and put into the form adapter
					final Integer type3 = source.getType3();
					final String selectId3 = ("type3");
					final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), type3);
					adapter.putSelectOptions(selectId3, options3);
					
					// Build type4 select option and put into the form adapter
					final Integer type4 = source.getType4();
					final String selectId4 = ("type4");
					final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), type4);
					adapter.putSelectOptions(selectId4, options4);
					
					// Build type5 select option and put into the form adapter
					final Integer type5 = source.getType5();
					final String selectId5 = ("type5");
					final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), type5);
					adapter.putSelectOptions(selectId5, options5);
					
					// Build type6 select option and put into the form adapter
					final Integer type6 = source.getType6();
					final String selectId6 = ("type6");
					final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), type6);
					adapter.putSelectOptions(selectId6, options6);
					
					// Build type7 select option and put into the form adapter
					final Integer type7 = source.getType7();
					final String selectId7 = ("type7");
					final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), type7);
					adapter.putSelectOptions(selectId7, options7);
					
					// Build type8 select option and put into the form adapter
					final Integer type8 = source.getType8();
					final String selectId8 = ("type8");
					final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), type8);
					adapter.putSelectOptions(selectId8, options8);
					
					// Build type9 select option and put into the form adapter
					final Integer type9 = source.getType9();
					final String selectId9 = ("type9");
					final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), type9);
					adapter.putSelectOptions(selectId9, options9);
				}
			}
		}
		
		final String selectId = ("type");
		final SelectOptions options = adapter.getSelectOptions(selectId);
		if (options == null) {
			final SelectOptions options_ = new SimpleSelectOptions(selectId, Source.getTypes(), null);
			adapter.putSelectOptions(selectId, options_);
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: type is %s, selectId is %s, options is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), null, selectId, options_.toHTMLString()));
			}
		}
		
		final String selectId1 = ("type1");
		final SelectOptions options1 = adapter.getSelectOptions(selectId1);
		if (options1 == null) {
			// Build type1 select option and put into the form adapter
			final SelectOptions options1_ = new SimpleSelectOptions(selectId1, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId1, options1_);
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: type1 is %s, selectId1 is %s, options1 is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), null, selectId1, options1_.toHTMLString()));
			}
			
			// Build type2 select option and put into the form adapter
			final String selectId2 = ("type2"); // See Source class getType2 method define for more information
			final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId2, options2);
			
			// Build type3 select option and put into the form adapter
			final String selectId3 = ("type3");
			final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId3, options3);
			
			// Build type4 select option and put into the form adapter
			final String selectId4 = ("type4");
			final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId4, options4);
			
			// Build type5 select option and put into the form adapter
			final String selectId5 = ("type5");
			final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId5, options5);
			
			// Build type6 select option and put into the form adapter
			final String selectId6 = ("type6");
			final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId6, options6);
			
			// Build type7 select option and put into the form adapter
			final String selectId7 = ("type7");
			final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId7, options7);
			
			// Build type8 select option and put into the form adapter
			final String selectId8 = ("type8");
			final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId8, options8);
			
			// Build type9 select option and put into the form adapter
			final String selectId9 = ("type9");
			final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId9, options9);
		}
		
		String html = null;
		try {
			final String path = getContext().getRealPath("/simpleform.html");
			final File file = new File(path);
			
			final View view = new SimpleHTMLView();
			view.loadHTMLResource(file, "utf-8");
			
			/*
			 * Put adapter into view
			 */
			final POJO anPOJO = adapter.getClass().getAnnotation(POJO.class);
			final String element = anPOJO.classId();
			view.putAdapter(element, adapter);

			final Date begin = new Date();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: bindAll: begin is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), begin.toString()));
			}

			final String contextPath = httpRequest.getContextPath();
			final String script = new StringBuilder().append(contextPath).append("/static/public/js/adapterj-all.js").toString();
			view.addExternalScript(script);
			view.bindAll(View.BROWSER_SIDE_BINDING);
			
			final Date end = new Date();
			final long cost = end.getTime() - begin.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: bindAll: end is %s, cost is %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end.toString(), cost));
			}
			
			html = view.toHTMLString();
		} catch (MalformedURLException e) {
			html = render(e).toHTMLString();
		} catch (IOException e) {
			html = render(e).toHTMLString();
		}
		
		httpResponse.setContentType("text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("utf-8");
		
		final PrintWriter writer = httpResponse.getWriter();
		writer.print(html);
		writer.flush();
	}
	
	/**
	 * 
	 * @param throwable
	 * @return
	 */
	protected final Htmlable render(final Throwable throwable) {
		final WelcomePage doc = new WelcomePage("AdapterJ Example");
		doc.setOwnerClass(getClass().getSimpleName());
		doc.writeln("<div class=\"page-header\"><h1>Echo POST Form: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(throwable));
		doc.writeln("</div>");
		return doc;
	}
}
