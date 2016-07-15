package io.leopard.web.mvc.resource;

import io.leopard.web.servlet.ResourceHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ResourcesDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	private List<ResourceHandler> resourceHandlers;

	@Override
	protected WebApplicationContext initWebApplicationContext() {
		WebApplicationContext context = super.initWebApplicationContext();
		Map<String, ResourceHandler> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, ResourceHandler.class, true, false);
		if (!matchingBeans.isEmpty()) {
			this.resourceHandlers = new ArrayList<ResourceHandler>(matchingBeans.values());
			AnnotationAwareOrderComparator.sort(this.resourceHandlers);
		}
		return context;
	}

	protected void process(HttpServletRequest request, HttpServletResponse response, Resource resource) throws ServletException, IOException {
		LeopardResourceHttpRequestHandler handler = new LeopardResourceHttpRequestHandler(super.getServletContext(), resource);
		handler.handleRequest(request, response);
	}

	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uri = request.getRequestURI();
		if (resourceHandlers != null) {
			for (ResourceHandler handler : resourceHandlers) {
				Resource resource = handler.doHandler(uri, request, response);
				if (resource != null) {
					this.process(request, response, resource);
					return;
				}
			}
		}
		super.noHandlerFound(request, response);
	}
}