package com.adapterj.test.commandline;

import com.adapterj.example.pojo.Stock;
import com.adapterj.widget.SimpleHTMLViewAccelerator;

public class ViewAcceleratorImpl1 extends SimpleHTMLViewAccelerator {

	private static final long serialVersionUID = -6039303936523241016L;

	@SuppressWarnings("rawtypes")
	public String toHTMLString() {
		final String id = "_list.item";
		final com.adapterj.widget.SimpleListAdapter adapter1 = getSimpleListAdapter(id);
		final java.util.List list11 = adapter1.getAllItems();
		final java.util.List list12 = adapter1.getAllAnchorGroup();
		final java.util.List list13 = adapter1.getAllTextGroup();
		
		final StringBuffer s = new StringBuffer();
		s.append("<!doctype html>\n");
		s.append("<html>\n");
		s.append(" <head> \n");
		s.append("  <title>Stock Prices</title> \n");
		s.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n");
		s.append("  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\"> \n");
		s.append("  <meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\"> \n");
		s.append("  <link rel=\"shortcut icon\" href=\"/images/favicon.ico\"> \n");
		s.append("  <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\" media=\"all\"> \n");
		s.append("  <script type=\"text/javascript\" src=\"/js/util.js\"></script> \n");
		s.append("  <style type=\"text/css\">\n");
		s.append("		/*<![CDATA[*/\n");
		s.append("		body {\n");
		s.append("			color: #333333;\n");
		s.append("			line-height: 150%;\n");
		s.append("		}\n");
		s.append("		thead {\n");
		s.append("			font-weight: bold;\n");
		s.append("			background-color: #CCCCCC;\n");
		s.append("		}\n");
		s.append("		.table tbody tr:nth-child(even) td, .table tbody tr:nth-child(even) th { background-color:#D4FFFF; }\n");
		s.append("		.table tbody tr:nth-child(odd)  td, .table tbody tr:nth-child(odd)  th { background-color:#D4BFFF; }\n");
		s.append("		.odd {\n");
		s.append("			background-color: #FFCCCC;\n");
		s.append("		}\n");
		s.append("		.even {\n");
		s.append("			background-color: #CCCCFF;\n");
		s.append("		}\n");
		s.append("		.minus {\n");
		s.append("			color: #FF0000;\n");
		s.append("		}\n");
		s.append("		/*]]>*/\n");
		s.append("	</style> \n");
		s.append(" </head> \n");
		s.append(" <body> \n");
		s.append("  <h1>Stock Prices</h1> \n");
		s.append("  <table class=\"table\"> \n");
		s.append("   <thead> \n");
		s.append("    <tr> \n");
		s.append("     <th>#</th> \n");
		s.append("     <th>symbol</th> \n");
		s.append("     <th>name</th> \n");
		s.append("     <th>price</th> \n");
		s.append("     <th>change</th> \n");
		s.append("     <th>ratio</th> \n");
		s.append("    </tr> \n");
		s.append("   </thead> \n");
		s.append("   <tbody> \n");
		s.append("    ");
		
		for (int j1 = 0; j1 < list11.size(); j1++) {
			s.append("    <tr id=\"_list.item\"> \n");
			s.append("     <td><span id=\"text[j][0].text\">");
			s.append((((com.adapterj.widget.TextGroup) list13.get(j1)).text(0)).getText());
			s.append("</span></td> \n");
			s.append("     <td><a id=\"anchor[j][0].url\" href=\"");
			s.append((((com.adapterj.widget.AnchorGroup) list12.get(j1)).anchor(0)).getURL());
			s.append("\"><span id=\"anchor[j][0].title\">");
			s.append((((com.adapterj.widget.AnchorGroup) list12.get(j1)).anchor(0)).getText());
			s.append("</span></a></td> \n");
			s.append("     <td><a id=\"stock[j].url\" href=\"");
			s.append(_formatter.format(((Stock) list11.get(j1)).getUrl()));
			s.append("\"><span id=\"stock[j].name\">");
			s.append(_formatter.format(((Stock) list11.get(j1)).getName()));
			s.append("</span></a></td> \n");
			s.append("     <td><strong><span id=\"stock[j].price\">");
			s.append(_formatter.format(((Stock) list11.get(j1)).getPrice(), "", ""));
			s.append("</span></strong></td> \n");
			s.append("     <td><span id=\"stock[j].change\">");
			s.append(_formatter.format(((Stock) list11.get(j1)).getChange(), "", ""));
			s.append("</span></td> \n");
			s.append("     <td><span id=\"stock[j].ratio\">");
			s.append(_formatter.format(((Stock) list11.get(j1)).getRatio(), "", ""));
			s.append("</span></td> \n");
			s.append("    </tr>\n");
			s.append("    ");
		}
		s.append(" \n");
		s.append("   </tbody> \n");
		s.append("  </table>   \n");
		s.append(" </body>\n");
		s.append("</html>\n");
		
		return s.toString();
	}

	public static void main(String args[]) {
		final ViewAcceleratorImpl1 impl = new ViewAcceleratorImpl1();
		final String html = impl.toHTMLString();

		System.out.println(html);
	}
}
