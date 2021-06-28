package com.example.wikipill;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.UpdateItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.DynamoDBEntry;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.util.json.JsonUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DatabaseAccess {
    private static final String COGNITO_POOL_ID = "us-east-1:634afb0a-1c2c-4875-a73d-3f8a93397a85";
    private static final Regions MY_REGION = Regions.US_EAST_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private Context context;
    private final String DYNAMODB_TABLE = "Medicine";
    CognitoCachingCredentialsProvider credentialsProvider;


    private static volatile DatabaseAccess instance;
    private DatabaseAccess(Context context) {
        this.context =context;
        credentialsProvider = new CognitoCachingCredentialsProvider (context, COGNITO_POOL_ID, MY_REGION);
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);
    }
    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
//    public DynamoDBEntry getItem (){
//        return dbTable.getItem(new Primitive("1")).get("user-email");
//    }
//    public List<Document> getAllItems() {
//        return dbTable.query(new Primitive("1")).getAllResults();
//    }
    public String getPara() {
        Document db = dbTable.getItem(new Primitive("1"));
        return db.get("evion_600").asString();
    }
    public String getsaridon() {
        Document db = dbTable.getItem(new Primitive("1"));
        return db.get("pan_40").asString();
    }
    public String getfexofay() {
       Document db = dbTable.getItem(new Primitive("1"));
       return Objects.requireNonNull(db.get("candid")).asString();
    }
    public void updateFexofay(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("candid",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
    public void updatesaradon(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("pan_40",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
    public void updateParacip(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("evion_600",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
    public void updateBPM(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("BPM",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
    public void updateSpo2(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("SPo2",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
    public  void  updateTest(int intake){
        Document doc = dbTable.getItem(new Primitive("1"));
        doc.put("Test",intake);
        Document result = dbTable.updateItem(doc,new Primitive("1"),new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }
}