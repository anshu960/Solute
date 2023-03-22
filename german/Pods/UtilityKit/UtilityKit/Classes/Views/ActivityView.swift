//
//  ActivityUtil.swift
//  HelloCordova
//
//  Created by Vivek Kumar on 5/11/18.
//

import UIKit

public class ActivityUtill: UtilityView {
    public static let shared = ActivityUtill()
    let activityView = UIActivityIndicatorView()
    let activityTextLbl = UILabel()
    var activityText = ""{
        didSet{
            activityTextLbl.text = activityText
        }
    }

    override public func awakeFromNib() {
        super.awakeFromNib()

    }

    override public func layoutSubviews() {
        super.layoutSubviews()
        let window = UIApplication.shared.keyWindow!.rootViewController!.view!
        self.frame = CGRect(x: 0, y: 0, width: Int(window.frame.width), height: Int(window.frame.height))
        self.backgroundColor = UIColor(red: 0/255, green: 0/255, blue: 0/255, alpha: 0.5)
        activityView.style = .whiteLarge
        activityView.frame = CGRect(x: (window.frame.width/2)-50, y: (window.frame.height/2)-50, width: 100, height: 100)
        activityView.color = UIColor.white
        activityView.layer.cornerRadius = 5
        self.activityTextLbl.frame = CGRect(x: 0, y: 67, width: 100, height: 30)
        activityTextLbl.textColor = UIColor.white
        activityTextLbl.backgroundColor = UIColor.clear
        activityTextLbl.textAlignment = .center
        self.activityView.addSubview(self.activityTextLbl)
        self.activityView.startAnimating()
        self.addSubview(self.activityView)
    }
    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
}
