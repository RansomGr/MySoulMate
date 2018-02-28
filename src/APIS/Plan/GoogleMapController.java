/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIS.Plan;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.util.MarkerImageFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.BorderPane;
import netscape.javascript.JSObject;

/**
 *
 * @author chandal
 */
public class GoogleMapController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
    
    Marker myMarker = null;
  
    protected GoogleMapView mapComponent;
    private GoogleMap map;
  
    @FXML
    private BorderPane map_container;
    int count = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        mapComponent = new GoogleMapView(Locale.ENGLISH.getLanguage(), "AIzaSyDmK68Bq5oD_6YfMsK-Nh848i5KLRpO61Y&libraries=places&language=en");
        mapComponent.addMapInializedListener((MapComponentInitializedListener) this);
        map_container.setCenter(mapComponent);
    }

    @Override
    public void mapInitialized() {
        LatLong center = new LatLong(36.5, 10);

        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(10)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.ROADMAP)
                .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
        
        map = mapComponent.createMap(options, false);

        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {

            count++;
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            LatLong markerLatLong = new LatLong(ll.getLatitude(), ll.getLongitude());
            // LatLong markerLatLong1 = new LatLong(ll.getLatitude(), ll.getLongitude());
            String forrrAdd = "";
            forrrAdd = adressss(ll.getLatitude(), ll.getLongitude());
            
            //System.out.println(forrrAdd);
            String str = between(forrrAdd, "\"formatted_address\" : \"", "\",         \"geometry\"");// getting address values from here  call your adress entity to uupdate
            System.out.println("formatted adres = -->" + str);// str holds the values of the adress
            markerOptions.position(markerLatLong)
                    .title("My new Marker")
                    .icon(MarkerImageFactory.createMarkerImage("mymarker.png", "png"))
                    .animation(Animation.DROP)
                    .visible(true);
            if (myMarker == null) {
                myMarker = new Marker(markerOptions);
                map.addMarker(myMarker);
            }else{
                myMarker.setOptions(markerOptions);
            }

        });
    }

    static String between(String value, String a, String b) {
        // Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.indexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    //reverse geocoding.........................................................................................
    public String adressss(double lat, double ln) {
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + ln + "&sensor=true");
            // making connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            // Reading data's from url
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String outt = "";
            System.out.println("Output from Server .... \n");
            int i=0;
            int start,end;
            while ((output = br.readLine()) != null) {
                if(output.contains("long_name") && i<6){
                    i++;
                    start =output.indexOf("\"", 29);
                    end=output.indexOf("\"", 32);
                    if(i==1)
                    System.out.println("Road : "+output.substring(start+1,end));
                    if(i==3)
                    System.out.println("City : "+output.substring(start+1,end));
                    if(i==4)
                    System.out.println("Country : "+output.substring(start+1,end));
                }
                
                outt += output;
            }
            
            

            return outt;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
