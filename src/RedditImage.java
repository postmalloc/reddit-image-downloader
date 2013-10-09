/*
 * Reddit Image Grabber
 * Author: Srimukh Sai
 * 
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RedditImage{
	public static void main(String[] args){
		
		//jsoup document object
		Document page; 
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("This program will download all the images from the sub-reddit /r/funny");
		System.out.println("Enter the number of pages you want to download, each page has around 25 images");
		int numPages = input.nextInt();
		System.out.println("Enter the destination folder, e.g /home/srimukh/reddit");
		String dest = input.next();
		System.out.println("Downloading now...");
		
		try{
			
			int i = 0;
			
			//fetching the document from the URL in html
			page = (Jsoup.connect("http://www.reddit.com/r/funny/").timeout(10000)).get(); 
			while(i < numPages){
				
				//selecting all the elements whose urls end with "jpg" or "png"
				Elements images = page.select("a[href$=jpg], a[href$=png]");
				for (Element link : images) {
					
					//extracting the url from those elements
					URL addr = new URL(link.attr("href"));
<<<<<<< HEAD
					ReadableByteChannel rbc = Channels.newChannel(addr.openStream());
					if(link.attr("href").endsWith("jpg")){
						FileOutputStream fos = new FileOutputStream(dest + "/" + link.text() + ".jpg");
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
=======
					InputStream in = addr.openStream();
					OutputStream op;
					
					//raw outputstream of each element
					String fileName = link.text();
					//to replace invalid characters in filename. 
					fileName = 	fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
					System.out.println("Downloading " + fileName);
						
					if(link.attr("href").endsWith("jpg")){
						op = new FileOutputStream(dest + "/" + fileName + ".jpg");
>>>>>>> 04858917353f3254308d7040922278bbf7f9f588
					}
					else{
<<<<<<< HEAD
						FileOutputStream fos = new FileOutputStream(dest + "/" + link.text() + ".png");
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
					}				
=======
						op = new FileOutputStream(dest + "/" + fileName + ".png");
					}
					
					//byte array for storing the raw data
					byte[] b = new byte[20480];
					int length;
					
					while ((length = in.read(b)) != -1) {
						//writing it to a file
						op.write(b, 0, length)	;
					}
					
					in.close();
					op.close();
>>>>>>> 04858917353f3254308d7040922278bbf7f9f588
				}
				//moving to next page
				Element next = page.select("a[rel = nofollow next]").first();
				String nextLink = next.attr("href").toString();
				page = (Jsoup.connect(nextLink).timeout(10000)).get();
				i = i + 1;
				
			}
			
			System.out.println("Download complete.");
		}
		
		catch(Exception e){
			e.printStackTrace();
			
			
			
		}
	}
}
