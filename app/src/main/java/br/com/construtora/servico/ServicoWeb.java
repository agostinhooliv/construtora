package br.com.construtora.servico;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by agostinhooliv on 04/05/17.
 */

public class ServicoWeb {

    public String postLogin(String js) {

        String response = "";
        try {
            URL url = new URL("http://192.168.0.2:3000/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.print(js);
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            response = scanner.next();
            return response;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String postDespesas(String js) {

        String response = "";
        try {
            URL url = new URL("http://192.168.0.2:3000/insert_despesas_gerais");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.print(js);
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            response = scanner.next();
            return response;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
