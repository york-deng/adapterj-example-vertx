package com.adapterj.example.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adapterj.annotation.List;
import com.adapterj.web.Htmlable;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.View;


import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.db.SourceListQuery;
import com.adapterj.example.pojo.Source;
import com.adapterj.example.web.WelcomePage;

/**
 * Show source list
 * 
 * @author York/GuangYu DENG
 */
public class MultiListViewServlet extends HttpServlet {

	private static final long serialVersionUID = -149068714872773711L;
	
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = MultiListViewServlet.class.getName();

    private ServletContext _context;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		_context = config.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest httpRequest, 
						 HttpServletResponse httpResponse) throws ServletException, IOException 
	{
		String html = null;
		
		final SourceListQuery query = SourceListQuery.getInstance();
		final java.util.List<Source> list = query.findAllItems();

		final SimpleListAdapter<Source> adapter = new SimpleListAdapter<Source>();
		if (list != null && !list.isEmpty()) {
			final Iterator<Source> iter = list.iterator();
			while(iter.hasNext()) {
				final Source source = iter.next();
//				final String sourceId = Long.toString(source.getId());
//				source.setViewLink0("../source/delete?source.id=" + sourceId);
//				source.setViewLink1("../source/form?source.id=" + sourceId);
//				source.setViewLink2("../xpaths/form?source.id=" + sourceId);
				adapter.addItem(source);
			}
		}
		
		//final ID anID = adapter.getClass().getAnnotation(ID.class);
		final List anList = adapter.getClass().getAnnotation(List.class);
		final String classId = anList.classId();
		final String entryId = anList.entryId();
		
		final int adapters = 2;

		final View view = new SimpleHTMLView();
		try {
			final String path = _context.getRealPath("/multilist.html");
			final File file = new File(path);
			view.loadHTMLResource(file, "utf-8");
			
			for (int i = 0; i < adapters; i ++) {
				// Such as: "", "[0]", "[1]", ... 
				final String array = new StringBuffer().append('[').append(i).append(']').toString();
				// Such as: _list.item, _list[0].item, _list[1].item, ...
				final String id = entryId != null && !entryId.isEmpty() ? new StringBuffer(classId)
						.append(array != null & !array.isEmpty() ? array : "").append('.').append(entryId).toString() : classId; 
				if (DEBUG) {
		            StackTraceElement t = (new Throwable()).getStackTrace()[0];
		            String format = "(%s:%d) %s: list adapter: id is \"%s\"";
		            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), id));
				}
				
				// Put adapter into view
				view.putAdapter(id, adapter);
			}

			final Date begin = new Date();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: toHTMLString: begin is %s";
	            Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), begin.toString()));
			}
			// Bind all adapters and view
			view.bindAll(View.SERVER_SIDE_BINDING);
			final Date end = new Date();
			final long cost = end.getTime() - begin.getTime();
			if (DEBUG) {
	            StackTraceElement t = (new Throwable()).getStackTrace()[0];
	            String format = "(%s:%d) %s: toHTMLString: end is %s, %d";
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
		WelcomePage doc = new WelcomePage("Source News");
		doc.setOwnerClass(getClass().getSimpleName());
		doc.writeln("<div class=\"page-header\"><h1>Echo POST Form: </h1></div>");
		doc.writeln("<div class=\"content\">");
		doc.write(Log.getStackTraceHTMLString(throwable));
		doc.writeln("</div>");
		return doc;
	}
}
