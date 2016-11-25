/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawling;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vishal
 */
public class NewsCrawler implements Runnable {

    public static int offset = -20;
    public static int count = 20;
    static Map<String, NewsModel> map = new HashMap<>();
    private ServletContextEvent sce = null;

    public NewsCrawler() {
    }

    public NewsCrawler(ServletContextEvent sce) {
        this.sce = sce;
    }

    @Override
    public void run() {

        System.out.println("THREAD START NEW :" + new Date());
        // TODO
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder("https://api.cognitive.microsoft.com/bing/v5.0/news/search?q=");

            builder.setParameter("q", "");
            builder.setParameter("count", String.valueOf(count));
            builder.setParameter("offset", String.valueOf(offset += count));
            builder.setParameter("mkt", "en-IN");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "4b94a2e731de4afa84e82d2989c595e3");
            //System.out.println("URL :" + uri.toString());

            // Request body
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                JSONObject jsonObj = new JSONObject(EntityUtils.toString(entity));
                System.out.println(jsonObj.toString());

                //System.out.println(jsonObj.getJSONArray("value"));
                JSONArray list = jsonObj.getJSONArray("value");
                for (int i = 0; i < list.length(); i++) {

                    JSONObject oj = (JSONObject) list.get(i);

                    // json to object
                    NewsModel newNews = new NewsModel();
                    newNews.setTitle((String) oj.get("name"));
                    newNews.setDescription((String) oj.get("description"));
                    // end of json to object

                    if (!map.containsKey(newNews.getTitle())) {
//                        System.out.println(newNews);
                        map.put(newNews.getTitle(), newNews);

                        // check if news list is alredy filled before
                        List<NewsModel> newsList = (List<NewsModel>) sce.getServletContext().getAttribute("newsList");
                        if (newsList == null) {
                            newsList = new ArrayList<>();
                        }
                        newsList.add(newNews);
                        // this is to add news list in servlet context
                        sce.getServletContext().setAttribute("newsList", newsList);
                    } else {
                        System.out.println("DUPLICATE : " + newNews);
                    }
                }
            }
            System.out.println("THREAD END NEW :" + new Date());
        } catch (Exception e) {
            System.out.println("Exception in NEWS Thread :" + e.getMessage());
        }
    }

}
