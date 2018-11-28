package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;



public class DownloadJson {
	
	private String urlString;
	private URL url;
	private String urlStringOutput;
	
	public DownloadJson(String urlString) {
		this.urlString = urlString;
		this.url= validateURL(this.urlString);
		this.urlStringOutput=readStringFromURL(this.url);
	}


	public String readStringFromURL(URL inputUrl) {
		String StringOut = null;
		try {
			URLConnection connection = inputUrl.openConnection();
			InputStreamReader inpStrR = new InputStreamReader(connection.getInputStream());
			BufferedReader dataBuff = new BufferedReader(inpStrR);
			StringOut = dataBuff.lines().collect(Collectors.joining("\n"));
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return StringOut;
		
	}

	
	public URL validateURL(String url) {
		URL reqURL = null;
		try {
			reqURL = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reqURL;
	}


	public String getUrlStringOutput() {
		return urlStringOutput;
	}


	public void setUrlStringOutput(String urlStringOutput) {
		this.urlStringOutput = urlStringOutput;
	}
	
}
