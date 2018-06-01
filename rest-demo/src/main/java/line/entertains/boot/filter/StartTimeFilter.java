package line.entertains.boot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

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

		log.info("doFilter 00000000000");
		chain.doFilter(request, response);
		log.info("doFilter 11111111111");
	}

	@Override
	public void destroy() {
		log.info("destroy \"startTimeFilter successfully!");
	}

}
