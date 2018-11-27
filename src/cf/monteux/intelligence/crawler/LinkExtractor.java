/*
 * Copyright (c) 2018, Rove Monteux
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *   Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package cf.monteux.intelligence.crawler;

import cf.monteux.intelligence.network.Https;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.http.HttpResponse;
import java.util.Hashtable;

public class LinkExtractor {

    /* Extracts a page worth of links of results from DuckDuckGo */
    public static void DuckDuckGo(Hashtable<String, Integer> addresses, String term) throws IOException, InterruptedException {
        HttpResponse<String> duckDuckGo = Https.getResponse("https://duckduckgo.com/html?q="+term);
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
    }

    /* Extracts a page worth of links of results from Bing */
    public static void Bing(Hashtable<String, Integer> addresses, String term) throws IOException, InterruptedException {
        HttpResponse<String> duckDuckGo = Https.getResponse("https://www.bing.com/search?q="+term);
        duckDuckGo.body().lines()
                .filter(line -> line.contains("b_title"))
                .map(line -> line.substring(line.indexOf("<h2>") + 13, line.indexOf("\">", line.indexOf("<h2>"))))
                .forEach(result -> {
                    try {
                        String finalResult = URLDecoder.decode(result, "UTF-8");
                        System.out.println("Putting Bing address "+finalResult);
                        if (!(addresses.contains(finalResult))) {
                            addresses.put(finalResult, 1);
                        }
                        else {

                        }
                    }
                    catch (Exception e) { }
                });
    }

}
