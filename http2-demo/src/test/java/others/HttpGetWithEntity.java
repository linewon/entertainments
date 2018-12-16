package others;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * HTTP-GET-REQUESTBODY
 * https://blog.csdn.net/liehuoruge91/article/details/78437076
 * @author line
 */
public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {

    private final static String METHOD_NAME = "GET";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpGetWithEntity() {
        super();
    }

    public HttpGetWithEntity(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpGetWithEntity(final String uri) {
        super();
        setURI(URI.create(uri));
    }
}