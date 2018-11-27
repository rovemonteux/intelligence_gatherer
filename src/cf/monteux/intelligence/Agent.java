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

package cf.monteux.intelligence;

import cf.monteux.intelligence.crawler.LinkExtractor;

import java.io.IOException;
import java.util.*;

public class Agent {

    public Hashtable<String, Integer> addresses = new Hashtable<String, Integer>();
    public ArrayList<String> popularUrls = new ArrayList<String>();

    public Agent() {

    }

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

    public void topResults(String term) throws IOException, InterruptedException {
        // Google, Facebook, Instagram, LinkedIn, Bing, DuckDuckGo
        term = term.replace(" ", "%20");

        LinkExtractor.DuckDuckGo(addresses, term);
        LinkExtractor.Bing(addresses, term);

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

}
