package com.adapterj.test.servlets;

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
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SelectOptions;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.SimpleSelectOptions;
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
 * 
 * @author York/GuangYu DENG
 */
public class SimpleListServlet13 extends InitServlet {

	 static final long serialVersionUID = -149068714872773711L;
	
	 static final boolean DEBUG = Debugger.DEBUG;
     static final String TAG = SimpleListServlet13.class.getName();

     final Registry _registry = RegistryFactory.getRegistry();
     final String _templateFile = "/simplelist.html";
    
     String _acceleratorClass = null;
     Document _document = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

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

		/*
		 * Build a list adapter 
		 */
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
		 * Put select-options into adapter 
		 */
		final String selectId = ("type"); // See Source class getType method define for more information
		final SelectOptions options = adapter.getSelectOptions(selectId);
		if (options == null) {
			// Build type select-options and put it into the form adapter
			final SelectOptions options_ = new SimpleSelectOptions(selectId, Source.getTypes(), null);
			adapter.putSelectOptions(selectId, options_);
		}
		final String selectId1 = ("type1"); // See Source class getType1 method define for more information
		final SelectOptions options1 = adapter.getSelectOptions(selectId1);
		if (options1 == null) {
			// Build type1 select-options and put it into the form adapter
			final SelectOptions options1_ = new SimpleSelectOptions(selectId1, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId1, options1_);
			
			// Build type2 select-options and put it into the form adapter
			final String selectId2 = ("type2");
			final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId2, options2);
			
			// Build type3 select-options and put it into the form adapter
			final String selectId3 = ("type3");
			final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId3, options3);
			
			// Build type4 select-options and put it into the form adapter
			final String selectId4 = ("type4");
			final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId4, options4);
			
			// Build type5 select-options and put it into the form adapter
			final String selectId5 = ("type5");
			final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId5, options5);
			
			// Build type6 select-options and put it into the form adapter
			final String selectId6 = ("type6");
			final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId6, options6);
			
			// Build type7 select-options and put it into the form adapter
			final String selectId7 = ("type7");
			final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId7, options7);
			
			// Build type8 select-options and put it into the form adapter
			final String selectId8 = ("type8");
			final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId8, options8);
			
			// Build type9 select-options and put it into the form adapter
			final String selectId9 = ("type9");
			final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), null);
			adapter.putSelectOptions(selectId9, options9);
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
			final View view = new SimpleHTMLView(_acceleratorClass, _document, "md5");
			view.putAdapter(id, adapter);

			final String contextPath = httpRequest.getContextPath();
			final String script = new StringBuilder().append(contextPath).append("/static/public/js/adapterj-all.js").toString();
			view.addExternalScript(script);
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
