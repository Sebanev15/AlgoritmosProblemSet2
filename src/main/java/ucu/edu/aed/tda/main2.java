package ucu.edu.aed.tda;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class main2 {
    public static void main(String[] args){
        InputStream inputStream = main2.class.getClassLoader().getResourceAsStream("claves1.txt");
        assert inputStream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        ArbolBinario<Integer> arbol = new ArbolBinario<>();
        try{
            String linea = br.readLine();
            while (linea != null){
                arbol.insertar(Integer.parseInt(linea));
                linea = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
