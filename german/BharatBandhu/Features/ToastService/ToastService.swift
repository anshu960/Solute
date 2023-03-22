//
//  ToastService.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 19/03/23.
//

import UIKit
class ToastService{
    public static let shared = ToastService()
    var window : UIWindow? = nil
    var view = UIView()
    var label = UILabel()
    func toas(msg:String) {
        DispatchQueue.main.async {
            if let rootView = self.window{
                self.view.frame = CGRect(x: 20, y: rootView.bounds.height-120, width: rootView.bounds.width-40, height: 60)
                self.view.backgroundColor = UIColor.white
                self.view.layer.cornerRadius = 16
                self.label.frame = CGRect(x: 30, y: rootView.bounds.height-120, width: rootView.bounds.width-60, height: 60)
                self.label.textAlignment = .center
                self.label.numberOfLines = 2
                self.label.text = msg
                self.label.textColor = UIColor.black
                rootView.addSubview(self.view)
                rootView.addSubview(self.label)
                DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                    self.view.removeFromSuperview()
                    self.label.removeFromSuperview()
                }
            }
        }
    }
    
}
