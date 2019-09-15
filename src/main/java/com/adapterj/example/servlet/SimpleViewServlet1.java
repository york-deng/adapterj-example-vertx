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
import com.adapterj.annotation.POJO;
import com.adapterj.registry.Registry;
import com.adapterj.registry.RegistryFactory;
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SelectOptions;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleSelectOptions;
import com.adapterj.widget.SimpleViewAdapter;
import com.adapterj.widget.View;

import com.adapterj.ext.servlet.InitServlet;
import com.adapterj.ext.servlet.SimpleHttpParametersResolver;
import com.adapterj.ext.web.AbstractPage;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.db.SourceQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;

/**
 * Show a source in read only view
 * 
 * @author York/GuangYu DENG
 */
public class SimpleViewServlet1 extends InitServlet {

	private static final long serialVersionUID = 2641353179969392683L;
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = SimpleListServlet1.class.getName();

    private final Registry _registry = RegistryFactory.getRegistry();
    private final String _templateFile = "/simpleview.html";
    
    private String _acceleratorClass = null;
    private Document _document = null;
    private String _md5 = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			final String path = getContext().getRealPath(_templateFile);
			final File file = new File(path);
			
			_acceleratorClass = _registry.getAcceleratorClassName(_templateFile);
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
		if (parameter == null) {
			final String error = (
					"IllegalArgumentException: \n http parameters resolver return a null. " + 
					"\n See your POJO define, or the HttpParametersResolver for more information.");
			throw new IllegalArgumentException(error);
		}
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
		final SimpleViewAdapter<Source> adapter = new SimpleViewAdapter<Source>();

		if (parameter != null) {
			final Long id = parameter.getId();
			final String domain = parameter.getDomain();
			final String www = parameter.getWWW();
			
			if ((id != null && id > 0) || 
				(domain != null && !domain.isEmpty()) || 
				(www != null && !www.isEmpty())) {
				
				// Gets data by parameter
				final Source source = SourceQuery.getInstance().findByMatching(parameter);
				
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
					
					final String baseURI = getBaseURI(httpRequest);
					final StringBuffer s = new StringBuffer(baseURI);

					final AnchorGroup anchors = new AnchorGroup(1);
					s.delete(baseURI.length(), s.length());
					anchors.anchor(0, new Anchor(s.append("simple/form/html").append('?').append("source.id").append('=').append(source.getId()).toString()));
					adapter.setAnchorGroup(anchors);
					
					// Put data into adapter
					adapter.setData(source);
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
			final POJO anPOJO = adapter.getClass().getAnnotation(POJO.class);
			final String element = anPOJO.classId();
			
			final View view = new SimpleHTMLView(_acceleratorClass, _document, _md5);
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
