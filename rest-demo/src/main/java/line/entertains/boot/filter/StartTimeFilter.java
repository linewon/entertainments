package line.entertains.boot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import line.entertains.boot.filter.wrapper.MyResponseWrapper;
import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = "/", filterName = "startTimeFilter")
@Slf4j
public class StartTimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("initialize \"startTimeFilter successfully!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		MyResponseWrapper respWrapper = new MyResponseWrapper(resp);
		log.info("filter.begin : {}", req.getRequestURI());
		chain.doFilter(request, respWrapper);

		String respString = new String(respWrapper.getBytes());
		log.info("filter.end : {}", respString);
		response.getOutputStream().write(respWrapper.getBytes());
	}

	@Override
	public void destroy() {
		log.info("destroy \"startTimeFilter successfully!");
	}

}
