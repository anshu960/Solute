//
//  WorkoutTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 09/01/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import Foundation
public class DispatchUtill :NSObject{
    
    static let shared = DispatchUtill()
    
    public static let concurrentdefaultQueue = DispatchQueue(label: "com.vivek.concurrent.default", qos: .default, attributes:.concurrent)
    
    public static let concurrentUtilityQueue = DispatchQueue(label: "com.vivek.concurrent.utility", qos: .utility, attributes: .concurrent)
    
    public static let concurrentUserInitiatedQueue = DispatchQueue(label: "com.vivek.concurrent.userInitiated", qos: .userInitiated, attributes: .concurrent)
    
    public static let concurrentBackgroundQueue = DispatchQueue(label: "com.vivek.concurrent.background", qos: .background, attributes: .concurrent)
    
    public static let concurrentUserInteractiveQueue = DispatchQueue(label: "com.vivek.concurrent.userInteractive", qos: .userInteractive, attributes: .concurrent)
    
    public static let concurrentUnspecifiedQueue = DispatchQueue(label: "com.vivek.concurrent.userInteractive", qos: .unspecified, attributes: .concurrent)
    
    //    public static let serialQueue = dispatch_queue_create("com.myApp.mySerialQueue", DISPATCH_QUEUE_SERIAL)
    
    let allQueue = [concurrentUnspecifiedQueue,concurrentBackgroundQueue,concurrentdefaultQueue,concurrentUtilityQueue,concurrentUserInitiatedQueue,concurrentUserInteractiveQueue]
    
    var count = 0
    func submitAsync(task:()){
        allQueue[count].async {
            task
        }
        if(count == 5){
            count = 0
        }else{
            count = count + 1
        }
    }
    
    var syncCount = 0
    func submitSync(task:()){
        allQueue[syncCount].async {
            task
        }
        if(syncCount == 5){
            syncCount = 0
        }else{
            syncCount = syncCount + 1
        }
    }
    
    func getThread(label:String) -> DispatchQueue {
        return DispatchQueue(label: "com.vivek.concurrent.utility.\(label)", qos: .utility, attributes: .concurrent)
        
    }
}
