//
//  ++UIIMageView.swift
//  JustFit
//
//  Created by Vivek Kumar on 17/12/18.
//  Copyright © 2018 Vivek Kumar. All rights reserved.
//

import UIKit
extension UIImageView{
    func tint(color:UIColor){
        self.image = self.image?.withRenderingMode(UIImage.RenderingMode.alwaysTemplate)
        self.tintColor = color
    }
    public func load(url:URL) {
        let newUrl = self.getUrl(url:url)
        DispatchQueue.main.async {
            let activityImdicator = UIActivityIndicatorView()
            activityImdicator.frame = self.bounds
            activityImdicator.backgroundColor = UIColor.lightGray
            activityImdicator.style = .white
            activityImdicator.clipsToBounds = true
            activityImdicator.layer.cornerRadius = self.layer.cornerRadius
            activityImdicator.startAnimating()
            self.viewWithTag(1000)?.removeFromSuperview()
            activityImdicator.tag = 1000
            self.addSubview(activityImdicator)
            DispatchUtill.concurrentBackgroundQueue.async {
                do{
                    let imageData = try Data(contentsOf: newUrl)
                    DispatchQueue.main.async {
                        self.image = UIImage(data: imageData)
                        activityImdicator.removeFromSuperview()
                        if(newUrl == url){
                            self.storeImage(remoteUrl: newUrl, image: self.image!)
                        }
                    }
                    
                }catch{
                    if(newUrl != url){
                        let defaults = UserDefaults()
                        defaults.removeObject(forKey: url.absoluteString)
                        defaults.synchronize()
                        DispatchQueue.main.async {
                            self.load(url: url)
                        }
                    }
                    DispatchQueue.main.async {
                        activityImdicator.removeFromSuperview()
                    }
                }
            }
        }
    }
    
    public func load(_ image:Any) {
        switch image {
        case is String:
            if let url = URL(string: image as! String){
                self.load(url: url)
            }
        case is URL:
            self.load(url: image as! URL)
        case is UIImage:
            self.image = image as? UIImage
        case is [Any]:
            if let urls = image as? [Any]{
                if(urls.count>0){
                    self.load(urls[0])
                }
            }
        default:
            break
        }
    }
    
    private func storeImage(remoteUrl:URL,image:UIImage) {
        var filePath = FileUtility.shared.defaultPath
        let fileName = Date().formattedTimeStamp.description + ".png"
        filePath = filePath.appendingPathComponent(fileName)
        let data = image.pngData()!
        FileUtility.shared.writeFile(directory: filePath, file: data)
        let defaults = UserDefaults()
        defaults.set(fileName, forKey: remoteUrl.absoluteString)
        defaults.synchronize()
    }
    
    
    
    private func getUrl(url:URL)-> URL{
        let defaults = UserDefaults()
        let urlStr = defaults.string(forKey: url.absoluteString) ?? ""
        var filePath = FileUtility.shared.defaultPath
        filePath.appendPathComponent(urlStr)
        if let localurl = URL(string: filePath.absoluteString),urlStr != ""{
            return localurl
        }else{
            return url
        }
    }
    
    
}
//extension UtilityImageView{
//    func present(){
//        //        let imageView = self
//        //        let imageVc = UIViewController()
//
//        //        if let vc = App.presentedController(){
//        //            switch vc.presentedViewController{
//        //            case is UITabBarController:
//        //                let tabVC = vc as! UITabBarController
//        //                let vc = tabVC.presentedViewController
//        //                if let navVC = vc?.navigationController{
//        //                    self.present(navVC: navVC)
//        //                }else if let viewC = vc as? UIViewController{
//        //                    self.present(toVC:viewC)
//        //                }
//        //            case is UIViewController:
//        //                present(toVC: vc )
//        //            default : break
//        //
//        //            }
//        //        }
//    }
//
//    func present(toVC:UIViewController){
//        let imageView = self
//        let imageVc = UIViewController()
//        imageView.frame = toVC.view.bounds
//        imageView.clipsToBounds = true
//        imageVc.view.addSubview(imageView)
//        toVC.present(imageVc, animated: true, completion: nil)
//    }
//    func present(navVC:UINavigationController)  {
//        let imageView = self
//        let imageVc = UIViewController()
//        imageView.frame = navVC.view.bounds
//        imageView.clipsToBounds = true
//        imageVc.view.addSubview(imageView)
//        navVC.pushViewController(imageVc, animated: true)
//    }
//
//
//}
