package jwei.league.main.web;

public class GetMethods {

	/**
	 * MAKE AN HTTP GET REQUEST
	 * 
	 * @param mUrl
	 *            the url of the request you're making
	 * @param httpClient
	 *            a configured http client
	 * @return
	 */
	public static String doGetWithResponse(String mUrl, DefaultHttpClient httpClient) {
		String ret = null;
		HttpResponse response = null;

		// INITIATE THE GET METHOD WITH THE DESIRED URL
		HttpGet getMethod = new HttpGet(mUrl);
		try {
			// USE YOUR HTTP CLIENT TO EXECUTE THE METHOD
			response = httpClient.execute(getMethod);
			System.out.println("STATUS CODE: " + String.valueOf(response.getStatusLine().getStatusCode()));
			if (null != response) {
				// CONVERT HTTP RESPONSE TO STRING
				ret = getResponseBody(response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
		}
		return ret;
	}

	public static String getResponseBody(HttpResponse response) {
        String response_text = null;
        HttpEntity entity = null;

        try {
            // GET THE MESSAGE BODY OF THE RESPONSE
            entity = response.getEntity();
            if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

            // IF NOT NULL GET CONTENT AS STREAM
            InputStream instream = entity.getContent();
            if (instream == null) { return ""; }

            // CHECK FOR LENGTH
            if (entity.getContentLength() > Integer.MAX_VALUE) { throw new IllegalArgumentException(
                    "HTTP entity too large to be buffered in memory"); }

            // GET THE CHARACTER SET OF THE RESPONSE
            String charset = null;
            if (entity.getContentType() != null) {
                HeaderElement values[] = entity.getContentType().getElements();
                if (values.length > 0) {
                    NameValuePair param = values[0].getParameterByName("charset");
                    if (param != null) {
                        charset = param.getValue();
                    }
                }
            }
            if (charset == null) {
                charset = HTTP.DEFAULT_CONTENT_CHARSET;
            }

            // ONCE CHARACTER SET IS OBTAINED - START READING FROM STREAM
            Reader reader = new InputStreamReader(instream, charset);
            StringBuilder buffer = new StringBuilder();
            try {
                // USE A BUFFER TO READ FROM STREAM
                char[] tmp = new char[2048];
                int l;
                while ((l = reader.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
            } finally {
                reader.close();
            }

            // CONVERT BUFFER TO STRING
            response_text = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response_text;
    }

}
