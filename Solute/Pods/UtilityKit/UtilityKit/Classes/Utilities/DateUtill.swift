//
//  DateUtill.swift
//  Friendly
//
//  Created by Vivek Kumar on 01/08/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//
import Foundation
public class DateUtill {
    public static let shared = DateUtill()
    let days : [Int:String] = [1:"sunday",2:"monday",3:"tuesday",4:"wednesday",5:"thursday",6:"friday",7:"saturday"]
    let monthNames : [Int:String] = [
        1:"January",2: "February",3: "March",
        4:"April", 5:"May", 6:"June",
        7:"July", 8:"August",9: "September",
        10: "October", 11:"November",12: "December"
    ];
    
    public func getDayOfWeek()->String {
        let day = "monday"
        let todayDate = NSDate()
        let myCalendar = NSCalendar(calendarIdentifier: NSCalendar.Identifier(rawValue: NSGregorianCalendar))
        let myComponents = myCalendar?.components(.NSWeekdayCalendarUnit, from: todayDate as Date)
        let weekDay = myComponents?.weekday
        if let date = weekDay,let day = days[date]{
            return day
        }
        return day
    }
    public func getMonthName()->String{
        let date = Date()
        let calendar = Calendar.current
        let year = calendar.component(.year, from: date)
        let months = calendar.component(.month, from: date)
        let day = calendar.component(.day, from: date)
        return monthNames[months]!
    }
    
    public func dateToDisplay(timestamp:Double) -> String {
        let date = Date(timeIntervalSince1970: timestamp)
        let dateFormatter = DateFormatter()
        dateFormatter.timeStyle = DateFormatter.Style.medium //Set time style
        dateFormatter.dateStyle = DateFormatter.Style.medium //Set date style
        dateFormatter.timeZone = .current
        dateFormatter.locale = Locale(identifier: "en_GB")
        dateFormatter.setLocalizedDateFormatFromTemplate("MMMMd")
        let localDate = dateFormatter.string(from: date)
        return localDate
    }
    
    func dateToDisplay(timestamp:String) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en-IN")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        let date = dateFormatter.date(from: timestamp)!
        
        let newFormatter = DateFormatter()
        newFormatter.setLocalizedDateFormatFromTemplate("MMMMd")
        return newFormatter.string(from: date)
    }
    
    func date(timestamp:String) -> Date? {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en-IN")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        return dateFormatter.date(from: timestamp)
    }
    
    public func timeToDisplay(timestamp:Double) -> String {
        let date = Date(timeIntervalSince1970: timestamp)
        let dateFormatter = DateFormatter()
        dateFormatter.timeStyle = .short//DateFormatter.Style.medium //Set time style
        dateFormatter.dateStyle = .none //Set date style
        dateFormatter.timeZone = .current
        let localDate = dateFormatter.string(from: date)
        return localDate
    }
}
extension Date{
    func linuxTimestamp() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en-IN")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        return dateFormatter.string(from: self)
    }
    static func formatedDate(timestamp:String) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en-IN")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        if let date = dateFormatter.date(from: timestamp){
            let newFormatter = DateFormatter()
            newFormatter.setLocalizedDateFormatFromTemplate("MMMMdY")
            return newFormatter.string(from: date)
        }else{
            return getCurrentDate()
        }
    }
    static func getCurrentDate() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.timeStyle = .short//DateFormatter.Style.medium //Set time style
        dateFormatter.dateStyle = .none //Set date style
        dateFormatter.timeZone = .current
        dateFormatter.setLocalizedDateFormatFromTemplate("MMMMdY")
        return dateFormatter.string(from: Date())
    }
}
