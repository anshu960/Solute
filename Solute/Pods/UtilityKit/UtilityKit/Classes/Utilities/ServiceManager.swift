//
//  ServiceManager.swift
//  JustFit
//
//  Created by  on 15/12/18.
//  Copyright © 2018 . All rights reserved.
//
import Foundation
public enum Result {
    case Success,Error,Empty,Timeout
}
public class ServiceManager{
    
    public class func makeServiceCall(toApi:String,withRequest:String,completion:@escaping((String,Result)->Void)) {
        
        let Url = URL(string: toApi)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = withRequest.data(using: .utf8)
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion("Something went wrong",.Empty)
                }
                else{
                    completion(responseString,Result.Success)
                }
            } else if error != nil {
                completion((error?.localizedDescription)!,.Error)
            } else {
                completion("",.Empty)
            }
        }
        task.resume()
    }
    
    
    public class func makeServiceCall(toApi:String,withRequest:NSDictionary,completion:@escaping((String,Result)->Void)) {
        
        let Url = URL(string: toApi)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = withRequest.json().data(using: .utf8)
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion("Something went wrong",.Empty)
                }
                else{
                    completion(responseString,Result.Success)
                }
            } else if error != nil {
                completion((error?.localizedDescription)!,.Error)
            } else {
                completion("",.Empty)
            }
        }
        task.resume()
    }
    public class func makeServiceCall(toApi:String,withRequest:[String:Any],completion:@escaping((String,Result)->Void)) {
        
        let Url = URL(string: toApi)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = withRequest.json().data(using: .utf8)
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion("Something went wrong",.Empty)
                }
                else{
                    completion(responseString,Result.Success)
                }
            } else if error != nil {
                completion((error?.localizedDescription)!,.Error)
            } else {
                completion("",.Empty)
            }
        }
        task.resume()
    }
    
    public class func makeServiceCall(toApi:String,requestData:[String:Any],completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: toApi)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = requestData.json().data(using: .utf8)
        request.allHTTPHeaderFields = ["Content-Type":"application/json"]
        request.timeoutInterval = 60
        
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                var resDict = responseString.toDictionary()
                resDict["ResponseValues"] = responseString
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }else if(resDict["status"] as? String ?? "" != "success"){
                    if let msg = resDict["Message"] as? String{
                        resDict["Message"] = msg
                    }else{
                        resDict["Message"] = "Something went wrong"
                    }
                    completion(resDict,.Error)
                }
                else{
                    completion(resDict,Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    public class func makeServiceCall(endpoint:String,requestData:[String:Any],completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: endpoint)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = requestData.json().data(using: .utf8)
        request.allHTTPHeaderFields = ["Content-Type":"application/json"]
        request.timeoutInterval = 60
        
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                var resDict = responseString.toDictionary()
                resDict["ResponseValues"] = responseString
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }else if(resDict["status"] as? String ?? "" != "success"){
                    if let msg = resDict["Message"] as? String{
                        resDict["Message"] = msg
                    }else{
                        resDict["Message"] = "Something went wrong"
                    }
                    completion(resDict,.Error)
                }
                else{
                    completion(resDict,Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    
    public class func post(endpoint:String,completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: endpoint)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.allHTTPHeaderFields = ["Content-Type":"application/json"]
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                var resDict = responseString.toDictionary()
                resDict["ResponseValues"] = responseString
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }else if(resDict["status"] as? String ?? "" != "success"){
                    if let msg = resDict["Message"] as? String{
                        resDict["Message"] = msg
                    }else{
                        resDict["Message"] = "Something went wrong"
                    }
                    completion(resDict,.Error)
                }
                else{
                    completion(resDict,Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    
    
    public class func makeServiceCall(toApi:String,requestData:[String:Any],header:[String:String],completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: toApi)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = requestData.json().data(using: .utf8)
        request.allHTTPHeaderFields = header
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }
                else{
                    completion(responseString.toDictionary(),Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    
    public class func makeServiceCall(toUrl:String,requestData:[String:Any],header:[String:String],completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: toUrl)!
        var request = URLRequest(url: Url)
        request.httpMethod = "POST"
        request.httpBody = requestData.json().data(using: .utf8)
        request.allHTTPHeaderFields = header
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }
                else{
                    completion(responseString.toDictionary(),Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    public class func makeGetServiceCall(toUrl:String,requestData:[String:Any],header:[String:String],completion:@escaping(([String:Any],Result)->Void)) {
        
        let Url = URL(string: toUrl)!
        var request = URLRequest(url: Url)
        request.httpMethod = "GET"
        request.httpBody = requestData.json().data(using: .utf8)
        request.allHTTPHeaderFields = header
        request.timeoutInterval = 60
        
        let task = URLSession.shared.dataTask(with: request)
        { data,response,error in
            if  data != nil {
                let responseString = String(data: data!, encoding: .utf8)!
                if(responseString == "")
                {
                    completion(["Message":"Something went wrong"],.Empty)
                }
                else{
                    completion(responseString.toDictionary(),Result.Success)
                }
            } else if error != nil {
                completion(["Message":"Something went wrong"],.Error)
            } else {
                completion(["Message":"Something went wrong"],.Empty)
            }
        }
        task.resume()
    }
    
    
//    public class func get(api:String,requestData:[String:Any],completion:@escaping((Response)->Void)) {
//        let urlStr = addParamToUrl(urlStr: api, param: requestData)
//        let Url = URL(string: urlStr)!
//        var request = URLRequest(url: Url)
//        request.httpMethod = "GET"
//        //        request.allHTTPHeaderFields = ["Content-Type":"application/json"]
//        request.timeoutInterval = 60
//        let task = URLSession.shared.dataTask(with: request)
//        { data,response,error in
////            completion(Response(data: data, error: error))
//        }
//        task.resume()
//    }
//    
//    public class func get<Type:Codable>(toUrl:String,requestData:[String:Any],responseType:Type,completion:@escaping((Response)->Void)) {
//        let Url = URL(string: toUrl)!
//        var request = URLRequest(url: Url)
//        request.httpMethod = "GET"
//        request.httpBody = requestData.json().data(using: .utf8)
//        request.allHTTPHeaderFields = ["Content-Type":"application/json"]
//        request.timeoutInterval = 60
//        let task = URLSession.shared.dataTask(with: request)
//        { data,response,error in
////            completion(Response(data: data, error: error,type: responseType))
//        }
//        task.resume()
//    }
    
    public class func addParamToUrl(urlStr:String,param:[String:Any]) -> String {
        var finalUrl = urlStr
        var count = 0
        if(param.count>0){
            for item in param{
                if(count == 0){
                    finalUrl = finalUrl + "?\(item.key)=\(item.value)"
                }else{
                    finalUrl = finalUrl + "&\(item.key)=\(item.value)"
                }
                count+=1
            }
        }
        return finalUrl
    }
}
