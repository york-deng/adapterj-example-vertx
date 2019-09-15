package com.adapterj.example.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.adapterj.web.Htmlable;
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.View;
import com.adapterj.ext.servlet.InitServlet;

import com.adapterj.example.db.SourceListQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.ErrorPage;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

/**
 * Show source list
 * 
 * @author York/GuangYu DENG
 */
public class SimpleListServlet2 extends InitServlet {

	private static final long serialVersionUID = -149068714872773711L;
	
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = SimpleListServlet2.class.getName();

    private Document _doc = null;
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			final String path = getContext().getRealPath("/simplelist.html");
			final File file = new File(path);
			
			_doc = Jsoup.parse(file, "utf-8");
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
		final Date begin1 = new Date();
		
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
				anchors.anchor(0, new Anchor(s.append("simple/disable"    ).append('?').append("source.id").append('=').append(id).toString()));
				s.delete(baseURI.length(), s.length());
				anchors.anchor(1, new Anchor(s.append("simple/form/script").append('?').append("source.id").append('=').append(id).toString()));
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
			final View view = new SimpleHTMLView("/simplelist.html"); // _doc
			view.putAdapter(id, adapter);
			
			// bindAll 
			final Date begin3 = new Date();
			final String contextPath = httpRequest.getContextPath();
			final String script = new StringBuilder().append(contextPath).append("/static/public/js/adapterj-all.js").toString();
			view.addExternalScript(script);
			view.bindAll(View.BROWSER_SIDE_BINDING);
			final Date end3 = new Date();
			final long cost3 = end3.getTime() - begin3.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: bindAll: cost3 is %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost3));
			}
			
			// toHTMLString
			final Date begin4 = new Date();
			html = view.toHTMLString();
			final Date end4 = new Date();
			final long cost4 = end4.getTime() - begin4.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: toHTMLString: cost4 is %d";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost4));
			}
		} catch (ServerSideException e) {
			html = render(e).toHTMLString();
		} catch (Throwable e) {
			html = render(e).toHTMLString();
		}
		
		httpResponse.setContentType("text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("utf-8");
		
		final BufferedWriter writer = new BufferedWriter(httpResponse.getWriter());
		writer.write(html);
		writer.flush();
		
		final Date end1 = new Date();
		final long cost1 = end1.getTime() - begin1.getTime();
		if (DEBUG) {
            StackTraceElement t = (new Throwable()).getStackTrace()[0];
            String format = "(%s:%d) %s: doGet: end1 is %s, cost1 is %d";
            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end1.toString(), cost1));
		}
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
