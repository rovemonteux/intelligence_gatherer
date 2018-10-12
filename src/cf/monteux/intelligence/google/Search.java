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

package cf.monteux.intelligence.google;

import java.io.IOException;

import com.google.gson.Gson;

import cf.monteux.intelligence.network.Https;
import cf.monteux.intelligence.properties.Configuration;

public class Search {

	public static void results(String search, Configuration configuration) throws IOException {
		String google = "https://www.googleapis.com/customsearch/v1?q="+search.replace(" ", "+")+"&cx="+configuration.getCustomSearch()+"&key="+configuration.getCustomSearchApiKey()+"";
	    String result = Https.retrieve(google);
	    GoogleResults results = new Gson().fromJson(result, GoogleResults.class);
	    System.out.println(result);
	    System.out.println(results.getResponseData().getResults().get(0).getTitle());
	    System.out.println(results.getResponseData().getResults().get(0).getUrl());
	}
	
	public static void results(String search) throws IOException {
		results(search, new Configuration());
	}
	
}