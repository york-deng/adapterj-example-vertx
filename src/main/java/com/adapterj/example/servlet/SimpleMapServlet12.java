package com.adapterj.example.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.adapterj.algo.MD5;
import com.adapterj.annotation.Map;
import com.adapterj.registry.RegistryFactory;
import com.adapterj.web.Htmlable;
import com.adapterj.widget.SelectOptions;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleMapAdapter;
import com.adapterj.widget.SimpleSelectOptions;
import com.adapterj.widget.View;
import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.db.SourceQuery;
import com.adapterj.example.db.VersionQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.pojo.Version;
import com.adapterj.example.web.ErrorPage;

/**
 * 
 * @author York/GuangYu DENG
 */
public class SimpleMapServlet12 extends InitServlet {

	private static final long serialVersionUID = -1317969295329107435L;
	
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = SimpleMapServlet12.class.getName();

    private final String _templateFile = "/simplemap.html";
    private Document _document = null;
    private String _md5 = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			final String path = getContext().getRealPath(_templateFile);
			final File file = new File(path);

			_document = Jsoup.parse(file, "utf-8");
			_md5 = MD5.encode(_document.html(), "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest httpRequest, 
						 HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		/*
		 * Resolve HTTP request parameters
		 */
		final SimpleHttpParametersResolver<Source> resolver = new SimpleHttpParametersResolver<Source>(RegistryFactory.getRegistry());
		final Source parameter = resolver.getParameter(httpRequest);
		if (DEBUG) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String format = "(%s:%d) %s: parameter is %s";
            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter));
		}
		
		/*
		 * Define selected variables for select-options 
		 */
		Integer type  = null;
		Integer type1 = null, type2 = null, type3 = null, type4 = null, type5 = null;
		Integer type6 = null, type7 = null, type8 = null, type9 = null;

		/*
		 * Create adapter
		 */
		final SimpleMapAdapter adapter = new SimpleMapAdapter();
		//adapter.setFormAction("../save");
		
		if (parameter != null) {
			final Long id = parameter.getId();
			final String domain = parameter.getDomain();
			final String www = parameter.getWWW();
			
			if ((id != null && id > 0) || 
				(domain != null && !domain.isEmpty()) || 
				(www != null && !www.isEmpty())) {
				
				// Gets data by parameter
				final Source source = SourceQuery.getInstance().findByMatching(parameter);
				final Version version = VersionQuery.getInstance().findLatest();
				
				if (source != null) {
					// Gets selected value for select-options
					type = source.getType();
					type1 = source.getType1();
					type2 = source.getType2();
					type3 = source.getType3();
					type4 = source.getType4();
					type5 = source.getType5();
					type6 = source.getType6();
					type7 = source.getType7();
					type8 = source.getType8();
					type9 = source.getType9();
					
					// Put source and version into adapter
					
					// source
					
					/* id                   INTEGER                        not null,
					 * name                 VARCHAR(255)                   not null,
					 * abbr                 VARCHAR(255)                   not null,
					 * logo                 VARCHAR(255),
					 * domain               VARCHAR(255)                   not null,
					 * www                  VARCHAR(255)                   not null,
					 * description          VARCHAR(511),
					 */
					final String elementId1 = "source.id";
					final Long value1 = source.getId();
					adapter.setValue(elementId1, value1);
					
					final String elementId2 = "source.name";
					final String value2 = source.getName();
					adapter.setValue(elementId2, value2);
					
					final String elementId3 = "source.abbr";
					final String value3 = source.getAbbr();
					adapter.setValue(elementId3, value3);
					
					final String elementId4 = "source.logo";
					final String value4 = source.getLogo();
					adapter.setValue(elementId4, value4);
					
					final String elementId5 = "source.domain";
					final String value5 = source.getDomain();
					adapter.setValue(elementId5, value5);
					
					final String elementId6 = "source.www";
					final String value6 = source.getWWW();
					adapter.setValue(elementId6, value6);
					
					final String elementId7 = "source.description";
					final String value7 = source.getDescription();
					adapter.setValue(elementId7, value7);
					
					final String elementId8 = "source.type";
					final Integer value8 = source.getType();
					adapter.setValue(elementId8, value8);
					
					final String elementId9 = "source.country";
					final String value9 = source.getCountry();
					adapter.setValue(elementId9, value9);
					
					final String elementId10 = "source.language";
					final String value10 = source.getLanguage();
					adapter.setValue(elementId10, value10);
					
					final String elementId11 = "source.specific";
					final String value11 = source.getSpecific();
					adapter.setValue(elementId11, value11);
					
					final String elementId12 = "source.priority";
					final Integer value12 = source.getPriority();
					adapter.setValue(elementId12, value12);
					
					final String elementId13 = "source.latency";
					final Integer value13 = source.getLatency();
					adapter.setValue(elementId13, value13);
					
					final String elementId14 = "source.url1";
					final String value14 = source.getURL1();
					adapter.setValue(elementId14, value14);

					final String elementId15 = "source.type1";
					final Integer value15 = source.getType1();
					adapter.setValue(elementId15, value15);
					
					final String elementId16 = "source.encoding1";
					final String value16 = source.getEncoding1();
					adapter.setValue(elementId16, value16);
					
					final String elementId17 = "source.url2";
					final String value17 = source.getURL2();
					adapter.setValue(elementId17, value17);
					
					final String elementId18 = "source.type2";
					final Integer value18 = source.getType2();
					adapter.setValue(elementId18, value18);
					
					final String elementId19 = "source.encoding2";
					final String value19 = source.getEncoding2();
					adapter.setValue(elementId19, value19);
					
					
					
					final String elementId20 = "source.id";
					final Long value20 = source.getId();
					adapter.setValue(elementId20, value20);
					
					/* 
					 * version              VARCHAR(31)                    not null,
					 * insert_time          TIMESTAMP                      not null,
					 * update_time          TIMESTAMP                      not null,
					 * state                INTEGER                        not null,
					 */
					
					// version
					
					final String elementId21 = "version.year";
					final Integer value21 = version.getYear();
					adapter.setValue(elementId21, value21);
					
					final String elementId22 = "version.serial";
					final Integer value22 = version.getSerial();
					adapter.setValue(elementId22, value22);
				}
			}
		}
		
		/*
		 * Put select-options into adapter 
		 */
		final String selectId = ("type"); // See Source class getType method define for more information
		final SelectOptions options = adapter.getSelectOptions(selectId);
		if (options == null) {
			// Build type select-options and put it into the form adapter
			final SelectOptions options_ = new SimpleSelectOptions(selectId, Source.getTypes(), type);
			adapter.putSelectOptions(selectId, options_);
		}
		final String selectId1 = ("type1"); // See Source class getType1 method define for more information
		final SelectOptions options1 = adapter.getSelectOptions(selectId1);
		if (options1 == null) {
			// Build type1 select-options and put it into the form adapter
			final SelectOptions options1_ = new SimpleSelectOptions(selectId1, Source.getSubtypes(), type1);
			adapter.putSelectOptions(selectId1, options1_);
			
			// Build type2 select-options and put it into the form adapter
			final String selectId2 = ("type2");
			final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), type2);
			adapter.putSelectOptions(selectId2, options2);
			
			// Build type3 select-options and put it into the form adapter
			final String selectId3 = ("type3");
			final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), type3);
			adapter.putSelectOptions(selectId3, options3);
			
			// Build type4 select-options and put it into the form adapter
			final String selectId4 = ("type4");
			final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), type4);
			adapter.putSelectOptions(selectId4, options4);
			
			// Build type5 select-options and put it into the form adapter
			final String selectId5 = ("type5");
			final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), type5);
			adapter.putSelectOptions(selectId5, options5);
			
			// Build type6 select-options and put it into the form adapter
			final String selectId6 = ("type6");
			final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), type6);
			adapter.putSelectOptions(selectId6, options6);
			
			// Build type7 select-options and put it into the form adapter
			final String selectId7 = ("type7");
			final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), type7);
			adapter.putSelectOptions(selectId7, options7);
			
			// Build type8 select-options and put it into the form adapter
			final String selectId8 = ("type8");
			final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), type8);
			adapter.putSelectOptions(selectId8, options8);
			
			// Build type9 select-options and put it into the form adapter
			final String selectId9 = ("type9");
			final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), type9);
			adapter.putSelectOptions(selectId9, options9);
		}
		
		String html = null;
		try {
			/*
			 * Put adapter into view
			 */
			final Map anMap = adapter.getClass().getAnnotation(Map.class);
			final String element = anMap.classId();
			
			final View view = new SimpleHTMLView(_document, _md5);
			view.putAdapter(element, adapter);

			final Date begin = new Date();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: bindAll: begin is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), begin.toString()));
			}
			
			view.bindAll(View.SERVER_SIDE_BINDING);
			
			final Date end = new Date();
			final long cost = end.getTime() - begin.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: bindAll: end is %s, cost is %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end.toString(), cost));
			}
			
			html = view.toHTMLString();
		} catch (Throwable e) {
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
		final ErrorPage doc = new ErrorPage("ERROR");
		doc.writeln("<div class=\"page-header\"><h1>Error: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(throwable));
		doc.writeln("</div>");
		return (doc);
	}
}
