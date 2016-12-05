package com.wut.datasources.cloudflare;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;
 
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }
}
