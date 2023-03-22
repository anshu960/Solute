//
//  ++CLLocation.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 3/28/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//
import Foundation
import CoreLocation
import MapKit
extension CLLocation{
    public func getLongitude() -> Double {
        let coordinates = self.coordinate
        return coordinates.longitude
    }
    public func getLatitude() ->Double {
        let coordinates = self.coordinate
        return coordinates.latitude
    }
    public func getAltitude() ->Double {
        return self.altitude
    }
    public func getAccuracy()->Double {
        return self.horizontalAccuracy
    }
    
    public func getAdress(completion: @escaping (_ address: NSDictionary?, _ error: Error?) -> ()) {
        let geoCoder = CLGeocoder()
        geoCoder.reverseGeocodeLocation(self) { placemarks, error in
            if let e = error {
                completion(nil, e)
            } else {
                let placeArray = placemarks
                var placeMark: CLPlacemark!
                placeMark = placeArray?[0]
                guard let address = placeMark.addressDictionary as NSDictionary? else {
                    return
                }
                completion(address, nil)
            }
        }
    }
}
