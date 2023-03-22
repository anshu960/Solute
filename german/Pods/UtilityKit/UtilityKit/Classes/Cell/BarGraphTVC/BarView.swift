//
//  BarView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 31/08/19.
//

import UIKit

class BarView: UIView {

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    var barData : BarData?{
        didSet{
            for v in self.subviews{
                v.removeFromSuperview()
            }
            let barDataView = UIView()
            
        }
    }
    
}
