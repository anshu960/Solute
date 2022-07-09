package com.friendly.utility

import com.solute.constants.Key
import org.json.JSONArray
import org.json.JSONObject

class AutofillUtill
{
    fun searchCompanies(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(Key.search,search)
//        ServiceManager().makeServiceCall(Server.searchCompanyName,request){
//            print(it.json)
//            if(it.json.has(Key.payload)){
//                val payload = it.json.getJSONArray(Key.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
    
    fun searchDesignation(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(Key.search,search)
//        ServiceManager().makeServiceCall(Server.searchDesignationName,request){
//            if(it.json.has(Key.payload)){
//                val payload = it.json.getJSONArray(Key.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
    
    fun searchTechnologies(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(Key.search,search)
//        ServiceManager().makeServiceCall(Server.searchTechnologyName,request){
//            if(it.json.has(Key.payload)){
//                val payload = it.json.getJSONArray(Key.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
}