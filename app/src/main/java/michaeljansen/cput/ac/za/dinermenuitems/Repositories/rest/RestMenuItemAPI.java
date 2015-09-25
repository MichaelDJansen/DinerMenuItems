package michaeljansen.cput.ac.za.dinermenuitems.Repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem;
import michaeljansen.cput.ac.za.dinermenuitems.Repositories.RestAPIMenuItem;

/**
 * Created by Michael on 25/09/2015.
 */
public class RestMenuItemAPI implements RestAPIMenuItem{
    final String BASE_URL= "https://diner-mdjansen.rhcloud.com/menuItems";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    public String addMenuItem(MenuItem entity){
        final String url = BASE_URL+"/create/";
        HttpEntity<MenuItem> requestEntity = new HttpEntity<MenuItem>(entity,requestHeaders);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String result = responseEntity.getBody();
            return result;
        }catch (Exception e)
        {
            return "Exists";
        }
    }

    public String updateMenuItem(MenuItem menuItem)
    {
        final String url = BASE_URL+"/update/"+menuItem.getMenuItemId();
        HttpEntity<MenuItem> requestEntity = new HttpEntity<MenuItem>(menuItem, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    public MenuItem getMenuItem(int id)
    {
        final String url = BASE_URL+"/"+id;
        HttpEntity<MenuItem> requestEntity = new HttpEntity<MenuItem>(requestHeaders);
        ResponseEntity<MenuItem> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, MenuItem.class);
        MenuItem menuItem = responseEntity.getBody();
        return menuItem;
    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        final String url = BASE_URL+"/all/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<MenuItem[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, MenuItem[].class);
        MenuItem[] results = responseEntity.getBody();

        for (MenuItem user : results) {
            menuItems.add(user);
        }
        return menuItems;
    }

    public void deleteMenuItem(int id){
        final String url = BASE_URL+"/delete/"+id ;
        HttpEntity<MenuItem> requestEntity = new HttpEntity<MenuItem>(requestHeaders);
    }

}
