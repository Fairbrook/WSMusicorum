/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Kevin Alan Martinez Virgen 14300260 8B1
 */
public class ArchivoController {
    public static boolean create(String path, byte[] cont){
        BufferedOutputStream writer = null;
        FileOutputStream archivo = null;
        try {
            File file = new File(path);
            archivo = new FileOutputStream(file);
            writer = new BufferedOutputStream(archivo);
            archivo.write(cont);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;   
        }finally{
            try {
                writer.close();
                archivo.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
    public static boolean write(String path, String cont){
        try(DataOutputStream writer = new DataOutputStream(new FileOutputStream(path))){
            writer.writeUTF(cont);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public static String read(String path){
        try(DataInputStream reader = new DataInputStream(new FileInputStream(path))){
            return reader.readUTF();
        } catch (IOException ex) {
            return null;
        }
    }
}
