/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        // System.out.println("json-muotoinen data:");
        // System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> fin = new ArrayList<>();
        
        System.out.println("Suomalaiset pelaajat:\n");
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                fin.add(player);
            }
        }
        
        Collections.sort(fin);
        
        fin.forEach((pelaaja) -> {
            System.out.println(pelaaja.toString());
        });
    }
  
}
