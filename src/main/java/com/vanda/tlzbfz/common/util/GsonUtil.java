package com.vanda.tlzbfz.common.util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public final class GsonUtil {
    public Gson createGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Integer.class,new IntegerDefaultAdapter());
        builder.registerTypeAdapter(String.class,new StringDefaultAdapter());
        builder.setPrettyPrinting();
        builder.disableHtmlEscaping();
        return builder.create();
    }
    public  <T> T  transferObject(String filePath,T obj){
        File file=new File(filePath);
        StringBuffer sb = new StringBuffer() ;
        JsonParser jsonParser=new JsonParser();
        Gson gson = new Gson();
        String line;
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json=sb.toString();
        JsonElement jsonElement=jsonParser.parse(json);
        json = jsonElement.toString();
        @SuppressWarnings("unchecked")
        T transferObj = (T) gson.fromJson(json, obj.getClass());
        return transferObj;
    }

    public  <T> List<T> transferList(String filePath,T obj){
        File file=new File(filePath);
        StringBuffer sb = new StringBuffer() ;
        JsonParser jsonParser=new JsonParser();
        List<T> objs=new ArrayList<T>();
        Gson gson = new Gson();
        String line;
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json=sb.toString();
        JsonElement jsonElement=jsonParser.parse(json);
        JsonArray jsonArray=jsonElement.getAsJsonArray();
        Iterator<JsonElement> it=jsonArray.iterator();
        while(it.hasNext()){
            jsonElement=it.next();
            json=jsonElement.toString();
            @SuppressWarnings("unchecked")
            T transferObj = (T) gson.fromJson(json, obj.getClass());
            objs.add(transferObj);
        }
        return objs;

    }

    public class IntegerDefaultAdapter extends TypeAdapter<Integer>{

        @Override
        public void write(JsonWriter jsonWriter, Integer integer) throws IOException {
            jsonWriter.value(String.valueOf(integer));
        }

        @Override
        public Integer read(JsonReader jsonReader) throws IOException {
            try {
                return Integer.valueOf(jsonReader.nextString());
            }catch (NumberFormatException e){
                e.printStackTrace();
                return -1;
            }
        }
    }

    public class StringDefaultAdapter extends TypeAdapter<String> {

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
//            jsonWriter.value(s);
            try {
                if (value == null) {
                    writer.value("0");
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL){
                jsonReader.nextNull();
                return "0";
            }else{
                return jsonReader.nextString();
            }
        }
    }

}
