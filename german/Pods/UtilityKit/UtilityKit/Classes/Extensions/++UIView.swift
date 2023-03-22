//
//  ++UIView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 16/09/18.
//

import UIKit
extension UIView{
    open func addSubview(_ view:UIView,with animation: AnimationStyle){
        switch animation {
        case .Fade:
            let alpha = view.alpha
            view.alpha = 0
            self.addSubview(view)
            UIView.animate(withDuration: 0.25) {
                view.alpha = alpha
            }
            break
        case .Pop:
            break
        case .Slide:
            break
        }
    }
    
    open func addSubview(_ view:UIView,with animation: AnimationStyle,withDuration:Double){
        switch animation {
        case .Fade:
            let alpha = view.alpha
            view.alpha = 0
            self.addSubview(view)
            UIView.animate(withDuration: withDuration) {
                view.alpha = alpha
            }
            break
        case .Pop:
            break
        case .Slide:
            break
        }
    }
    open func shadow() {
        if(self.layer.cornerRadius == 0){
            self.layer.cornerRadius = 8
        }
        self.layer.shadowColor = UIColor.black.cgColor
        self.layer.shadowOpacity = 0.5
        self.layer.shadowOffset = CGSize(width: 1, height: 2)
    }
    open func rounded(){
        let width = self.frame.width
        let height = self.frame.height
        let center = self.center
        let dimension = width > height ? height : width
        if(dimension != width || dimension != height){
            self.frame = CGRect(x: self.frame.minX, y: self.frame.minY, width: dimension, height: dimension)
            self.center = center
        }
        self.layer.cornerRadius = dimension/2
    }
    open func underline(){
        let bottomLine = CALayer()
        bottomLine.frame = CGRect(x: 0.0, y: self.frame.height - 1, width: self.frame.width, height: 1.0)
        bottomLine.backgroundColor = UIColor.white.cgColor
        self.layer.addSublayer(bottomLine)
    }
}

public enum LINE_POSITION {
    case LINE_POSITION_TOP
    case LINE_POSITION_BOTTOM
}

extension UIView {
   public func addLine(position : LINE_POSITION, color: UIColor, width: Double) {
        let lineView = UIView()
        lineView.backgroundColor = color
        lineView.translatesAutoresizingMaskIntoConstraints = false // This is important!
        self.addSubview(lineView)
        
        let metrics = ["width" : NSNumber(value: width)]
        let views = ["lineView" : lineView]
        self.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "H:|[lineView]|", options:NSLayoutConstraint.FormatOptions(rawValue: 0), metrics:metrics, views:views))
        
        switch position {
        case .LINE_POSITION_TOP:
            self.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:|[lineView(width)]", options:NSLayoutConstraint.FormatOptions(rawValue: 0), metrics:metrics, views:views))
            break
        case .LINE_POSITION_BOTTOM:
            self.addConstraints(NSLayoutConstraint.constraints(withVisualFormat: "V:[lineView(width)]|", options:NSLayoutConstraint.FormatOptions(rawValue: 0), metrics:metrics, views:views))
            break
        }
    }
}
