package com.adapterj.test.servlets;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.adapterj.annotation.List;
import com.adapterj.serverside.ServerSideException;
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.View;
import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;
import com.adapterj.registry.Registry;
import com.adapterj.registry.RegistryFactory;
import com.adapterj.example.db.SourceListQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.ErrorPage;
import com.adapterj.web.Htmlable;
import com.adapterj.ext.servlet.InitServlet;

/**
 * Show source list
 * 
 * @author York/GuangYu DENG
 */
@SuppressWarnings("unused")
public class SimpleListServlet11 extends InitServlet {

	 static final long serialVersionUID = -149068714872773711L;
	
	 static final boolean DEBUG = Debugger.DEBUG;
     static final String TAG = SimpleListServlet11.class.getName();

     final Registry _registry = RegistryFactory.getRegistry();
     final String _templateFile = "/simplelist.html";
    
     String _acceleratorClass = null;
     Document _document = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		final String classPath = getClass().getClassLoader().getResource("").getPath();
		//AdapterJClassLoader.getInstance().loadClasses(classPath);
		
		try {
			_acceleratorClass = _registry.getAcceleratorClassName(_templateFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		try {
			final String path = getContext().getRealPath(_templateFile);
			final File file = new File(path);

			_document = Jsoup.parse(file, "utf-8");
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
		final Date begin1 = new Date(); // DEBUG

		final java.util.List<Source> list = SourceListQuery.getInstance().findAllItems();

		final SimpleListAdapter<Source> adapter = new SimpleListAdapter<Source>();
		if (list != null && !list.isEmpty()) {
			adapter.addAllItems(list);
			
			final String baseURI = getBaseURI(httpRequest);
			final StringBuffer s = new StringBuffer(baseURI);
			final Iterator<Source> iter = list.iterator();
			while(iter.hasNext()) {
				final Source source = iter.next();
				final String id = Long.toString(source.getId());
				final AnchorGroup anchors = new AnchorGroup(2);
				s.delete(baseURI.length(), s.length());
				anchors.anchor(0, new Anchor(s.append("simple/disable"  ).append('?').append("source.id").append('=').append(id).toString()));
				s.delete(baseURI.length(), s.length());
				anchors.anchor(1, new Anchor(s.append("simple/form/html").append('?').append("source.id").append('=').append(id).toString()));
				adapter.addAnchorGroup(anchors);
			}
		}
		
		/*
		 * Put adapter into view
		 */
		final List anList = adapter.getClass().getAnnotation(List.class);
		final String classId = anList.classId();
		final String entryId = anList.entryId();
		
		// Such as: "", "[0]", "[1]", ... 
		final String array = "";

		// Such as: _list.item, _list[0].item, _list[1].item, ...
		final String id = entryId != null && !entryId.isEmpty() ? new StringBuffer(classId).append(array != null & !array.isEmpty() ? array : "").append('.').append(entryId).toString() : classId; 
		
		String html = null;
		try {
			/*
			 * final View view = new SimpleHTMLViewV1(_document);
			 * final View view = new SimpleHTMLViewV2(_acceleratorClass, _document);
			 * final View view = new SimpleHTMLViewV3(_acceleratorClass, _document);
			 * final View view = new SimpleHTMLViewV4(_acceleratorClass, _document);
			 * final View view = new SimpleHTMLView(_acceleratorClass, _document);
			 */
//			final Date begin2 = new Date(); // DEBUG
			final View view = new SimpleHTMLView(_acceleratorClass, _document, "md5");
			view.putAdapter(id, adapter);
//			final Date end2 = new Date(); // DEBUG
//			final long cost2 = end2.getTime() - begin2.getTime(); // DEBUG
//			if (DEBUG) { // DEBUG
//	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
//	            String format = "(%s:%d) %s: init/putAdapter: cost2 is %d";
//	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost2));
//			} // DEBUG
			
			// bindAll 
//			final Date begin3 = new Date(); // DEBUG
			final String contextPath = httpRequest.getContextPath();
			final String script = new StringBuilder().append(contextPath).append("/static/public/js/adapterj-all.js").toString();
			view.addExternalScript(script);
			view.bindAll(View.SERVER_SIDE_BINDING);
//			final Date end3 = new Date(); // DEBUG
//			final long cost3 = end3.getTime() - begin3.getTime(); // DEBUG
//			if (DEBUG) { // DEBUG
//	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
//	            String format = "(%s:%d) %s: bindAll: cost3 is %d";
//	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost3));
//			} // DEBUG
			
			// toHTMLString
//			final Date begin4 = new Date(); // DEBUG
			html = view.toHTMLString();
//			final Date end4 = new Date(); // DEBUG
//			final long cost4 = end4.getTime() - begin4.getTime(); // DEBUG
//			if (DEBUG) { // DEBUG
//	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
//	            String format = "(%s:%d) %s: toHTMLString: cost4 is %d";
//	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost4));
//			} // DEBUG
		} catch (ServerSideException e) {
			html = render(e).toHTMLString();
		} catch (Throwable e) {
			html = render(e).toHTMLString();
		}
		
		httpResponse.setContentType("text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("utf-8");
		
//		final Date begin5 = new Date(); // DEBUG
		final BufferedWriter writer = new BufferedWriter(httpResponse.getWriter());
		writer.write(html);
//		final Date end5 = new Date(); // DEBUG
//		final long cost5 = end5.getTime() - begin5.getTime(); // DEBUG
//		if (DEBUG) {  // DEBUG
//            StackTraceElement t = (new Throwable()).getStackTrace()[0];
//            String format = "(%s:%d) %s: write/flush: cost5 is %d";
//            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost5));
//		} // DEBUG

		final Date end1 = new Date(); // DEBUG
		final long cost1 = end1.getTime() - begin1.getTime(); // DEBUG
		if (DEBUG) { // DEBUG
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String format = "(%s:%d) %s: doGet: cost1 is %d";
            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost1));
		} // DEBUG
		
		writer.flush();
	}

	/**
	 * 
	 * @param throwable
	 * @return
	 */
	protected final Htmlable render(final Throwable thrown) {
		final ErrorPage doc = new ErrorPage("ERROR");
		doc.writeln("<div class=\"page-header\"><h1>Error: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(thrown));
		doc.writeln("</div>");
		return (doc);
	}
}
