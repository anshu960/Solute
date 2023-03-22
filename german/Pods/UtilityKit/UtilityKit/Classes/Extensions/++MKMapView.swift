//
//  ++MKMapView.swift
//  Pods-UtilityKit_Example
//
//  Created by Vivek Kumar on 24/08/18.
//

import UIKit
import MapKit
extension MKMapView {
    func visibleAnnotations() -> [MKAnnotation] {
        return self.annotations(in: self.visibleMapRect).map { obj -> MKAnnotation in return obj as! MKAnnotation }
    }
}
