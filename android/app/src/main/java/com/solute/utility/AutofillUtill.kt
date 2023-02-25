package com.solute.utility

import com.friendly.framework.constants.KeyConstant
import org.json.JSONArray
import org.json.JSONObject

class AutofillUtill
{
    fun searchCompanies(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(KeyConstant.search,search)
//        ServiceManager().makeServiceCall(Server.searchCompanyName,request){
//            print(it.json)
//            if(it.json.has(KeyConstant.payload)){
//                val payload = it.json.getJSONArray(KeyConstant.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
    
    fun searchDesignation(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(KeyConstant.search,search)
//        ServiceManager().makeServiceCall(Server.searchDesignationName,request){
//            if(it.json.has(KeyConstant.payload)){
//                val payload = it.json.getJSONArray(KeyConstant.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
    
    fun searchTechnologies(search:String,completion:(JSONArray)->Unit){
        var request = JSONObject()
        request.put(KeyConstant.search,search)
//        ServiceManager().makeServiceCall(Server.searchTechnologyName,request){
//            if(it.json.has(KeyConstant.payload)){
//                val payload = it.json.getJSONArray(KeyConstant.payload)
//                completion(payload)
//            }else{
//                completion(JSONArray())
//            }
//        }
    }
}