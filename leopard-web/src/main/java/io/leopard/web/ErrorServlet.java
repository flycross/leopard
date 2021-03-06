package io.leopard.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.leopard.mvc.trynb.ErrorUtil;

/**
 * 错误页面
 * 
 * @author 阿海
 */
@WebServlet(name = "errorServlet", urlPatterns = "/error.do")
public class ErrorServlet extends HttpServlet {
	private final Log logger = LogFactory.getLog(this.getClass());

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ahai 错误页面未实现.

		Object obj = request.getAttribute("javax.servlet.error.exception");
		String message;
		if (obj == null) {
			message = "未知错误";
		}
		else {
			Throwable exception = (Throwable) obj;
			logger.error(exception.getMessage(), exception);
			message = ErrorUtil.parseMessage(exception);
		}

		response.setContentType("text/html; charset=utf-8");
		Writer out = response.getWriter();
		out.write("error msg:" + message);
	}

}
