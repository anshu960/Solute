//
//  UtilityViewController.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 22/08/18.
//

import UIKit

open class UtilityViewController: UIViewController {
    
    let activityIndicator = UIActivityIndicatorView()
    public var isKeyboardResizingEabled = true
    private var activityView = ActivityUtill()
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
    public func alert(with message:String){
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: "Alert", message: message, preferredStyle: .alert)
            let okAction = UIAlertAction(title: "Ok", style: .default) { (alertAction) in
                
            }
            alertController.addAction(okAction)
            self.present(alertController, animated: true, completion: nil)
        }
    }
    public func alert(title:String,with message:String){
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let okAction = UIAlertAction(title: "Ok", style: .default) { (alertAction) in
                
            }
            alertController.addAction(okAction)
            self.present(alertController, animated: true, completion: nil)
        }
    }
    public func alert(title:String,message:String,completion:(() -> Swift.Void)? = nil){
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            let okAction = UIAlertAction(title: "Ok", style: .default) { (alertAction) in
                completion?()
            }
            alertController.addAction(okAction)
            self.present(alertController, animated: true, completion: completion)
        }
    }
    public func startActivity(){
        DispatchQueue.main.async {
            self.activityIndicator.frame = self.view.bounds
            self.activityIndicator.backgroundColor = UIColor.black.set(alpha: 0.5)
            self.activityIndicator.style = .whiteLarge
            self.activityIndicator.alpha = 0
            self.view.addSubview(self.activityIndicator)
            self.activityIndicator.startAnimating()
            UIView.animate(withDuration: 0.2) {
                self.activityIndicator.alpha = 1
            }
        }
    }
    public func startActivity(with duration:Double){
        DispatchQueue.main.async {
            self.activityIndicator.frame = self.view.bounds
            self.activityIndicator.backgroundColor = UIColor.black.set(alpha: 0.5)
            self.activityIndicator.style = .whiteLarge
            self.activityIndicator.alpha = 0
            self.view.addSubview(self.activityIndicator)
            UIView.animate(withDuration: duration) {
                self.activityIndicator.alpha = 1
            }
        }
    }
    public func stopActivity(){
        DispatchQueue.main.async {
            self.activityIndicator.stopAnimating()
            self.activityIndicator.removeFromSuperview()
        }
    }
    public func stopActivity(with duration:Double){
        UIView.animate(withDuration: duration, animations: {
            self.activityIndicator.alpha = 0
        }) { (isCompleted) in
            if(isCompleted){
                self.activityIndicator.stopAnimating()
                self.activityIndicator.removeFromSuperview()
                self.activityView = ActivityUtill()
            }
        }
    }
    public func startActivity(with title:String){
        DispatchQueue.main.async {
            self.activityView.activityText = title
            self.view?.addSubview(self.activityView, with: .Fade, withDuration: 0.25)
        }
    }
    public func addSubview(subView view:UIView,with duration:Double){
        DispatchQueue.main.async {
            let alpha = view.alpha
            view.alpha = 0
            self.view.addSubview(view)
            UIView.animate(withDuration: duration) {
                view.alpha = alpha
            }
        }
    }
    //Incomplete
    public func addSubview(subView view:UIView,with duration:Double,animation style:AnimationStyle){
        
    }
    
}
