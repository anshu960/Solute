//
//  ExtensionDate.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import Foundation
/**
 Returns: Dates.
 */
extension Date{
    
    //TimeStamp
    public var timeStamp : Double{
        return NSDate().timeIntervalSince1970
    }
    /**
     Returns current time stamp in format = "yyyyMMdd_HH:mm:ss.zzzz"
     */
    public var formattedTimeStamp : String{
        let date = Date(timeIntervalSince1970: self.timeStamp)
        let dateFormatter = DateFormatter()
        //        dateFormatter.timeZone = TimeZone(abbreviation: "GMT")
        dateFormatter.locale = NSLocale.current
        dateFormatter.dateFormat = "yyyyMMdd_HHmmss"
        return dateFormatter.string(from: date)
    }
    
    
    /**
     Returns: Current Date in format YYYYMMDD of type Int.
     */
    public func today()->Int{
        let date = Date()
        let calendar = Calendar.current
        let year = calendar.component(.year, from: date)
        let month = calendar.component(.month, from: date)
        let day = calendar.component(.day, from: date)
        let today = Int("\(year)" + "\(month)" + "\(day)")
        return today!
    }
    public func todayFormated()->String{
        let date = Date()
        let calendar = Calendar.current
        let year = calendar.component(.year, from: date).description
        var month = calendar.component(.month, from: date).description
        let day = calendar.component(.day, from: date).description
        if(Int(month)! < 10){
            month = "0\(month)"
        }
        if(Int(day)! < 10){
            month = "0\(day)"
        }
        return year + month + day
    }
    /**
     Returns: Current Date in format YYYY-MM-DD of type String.
     */
    public func formatedDate() -> String {
        let calendar = Calendar.current
        let year = calendar.component(.year, from: self)
        let month = calendar.component(.month, from: self)
        let day = calendar.component(.day, from: self)
        let returnValue = "\(year)-\(month)-\(day)"
        return returnValue
    }
    /**
     Returns: Current Date in format YYYY-MM-DD of type String.
     */
    public func lastDateOfMonth()->Int{
        let date = Date()
        let calendar = Calendar.current
        
        let year = calendar.component(.year, from: date)
        let month = calendar.component(.month, from: date)
        let range = calendar.range(of: .day, in: .month, for: date)!
        let lastDate = range.count
        let dateToRet = Int("\(year)\(month)\(lastDate)")
        return dateToRet!
    }
    /**
     Parameter year: Year of type Int
     
     Parameter month: Month of type Int
     
     Returns: Current Date in format YYYY\MM\DD as String.
     
     if month is less that 10 it will return incliding 0 before month
     
     example : If year is 2018, month is 3 and date is 4 then it will return 20180304 of type Int
     */
    public func lastDateOfMonth(year:Int,month:Int)->Int{
        let dateComponents = DateComponents(year: year, month: month)
        let calendar = Calendar.current
        let date = calendar.date(from: dateComponents)!
        
        let range = calendar.range(of: .day, in: .month, for: date)!
        let lastDate = range.count
        var dateToRet = Int("\(year)\(month)\(lastDate)")
        if(month<10){
            dateToRet = Int("\(year)0\(month)\(lastDate)")
        }
        if(lastDate < 10){
            dateToRet = Int("\(year)\(month)0\(lastDate)")
        }
        if(month<10 && lastDate < 10){
            dateToRet = Int("\(year)0\(month)0\(lastDate)")
            
        }
        return dateToRet!
    }
    /**
     Returns: Current Year of type Int.
     */
    public func year()->Int{
        let date = Date()
        let calendar = Calendar.current
        
        let year = calendar.component(.year, from: date)
        return year
    }
    /**
     Returns: Current Month of type Int.
     */
    public func month() -> Int {
        let date = Date()
        let calendar = Calendar.current
        
        let month = calendar.component(.month, from: date)
        return month
    }
    /**
     Returns: Current Month in format MM of type String.
     */
    public func monthStr() -> String {
        let date = Date()
        let calendar = Calendar.current
        
        let month = calendar.component(.month, from: date)
        if(month<10){
            return "0\(month)"
        }else{
            return "\(month)"
        }
    }
    /**
     Returns: Current Day of type Int.
     */
    public func day() -> Int{
        let date = Date()
        let calendar = Calendar.current
        
        let day = calendar.component(.day, from: date)
        return day
    }
    /**
     Returns: Current day of type Int.
     */
    public func dayStr() -> String{
        let date = Date()
        let calendar = Calendar.current
        
        let day = calendar.component(.day, from: date)
        if(day<10){
            return "0\(day)"
        }else{
            return "\(day)"
        }
    }
    public func firstDateOfMonth()->Int{
        let date = Date()
        let calendar = Calendar.current
        
        let year = calendar.component(.year, from: date)
        let month = calendar.component(.month, from: date)
        let dateToRet = Int("\(year)\(month)\(01)")
        return dateToRet!
    }
    
    public func linuxDate(timeStamp:String)->Date?{
        let dateFormatter = DateFormatter()
        dateFormatter.timeZone = TimeZone.current
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss.zzzz"
        let date = dateFormatter.date(from: timeStamp)
        return date
    }
    
    //MARK:For hour and minutes
    public func Hour() -> Int {
        let date = Date()
        let calendar = Calendar.current
        let hour = calendar.component(.hour, from: date)
        return hour
    }
    public func Minute() -> Int {
        let date = Date()
        let calendar = Calendar.current
        let minutes = calendar.component(.minute, from: date)
        return minutes
    }
    
}
