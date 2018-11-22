/*******************************************************************************
Copyright (c) 2018, Rove Monteux
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

  Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

  Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

  Neither the name of the copyright holder nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/

package cf.monteux.intelligence.network;

import cf.monteux.intelligence.properties.Agent;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

public class Https {

    public Https() {

    }

    public Hashtable<String, Integer> addresses = new Hashtable<String, Integer>();
    public ArrayList<String> popularUrls = new ArrayList<String>();

    public void topUrls(){
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(addresses.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }});
        for (int i=0; i<l.size(); i++) {
            System.out.println("Top url: "+l.get(i).getKey());
            popularUrls.add(l.get(i).getKey().toString());
        }
    }

    // Java 11
    public HttpResponse<String> getResponse(String address) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .build();
        HttpRequest mainRequest = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .setHeader("User-Agent", Agent.get())
                .build();
        HttpResponse<String> mainResponse = httpClient.send(mainRequest, BodyHandlers.ofString());
        return mainResponse;
    }

    // Java 11
	public void topResults(String term) throws IOException, InterruptedException {
		// Google, Facebook, Instagram, LinkedIn, Bing, DuckDuckGo
        term = term.replace(" ", "%20");

        // DuckDuckGo
        HttpResponse<String> duckDuckGo = getResponse("https://duckduckgo.com/html?q="+term);
        duckDuckGo.body().lines()
                .filter(line -> line.contains("result__a"))
                .map(line -> line.substring(line.indexOf("uddg=") + 5, line.indexOf("\">")))
                .forEach(result -> {
                    try {
                        String finalResult = URLDecoder.decode(result, "UTF-8");
                        if (!(addresses.contains(finalResult))) {
                            addresses.put(finalResult, 1);
                        }
                        else {

                        }
                    }
                    catch (Exception e) { }
                    });

        //Google


        // Bing

        // Facebook

        // Instagram

        // LinkedIn

    }

    public void displayUrls() {
        for (String url : popularUrls) {
            System.out.println("Address: "+url);
        }
    }

    // Java 6
	public static String retrieve(String address) throws IOException {
		String charset = "UTF-8";

	    URL url = new URL(address);
	    URLConnection hc = url.openConnection();
        hc.setRequestProperty("User-Agent", Agent.get());
	    Reader reader = new InputStreamReader(hc.getInputStream(), charset);
	    return IOUtils.toString(reader);
	}
}
