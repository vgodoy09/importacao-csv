package br.com.importarchive.control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.importarchive.util.ArchiveUtils;

@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadFileServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long tempoInicial = System.currentTimeMillis();
		
		DiskFileItemFactory dfif = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(dfif);

        if (!ServletFileUpload.isMultipartContent(request)) {
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        try {
			FileItemIterator itemIterator = sfu.getItemIterator(request);
			FileItemStream next = itemIterator.next();
			InputStream openStream = next.openStream();
			long tempoQueryInicial = System.currentTimeMillis();
			ArchiveUtils.readInputStreamToIssueNew(openStream);
			long tempoQueryFinal = System.currentTimeMillis();
	        System.err.println("Tempo contrução query query: " + (tempoQueryFinal - tempoQueryInicial) );
        } catch (Exception e) {
        	e.printStackTrace();
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
        
        long tempoFinal = System.currentTimeMillis();
        System.err.println("Geral: " + (tempoFinal - tempoInicial) );
        
        request.getRequestDispatcher("/conclude.jsp").forward(request, response);
	}

}
