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

import com.adapterj.algo.MD5;
import com.adapterj.annotation.List;
import com.adapterj.serverside.ServerSideException;
import com.adapterj.web.Htmlable;
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.Text;
import com.adapterj.widget.TextGroup;
import com.adapterj.widget.View;
import com.adapterj.ext.servlet.InitServlet;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.pojo.Stock;
import com.adapterj.example.web.ErrorPage;

/**
 * 
 * @author York/GuangYu DENG
 */
public class SimpleBenchmarkServlet1 extends InitServlet {

	private static final long serialVersionUID = -149068714872773711L;
	private static final boolean DEBUG = Debugger.DEBUG;
    private static final String TAG = SimpleBenchmarkServlet1.class.getName();

    private final String _templateFile = "/simplebenchmark.html";
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
		final Date begin1 = new Date(); // DEBUG

		final java.util.List<Stock> list = Stock.dummyItems();

		/*
		 * Build a list adapter 
		 */
		final SimpleListAdapter<Stock> adapter = new SimpleListAdapter<Stock>();
		if (list != null && !list.isEmpty()) {
			adapter.addAllItems(list);
			
			final String baseURI = getBaseURI(httpRequest);
			final StringBuffer s = new StringBuffer(baseURI);
			
			final Iterator<Stock> iter = list.iterator();
			int index = 1;
			while(iter.hasNext()) {
				final Stock stock = iter.next();
				final String symbol = stock.getSymbol();
				
				final TextGroup texts = new TextGroup(1);
				texts.text(0, new Text(Integer.toString(index)));
				adapter.addTextGroup(texts);
				
				final AnchorGroup anchors = new AnchorGroup(1);
				s.delete(baseURI.length(), s.length());
				anchors.anchor(0, new Anchor(s.append("stocks/").append(symbol).toString(), symbol));
				adapter.addAnchorGroup(anchors);
				
				index ++;
			}
		}
		
		/*
		 * Put adapter into view
		 */
		final List anList = adapter.getClass().getAnnotation(List.class);
		final String classId = anList.classId();
		final String entryId = anList.entryId();
		
		// Such as: "", "[0]", "[1]", ... for multiple lists 
		final String array = "";

		// Such as: _list.item, _list[0].item, _list[1].item, ...
		final String id = entryId != null && !entryId.isEmpty() ? new StringBuffer(classId).append(array != null & !array.isEmpty() ? array : "").append('.').append(entryId).toString() : classId; 
		
		String html = null;
		try {
			final View view = new SimpleHTMLView(_document, _md5);
			view.putAdapter(id, adapter);
			view.bindAll(View.SERVER_SIDE_BINDING);

			html = view.toHTMLString();
		} catch (ServerSideException e) {
			html = render(e).toHTMLString();
		} catch (Throwable e) {
			html = render(e).toHTMLString();
		}
		
		/*
		 * Write a HTTP response 
		 */
		httpResponse.setContentType("text/html;charset=UTF-8");
		httpResponse.setCharacterEncoding("utf-8");

		final BufferedWriter writer = new BufferedWriter(httpResponse.getWriter());
		writer.write(html);

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
